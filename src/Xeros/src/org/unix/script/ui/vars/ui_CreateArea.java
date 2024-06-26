/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.script.ui.vars;

import org.unix.script.manager.ScriptManager;
import org.unix.script.ui.ui_ScriptVars;
import simple.api.ClientContext;
import simple.api.coords.WorldPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class ui_CreateArea extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form ui_CreateArea
     */
    public ui_CreateArea() {
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

        btn_SetA = new javax.swing.JButton();
        btn_SetA.addActionListener(this);
        in_A = new javax.swing.JTextField();
        in_B = new javax.swing.JTextField();
        btn_SetB = new javax.swing.JButton();
        btn_SetB.addActionListener(this);
        jLabel1 = new javax.swing.JLabel();
        in_Name = new javax.swing.JTextField();
        btn_AddArea = new javax.swing.JButton();
        btn_AddArea.addActionListener(this);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btn_SetA.setText("Set A");

        in_A.setEditable(false);
        in_A.setText("");

        in_B.setEditable(false);
        in_B.setText("");

        btn_SetB.setText("Set B");

        jLabel1.setText("Name:");

        btn_AddArea.setText("Add Area");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 86, Short.MAX_VALUE)
                        .addComponent(btn_AddArea))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_Name))
                    .addComponent(in_B)
                    .addComponent(in_A))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_SetA, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(btn_SetB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_SetA)
                    .addComponent(in_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(in_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SetB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(in_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_AddArea)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AddArea;
    private javax.swing.JButton btn_SetA;
    private javax.swing.JButton btn_SetB;
    private javax.swing.JTextField in_A;
    private javax.swing.JTextField in_B;
    private javax.swing.JTextField in_Name;
    private javax.swing.JLabel jLabel1;
    private WorldPoint storeA = null;
    private WorldPoint storeB = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_SetA)) {
            storeA = ClientContext.instance().players.getLocal().getLocation();
            in_A.setText("" + storeA.x + ", " + storeA.y + ", " + storeA.plane);
        }
        if (o.equals(btn_SetB)) {
            storeB = ClientContext.instance().players.getLocal().getLocation();
            in_B.setText("" + storeB.x + ", " + storeB.y + ", " + storeB.plane);
        }

        if (o.equals(btn_AddArea)) {
            if (storeA == null || storeB == null || in_Name.getText().isEmpty()) {
                return;
            }
            if (ui_ScriptVars.cmboBoxModel.getElementAt(0).equals("None...")) {
                ui_ScriptVars.cmboBoxModel.removeElementAt(0);
            }
            ScriptManager.addArea(in_Name.getText(), storeA, storeB);
            System.out.println("Added new area to ScriptManager.areaList - Total: " + ScriptManager.getAreas().length);
            ui_ScriptVars.cmboBoxModel.addElement(in_Name.getText());
            ui_ScriptVars.cbo_AreaList.updateUI();
            ui_ScriptVars.isNewAreaBoxOpen = false;

            this.dispose();
        }
    }
    // End of variables declaration//GEN-END:variables
}
