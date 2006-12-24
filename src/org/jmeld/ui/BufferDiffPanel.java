package org.jmeld.ui;

import com.jgoodies.forms.layout.*;

import org.jmeld.*;
import org.jmeld.diff.*;
import org.jmeld.ui.text.*;
import org.jmeld.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BufferDiffPanel
       extends JPanel
       implements JMeldContentPanelIF
{
  private static int         instanceCounter = 0;
  private int                instanceCount = ++instanceCounter;
  private JMeldPanel         mainPanel;
  private FilePanel[]        filePanels;
  private JMRevision         currentRevision;
  private JMDelta            selectedDelta;
  private MyUndoManager      undoManager = new MyUndoManager();
  private ScrollSynchronizer scrollSynchronizer;
  private JMDiff             diff;

  BufferDiffPanel(JMeldPanel mainPanel)
  {
    this.mainPanel = mainPanel;

    diff = new JMDiff();

    filePanels = new FilePanel[3];

    init();

    setFocusable(true);
  }

  public void setBufferDocuments(
    BufferDocumentIF bd1,
    BufferDocumentIF bd2,
    JMDiff           diff,
    JMRevision       revision)
  {
    this.diff = diff;

    if (bd1 != null)
    {
      filePanels[0].setBufferDocument(bd1);
    }

    if (bd2 != null)
    {
      filePanels[1].setBufferDocument(bd2);
    }

    if (bd1 != null && bd2 != null)
    {
      filePanels[0].setRevision(revision);
      filePanels[1].setRevision(revision);
    }

    currentRevision = revision;
    repaint();
  }

  public String getTitle()
  {
    String           title;
    BufferDocumentIF bd;

    title = "";

    for (FilePanel filePanel : filePanels)
    {
      if (filePanel == null)
      {
        continue;
      }

      bd = filePanel.getBufferDocument();
      if (bd == null)
      {
        continue;
      }

      if (!StringUtil.isEmpty(title))
      {
        title += "-";
      }

      title += bd.getShortName();
    }

    return title;
  }

  public void diff()
  {
    BufferDocumentIF bd1;
    BufferDocumentIF bd2;

    bd1 = filePanels[0].getBufferDocument();
    bd2 = filePanels[1].getBufferDocument();

    if (bd1 != null && bd2 != null)
    {
      try
      {
        currentRevision = diff.diff(
            bd1.getLines(),
            bd2.getLines());

        filePanels[0].setRevision(currentRevision);
        filePanels[1].setRevision(currentRevision);
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }

    repaint();
  }

  private void init()
  {
    FormLayout      layout;
    String          columns;
    String          rows;
    CellConstraints cc;

    columns = "3px, pref, 3px, 0:grow, 5px, min, 60px, 0:grow, 25px, min, 3px, pref, 3px";
    rows = "6px, pref, 3px, fill:0:grow, 6px";
    layout = new FormLayout(columns, rows);
    cc = new CellConstraints();

    setLayout(layout);

    filePanels[0] = new FilePanel(this, BufferDocumentIF.ORIGINAL);
    filePanels[1] = new FilePanel(this, BufferDocumentIF.REVISED);

    // panel for file1
    add(
      new RevisionBar(this, filePanels[0], true),
      cc.xy(2, 4));
    add(
      filePanels[0].getSaveButton(),
      cc.xy(2, 2));
    add(
      filePanels[0].getFileLabel(),
      cc.xyw(4, 2, 3));
    add(
      filePanels[0].getScrollPane(),
      cc.xyw(4, 4, 3));

    add(
      new DiffScrollComponent(this, filePanels[0], filePanels[1]),
      cc.xy(7, 4));

    // panel for file2
    add(
      new RevisionBar(this, filePanels[1], false),
      cc.xy(12, 4));
    add(
      filePanels[1].getFileLabel(),
      cc.xyw(8, 2, 3));
    add(
      filePanels[1].getScrollPane(),
      cc.xyw(8, 4, 3));
    add(
      filePanels[1].getSaveButton(),
      cc.xy(12, 2));

    scrollSynchronizer = new ScrollSynchronizer(this, filePanels[0],
        filePanels[1]);
  }

  void toNextDelta(boolean next)
  {
    if (next)
    {
      doDown();
    }
    else
    {
      doUp();
    }

    //scrollSynchronizer.toNextDelta(next);
  }

  JMRevision getCurrentRevision()
  {
    return currentRevision;
  }

  public void resetUndoManager()
  {
    undoManager.discardAllEdits();
  }

  public boolean checkSave()
  {
    SavePanelDialog dialog;

    if (!isSaveEnabled())
    {
      return true;
    }

    dialog = new SavePanelDialog(mainPanel);
    for (FilePanel filePanel : filePanels)
    {
      if (filePanel != null)
      {
        dialog.add(filePanel.getBufferDocument());
      }
    }

    dialog.show();

    if (dialog.isOK())
    {
      dialog.doSave();
      return true;
    }

    return false;
  }

  public void doSave()
  {
    BufferDocumentIF document;

    for (FilePanel filePanel : filePanels)
    {
      if (filePanel == null)
      {
        continue;
      }

      if (!filePanel.isDocumentChanged())
      {
        continue;
      }

      document = filePanel.getBufferDocument();

      try
      {
        document.write();
      }
      catch (JMeldException ex)
      {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(mainPanel,
          "Can't write file" + document.getName(), "Problem writing file",
          JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public boolean isSaveEnabled()
  {
    for (FilePanel filePanel : filePanels)
    {
      if (filePanel != null && filePanel.isDocumentChanged())
      {
        return true;
      }
    }

    return false;
  }

  public boolean isUndoEnabled()
  {
    return undoManager.canUndo();
  }

  public void doUndo()
  {
    try
    {
      if (undoManager.canUndo())
      {
        undoManager.undo();
      }
    }
    catch (CannotUndoException ex)
    {
      System.out.println("Unable to undo: " + ex);
      ex.printStackTrace();
    }
  }

  public boolean isRedoEnabled()
  {
    return undoManager.canRedo();
  }

  public void doRedo()
  {
    try
    {
      if (undoManager.canRedo())
      {
        undoManager.redo();
      }
    }
    catch (CannotUndoException ex)
    {
      System.out.println("Unable to undo: " + ex);
      ex.printStackTrace();
    }
  }

  public MyUndoManager getUndoHandler()
  {
    return undoManager;
  }

  public void checkActions()
  {
    mainPanel.checkActions();
  }

  public class MyUndoManager
         extends UndoManager
         implements UndoableEditListener
  {
    CompoundEdit activeEdit;

    private MyUndoManager()
    {
    }

    public void start(String text)
    {
      activeEdit = new CompoundEdit();
    }

    public void end(String text)
    {
      activeEdit.end();
      addEdit(activeEdit);
      activeEdit = null;

      checkActions();
    }

    public void undoableEditHappened(UndoableEditEvent e)
    {
      if (activeEdit != null)
      {
        activeEdit.addEdit(e.getEdit());
        return;
      }

      addEdit(e.getEdit());
      checkActions();
    }
  }

  public void doLeft()
  {
    System.out.println("doLeft: " + instanceCount);
  }

  public void doRight()
  {
    System.out.println("doRight: " + instanceCount);
  }

  public void doDown()
  {
    JMDelta       d;
    List<JMDelta> deltas;
    int           index;

    if (currentRevision == null)
    {
      return;
    }

    deltas = currentRevision.getDeltas();
    index = deltas.indexOf(getSelectedDelta());
    if (index == -1)
    {
      // I don't know it now anymore!
      selectedDelta = null;
    }
    else
    {
      // Select the next delta if there is any.
      if (index + 1 < deltas.size())
      {
        selectedDelta = deltas.get(index + 1);
        scrollSynchronizer.showDelta(selectedDelta);
      }
    }
  }

  public void doUp()
  {
    JMDelta       d;
    List<JMDelta> deltas;
    int           index;

    if (currentRevision == null)
    {
      return;
    }

    deltas = currentRevision.getDeltas();
    index = deltas.indexOf(getSelectedDelta());
    if (index == -1)
    {
      // I don't know it now anymore!
      selectedDelta = null;
    }
    else
    {
      // Select the next delta if there is any.
      if (index - 1 >= 0)
      {
        selectedDelta = deltas.get(index - 1);
        scrollSynchronizer.showDelta(selectedDelta);
      }
    }
  }

  public void doZoom(boolean direction)
  {
    JTextComponent c;
    Font           font;
    float          size;
    Zoom zoom;

    for (FilePanel p : filePanels)
    {
      if(p == null)
      {
        continue;
      }
      
      c = p.getEditor();

      zoom = (Zoom) c.getClientProperty("JMeld.zoom");
      if (zoom == null)
      {
        zoom = new Zoom();
        zoom.font = c.getFont();
        zoom.size = zoom.font.getSize();
        c.putClientProperty("JMeld.zoom", zoom);
      }

      size = c.getFont().getSize() + (direction ? 1.0f : -1.0f);
      size = size > 100.0f ? 100.0f : size;
      size = size < 5.0f ? 5.0f : size;

      c.setFont(zoom.font.deriveFont(size));
    }
  }

  class Zoom
  {
    Font  font;
    float size;
  }

  public JMDelta getSelectedDelta()
  {
    List<JMDelta> deltas;

    if (currentRevision == null)
    {
      return null;
    }

    deltas = currentRevision.getDeltas();
    if (deltas.size() == 0)
    {
      return null;
    }

    if (selectedDelta == null)
    {
      selectedDelta = deltas.get(0);
    }

    return selectedDelta;
  }
}
