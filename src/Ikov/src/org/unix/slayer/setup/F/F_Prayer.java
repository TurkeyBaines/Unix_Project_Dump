/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.F;

import org.unix.slayer.setup.D.D_Loot;
import org.unix.slayer.setup.E.E_Food;
import org.unix.slayer.setup.H.H_FinishingUp;
import org.unix.slayer.setup.SetupManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class F_Prayer extends javax.swing.JFrame implements ActionListener {


    public F_Prayer() {
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        check_Protection = new javax.swing.JCheckBox();
        check_StatBoost = new javax.swing.JCheckBox();
        check_Potion = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_Back = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Select your Prayer Preferences");

        check_Protection.setText("Protection Prayers");
        if (SetupManager.currentConfig.ToProtect != -1) {
            if (SetupManager.currentConfig.ToProtect == 0) { check_Protection.setSelected(false); } else { check_Protection.setSelected(true); }
        }

        check_StatBoost.setText("Stat Boosting Prayers");
        if (SetupManager.currentConfig.ToBoost != -1) {
            if (SetupManager.currentConfig.ToBoost == 0) { check_StatBoost.setSelected(false); } else { check_StatBoost.setSelected(true); }
        }

        check_Potion.setText("Use Prayer Potions");
        if (SetupManager.currentConfig.ToPrayerPotion != -1) {
            if (SetupManager.currentConfig.ToPrayerPotion == 0) { check_Potion.setSelected(false); } else { check_Potion.setSelected(true); }
        }

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 8)); // NOI18N
        jLabel3.setText("Please ensure Pots are in your");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 8)); // NOI18N
        jLabel4.setText("Bank Preset");

        btn_Back.setText("Back");
        btn_Back.addActionListener(this);

        btn_Next.setText("Next");
        btn_Next.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check_Potion)
                            .addComponent(check_StatBoost)
                            .addComponent(jLabel1)
                            .addComponent(check_Protection)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel4)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Next)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(check_Protection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_StatBoost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_Potion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Next;
    private javax.swing.JCheckBox check_Potion;
    private javax.swing.JCheckBox check_Protection;
    private javax.swing.JCheckBox check_StatBoost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s.equals(btn_Back)) {
            new E_Food().setVisible(true);
            D_Loot.foodWindowOpen = true;
            E_Food.prayerWindowOpen = false;
            this.dispose();
        } else if (s.equals(btn_Next)) {
            SetupManager.currentConfig.ToProtect = check_Protection.isSelected() ? 1 : 0;
            SetupManager.currentConfig.ToBoost = check_StatBoost.isSelected() ? 1 : 0;
            SetupManager.currentConfig.ToPrayerPotion = check_Potion.isSelected() ? 1 : 0;
            new H_FinishingUp().setVisible(true);
            this.dispose();
            E_Food.prayerWindowOpen = false;
        }

    }
    // End of variables declaration//GEN-END:variables
}
