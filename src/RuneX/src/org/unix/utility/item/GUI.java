/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.utility.item;

import simple.api.ClientContext;
import simple.api.wrappers.SimpleItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Turk
 */
public class GUI extends javax.swing.JFrame implements ActionListener {

    ClientContext c = ClientContext.instance();

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setTitle("Item Finder");
        setResizable(false);

        text_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_output = new javax.swing.JTextArea();

        text_search.setText("jTextField1");

        btn_search.setText("Search");
        btn_search.addActionListener(this);

        text_output.setColumns(20);
        text_output.setRows(5);
        jScrollPane1.setViewportView(text_output);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(text_search, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_search)))
                                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(text_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_search))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea text_output;
    private javax.swing.JTextField text_search;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_search)) {
            if (!text_search.getText().isEmpty()) {
                text_output.setText("");
                SimpleItem sItem = null;
                try {
                    for (int i = 0; i < 40000; i++) {
                        sItem = new SimpleItem(c, i, 1);
                        if (sItem != null) {
                            if (sItem.getName().toLowerCase().contains(text_search.getText().toLowerCase())) {
                                text_output.setText(text_output.getText() + "Name: " + sItem.getName() + " | id: " + sItem.getId() + " | Stack: " + sItem.isStackable() + "\n");
                            }
                        }
                    }
                } catch (Exception ex) {}
            }
        }
    }
    // End of variables declaration//GEN-END:variables
}
