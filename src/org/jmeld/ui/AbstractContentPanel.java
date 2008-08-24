/*
   JMeld is a visual diff and merge tool.
   Copyright (C) 2007  Kees Kuip
   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.
   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.
   You should have received a copy of the GNU Lesser General Public
   License along with this library; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA  02110-1301  USA
 */
package org.jmeld.ui;

import org.jmeld.ui.search.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

public class AbstractContentPanel
       extends JPanel
       implements JMeldContentPanelIF
{
  private MyUndoManager undoManager = new MyUndoManager();

  public boolean isSaveEnabled()
  {
    return false;
  }

  public void doSave()
  {
  }

  public boolean checkSave()
  {
    return true;
  }

  public boolean isUndoEnabled()
  {
    return getUndoHandler().canUndo();
  }

  public void doUndo()
  {
    try
    {
      if (getUndoHandler().canUndo())
      {
        getUndoHandler().undo();
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
    return getUndoHandler().canRedo();
  }

  public void doRedo()
  {
    try
    {
      if (getUndoHandler().canRedo())
      {
        getUndoHandler().redo();
      }
    }
    catch (CannotUndoException ex)
    {
      System.out.println("Unable to undo: " + ex);
      ex.printStackTrace();
    }
  }

  public void doLeft()
  {
  }

  public void doRight()
  {
  }

  public void doUp()
  {
  }

  public void doDown()
  {
  }

  public void doZoom(boolean direction)
  {
  }

  public void doGoToSelected()
  {
  }

  public void doGoToFirst()
  {
  }

  public void doGoToLast()
  {
  }

  public SearchHits doSearch(SearchCommand command)
  {
    return null;
  }

  public void doNextSearch()
  {
  }

  public void doPreviousSearch()
  {
  }

  public void doRefresh()
  {
  }

  public void doMergeMode(boolean mergeMode)
  {
  }

  public boolean checkExit()
  {
    return true;
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

    public void add(UndoableEdit edit)
    {
      addEdit(edit);
    }

    public void end(String text)
    {
      activeEdit.end();
      addEdit(activeEdit);
      activeEdit = null;

      checkActions();
    }

    @Override
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

  public MyUndoManager getUndoHandler()
  {
    return undoManager;
  }

  public void checkActions()
  {
  }
}
