/*
 * FolderDiffForm.java
 *
 * Created on January 28, 2007, 11:17 AM
 */

package org.jmeld.ui;

/**
 *
 * @author  kees
 */
public class FolderDiffForm extends AbstractContentPanel
{
  
  /** Creates new form FolderDiffForm */
  public FolderDiffForm ()
  {
    initComponents ();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents()
  {

    folder1Label = new org.jmeld.ui.swing.DiffLabel();
    folderBar = new javax.swing.JLabel();
    folder2Label = new org.jmeld.ui.swing.DiffLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    folderTreeTable = new org.jmeld.ui.swing.table.JMTreeTable();
    onlyRightButton = new javax.swing.JToggleButton();
    leftRightChangedButton = new javax.swing.JToggleButton();
    onlyLeftButton = new javax.swing.JToggleButton();
    leftRightUnChangedButton = new javax.swing.JToggleButton();
    hierarchyComboBox = new javax.swing.JComboBox();
    expandAllButton = new javax.swing.JButton();
    collapseAllButton = new javax.swing.JButton();

    folder1Label.setText("Left name of directory");

    folderBar.setText("jLabel2");

    folder2Label.setText("Right name of directory");

    jScrollPane1.setViewportView(folderTreeTable);

    onlyRightButton.setText("R");

    leftRightChangedButton.setText("LR");

    onlyLeftButton.setText("L");

    leftRightUnChangedButton.setText("Un");

    hierarchyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    expandAllButton.setText("Exp");

    collapseAllButton.setText("Col");

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(folder1Label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(layout.createSequentialGroup()
                .add(expandAllButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(collapseAllButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(hierarchyComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 147, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
              .add(layout.createSequentialGroup()
                .add(onlyLeftButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(leftRightChangedButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(onlyRightButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(leftRightUnChangedButton))
              .add(folder2Label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
          .add(folderBar))
        .addContainerGap())
    );

    layout.linkSize(new java.awt.Component[] {leftRightChangedButton, leftRightUnChangedButton, onlyLeftButton, onlyRightButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(folder1Label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(folder2Label, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(onlyRightButton)
            .add(leftRightChangedButton)
            .add(onlyLeftButton)
            .add(leftRightUnChangedButton))
          .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(collapseAllButton)
            .add(expandAllButton)
            .add(hierarchyComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(folderBar)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents
  
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  protected javax.swing.JButton collapseAllButton;
  protected javax.swing.JButton expandAllButton;
  protected org.jmeld.ui.swing.DiffLabel folder1Label;
  protected org.jmeld.ui.swing.DiffLabel folder2Label;
  protected javax.swing.JLabel folderBar;
  protected org.jmeld.ui.swing.table.JMTreeTable folderTreeTable;
  protected javax.swing.JComboBox hierarchyComboBox;
  protected javax.swing.JScrollPane jScrollPane1;
  protected javax.swing.JToggleButton leftRightChangedButton;
  protected javax.swing.JToggleButton leftRightUnChangedButton;
  protected javax.swing.JToggleButton onlyLeftButton;
  protected javax.swing.JToggleButton onlyRightButton;
  // End of variables declaration//GEN-END:variables
  
}
