/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.script.ui;

import org.unix.script.manager.ScriptManager;
import org.unix.script.tasks.Bank;
import org.unix.script.ui.preset.ui_GetPreset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 *
 * @author 0x181
 */
public class ui_Banking extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form ui_Banking
     */
    public ui_Banking() {
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btn_Open = new javax.swing.JButton();
        btn_Close = new javax.swing.JButton();
        btn_DepInv = new javax.swing.JButton();
        btn_GetPre = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Store Items and Withdraw Others");

        btn_Open.setText("Open the Bank");
        btn_Open.addActionListener(this);

        btn_Close.setText("Close the Bank");
        btn_Close.addActionListener(this);

        btn_DepInv.setText("Deposit Inventory");
        btn_DepInv.addActionListener(this);

        btn_GetPre.setText("Get Preset");
        btn_GetPre.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Open)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Close))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_DepInv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_GetPre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Open)
                    .addComponent(btn_Close))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DepInv)
                    .addComponent(btn_GetPre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Close;
    private javax.swing.JButton btn_DepInv;
    private javax.swing.JButton btn_GetPre;
    private javax.swing.JButton btn_Open;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btn_Open)) {
            ScriptManager.addTask(new Bank(0));
            SetupUI.subMenuOpen = false;
            SetupUI.taskListModel.addElement("[BANK] Open");
            SetupUI.list_Tasks.updateUI();
            this.dispose();
        } else if (o.equals(btn_Close)) {
            ScriptManager.addTask(new Bank(1));
            SetupUI.subMenuOpen = false;
            SetupUI.taskListModel.addElement("[BANK] Close");
            SetupUI.list_Tasks.updateUI();
            this.dispose();
        } else if (o.equals(btn_DepInv)) {
            ScriptManager.addTask(new Bank(2));
            SetupUI.subMenuOpen = false;
            SetupUI.taskListModel.addElement("[BANK] Inventory");
            SetupUI.list_Tasks.updateUI();
            this.dispose();
        } else if (o.equals(btn_GetPre)) {
            new ui_GetPreset().setVisible(true);
            this.dispose();
        }

    }
    // End of variables declaration//GEN-END:variables
}