/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package org.unix.packetSender.gui;

import org.unix.packetSender.uCheater;
import simple.api.ClientContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends javax.swing.JFrame implements ActionListener {

    ClientContext c = ClientContext.instance();

    DefaultComboBoxModel SPELL = new DefaultComboBoxModel<>(new String[] {"Low-Alch", "High Alch", "Vengeance", "Ice Barrage", "Blood Barrage", "Superheat"});
    DefaultComboBoxModel INTERACT = new DefaultComboBoxModel<>(new String[] {"Bury", "Clean", "Use-with"});
    DefaultComboBoxModel EAT = new DefaultComboBoxModel<>(new String[] { "Single Item", "Multi-Item" });

    uCheater sc;

    public GUI(uCheater instance) {
        sc = instance;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        box_MASTER = new javax.swing.JComboBox<>();
        box_SUB = new javax.swing.JComboBox<>();
        txt_SUB = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_SEND = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Send Some Actions!");

        box_MASTER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Use Spell", "Interact Item" }));
        box_MASTER.addActionListener(this);



        box_SUB.setModel(SPELL);
        box_SUB.addActionListener(this);

        txt_SUB.setText("null");

        jLabel2.setText("Text Input:");

        btn_SEND.setText("Send Action");
        btn_SEND.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(box_MASTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txt_SUB, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                        .addComponent(box_SUB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_SEND))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(jLabel1)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(box_MASTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(box_SUB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_SEND))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_SUB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }


    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> box_MASTER;
    private javax.swing.JComboBox<String> box_SUB;
    private javax.swing.JButton btn_SEND;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txt_SUB;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(box_MASTER)) {
            int a = box_MASTER.getSelectedIndex();
            if (a == 0) {
                box_SUB.setModel(SPELL);
            } else if (a == 1) {
                box_SUB.setModel(INTERACT);
            } else if (a == 2) {
                box_SUB.setModel(EAT);
            }
            box_SUB.updateUI();
        } else if (e.getSource().equals(box_SUB)) {
            int a = box_MASTER.getSelectedIndex();
            int b = box_SUB.getSelectedIndex();
            if (!txt_SUB.isVisible()) {
                if (a == 0) {
                    if (b < 2) {
                        txt_SUB.setVisible(true);
                    }
                } else {
                    txt_SUB.setVisible(true);
                }
            }
        } else if (e.getSource().equals(btn_SEND)) {
            sc.sendAction(box_MASTER.getSelectedIndex(), box_SUB.getSelectedIndex(), txt_SUB.getText());
        }
    }

}
