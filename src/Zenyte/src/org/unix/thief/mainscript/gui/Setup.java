/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.thief.mainscript.gui;

import org.unix.Database;
import org.unix.thief.mainscript.Config;
import org.unix.thief.mainscript.uThief;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Turk
 */
public class Setup extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form Setup
     */
    public Setup() {
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
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbo_NPC = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        cbo_Food = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        chx_DisableFood = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        chx_Upgrade = new javax.swing.JCheckBox();
        btn_Start = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Welcome to");

        jLabel2.setFont(new java.awt.Font("Monotype Corsiva", 1, 18)); // NOI18N
        jLabel2.setText("uThief");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Select an NPC");

        cbo_NPC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Man", "Woman", "Cake Stall", "Silk Stall", "Master Farmer", "Guard", "Knight of Ardougne" }));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setText("Select a Food");

        cbo_Food.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Shrimp", "Tuna", "Monkfish", "Trout" }));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        chx_DisableFood.setText("Disable Food");
        chx_DisableFood.addActionListener(this);

        jLabel5.setText("Are you a Pro Member?");

        chx_Upgrade.setText("Pro Mode!");
        chx_Upgrade.setToolTipText("This mode will automatically upgrade your thieving victim to get you all the way to 99!");
        chx_Upgrade.addActionListener(this);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(cbo_Food, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(chx_DisableFood))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chx_Upgrade)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(cbo_NPC, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_NPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_Food, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chx_DisableFood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chx_Upgrade)
                .addGap(15, 15, 15))
        );

        btn_Start.setText("Start Script");
        btn_Start.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Start)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Start)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Start;
    private javax.swing.JComboBox<String> cbo_Food;
    private javax.swing.JComboBox<String> cbo_NPC;
    private javax.swing.JCheckBox chx_DisableFood;
    private javax.swing.JCheckBox chx_Upgrade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btn_Start)) {

            if (!chx_DisableFood.isSelected()) {
                Config.useFood = true;
                if (cbo_Food.getSelectedItem().toString().equals("Tuna")) {
                    Config.foodID = Database.food.TUNA;
                } else if (cbo_Food.getSelectedItem().toString().equals("Monkfish")) {
                    Config.foodID = Database.food.MONKFISH;
                } else if (cbo_Food.getSelectedItem().toString().equals("Trout")) {
                    Config.foodID = Database.food.TROUT;
                } else if (cbo_Food.getSelectedItem().toString().equals("Shrimp")) {
                    Config.foodID = Database.food.SHRIMP;
                }
            }

            if (!chx_Upgrade.isSelected()) {
                if (cbo_NPC.getSelectedItem().toString().equals("Man")) {
                    Config.npcID = Database.npcs.MAN;
                } else if (cbo_NPC.getSelectedItem().toString().equals("Woman")) {
                    Config.npcID = Database.npcs.WOMAN;
                } else if (cbo_NPC.getSelectedItem().toString().equals("Cake Stall")) {
                    Config.npcID = new int[] {Database.objects.CAKE_STALL};
                    Config.isStall = true;
                } else if (cbo_NPC.getSelectedItem().toString().equals("Silk Stall")) {
                    Config.npcID = new int[] {Database.objects.SILK_STALL};
                    Config.isStall = true;
                } else if (cbo_NPC.getSelectedItem().toString().equals("Master Farmer")) {
                    Config.npcID = new int[] {Database.npcs.MASTER_FARMER};
                } else if (cbo_NPC.getSelectedItem().toString().equals("Guard")) {
                    Config.npcID = Database.npcs.GUARD;
                } else if (cbo_NPC.getSelectedItem().toString().equals("Knight of Ardougne")) {
                    Config.npcID = new int[] {Database.npcs.KNIGHT_OF_ARDOUGNE};
                }
            }

            uThief.START = true;
            this.dispose();


        } else if (o.equals(chx_DisableFood)) {
            if (chx_DisableFood.isSelected()) {
                cbo_Food.setEnabled(false);
            } else {
                cbo_Food.setEnabled(true);
            }
        } else if (o.equals(chx_Upgrade)) {
            if (chx_Upgrade.isSelected())
                cbo_NPC.setEnabled(false);
            else
                cbo_NPC.setEnabled(true);
        }
    }
    // End of variables declaration//GEN-END:variables
}
