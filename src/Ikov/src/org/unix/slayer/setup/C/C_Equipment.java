/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.C;

import org.unix.slayer.setup.A.A_Presets;
import org.unix.slayer.setup.B.B_Tasks;
import org.unix.slayer.setup.D.D_Loot;
import org.unix.slayer.setup.SetupManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class C_Equipment extends javax.swing.JFrame implements ActionListener {

    public static boolean lootWindowOpen = false;

    public C_Equipment() {
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        combi_Melee = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        combi_Range = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combi_Mage = new javax.swing.JComboBox<>();
        btn_Back = new javax.swing.JButton();
        btn_Next = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Please select a bank preset for all 3 combat styles...");

        jLabel2.setText("Melee");

        combi_Melee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Preset 1", "Preset 2", "Preset 3", "Preset 4", "Preset 5", "Preset 6", "Preset 7", "Preset 8", "Preset 9", "Preset 10", }));
        if (SetupManager.currentConfig.MeleePreset != -1) {
            combi_Melee.setSelectedIndex(SetupManager.currentConfig.MeleePreset);
            combi_Melee.updateUI();
        }

        jLabel3.setText("Range");

        combi_Range.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Preset 1", "Preset 2", "Preset 3", "Preset 4", "Preset 5", "Preset 6", "Preset 7", "Preset 8", "Preset 9", "Preset 10", }));
        if (SetupManager.currentConfig.RangePreset != -1) {
            combi_Range.setSelectedIndex(SetupManager.currentConfig.RangePreset);
            combi_Range.updateUI();
        }

        jLabel4.setText("Magic");

        combi_Mage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Preset 1", "Preset 2", "Preset 3", "Preset 4", "Preset 5", "Preset 6", "Preset 7", "Preset 8", "Preset 9", "Preset 10", }));
        if (SetupManager.currentConfig.MagePreset != -1) {
            combi_Mage.setSelectedIndex(SetupManager.currentConfig.MagePreset);
            combi_Mage.updateUI();
        }

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
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_Back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Next)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combi_Melee, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(combi_Range, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combi_Mage, 0, 105, Short.MAX_VALUE))))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combi_Melee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combi_Range, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combi_Mage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_Next;
    private javax.swing.JComboBox<String> combi_Mage;
    private javax.swing.JComboBox<String> combi_Melee;
    private javax.swing.JComboBox<String> combi_Range;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s.equals(btn_Back)) {
            new B_Tasks().setVisible(true);
            this.dispose();
            A_Presets.tasksWindowOpen = true;
            B_Tasks.gearWindowOpen = false;
        } else if (s.equals(btn_Next)) {
            if (!lootWindowOpen) {
                SetupManager.currentConfig.MeleePreset = combi_Melee.getSelectedIndex();
                SetupManager.currentConfig.RangePreset = combi_Range.getSelectedIndex();
                SetupManager.currentConfig.MagePreset = combi_Mage.getSelectedIndex();
                new D_Loot().setVisible(true);
                this.dispose();
                lootWindowOpen = true;
                B_Tasks.gearWindowOpen = false;
            }
        }
    }
    // End of variables declaration//GEN-END:variables
}
