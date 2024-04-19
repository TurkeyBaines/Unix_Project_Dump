/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.H;

import org.unix.slayer.Constants;
import org.unix.slayer.setup.A.A_Presets;
import org.unix.slayer.setup.B.B_Tasks;
import org.unix.slayer.setup.C.C_Equipment;
import org.unix.slayer.setup.D.D_Loot;
import org.unix.slayer.setup.E.E_Food;
import org.unix.slayer.setup.F.F_Prayer;
import org.unix.slayer.setup.SetupManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 0x181
 */
public class H_FinishingUp extends javax.swing.JFrame implements ActionListener {

    public H_FinishingUp() {
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saveToFile();

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        info_Preset_Name = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        info_Task_Difficulty = new javax.swing.JLabel();
        info_Task_Kill = new javax.swing.JLabel();
        info_Task_Skip = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        info_Gear_Melee = new javax.swing.JLabel();
        info_Gear_Range = new javax.swing.JLabel();
        info_Gear_Mage = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        info_Loot_Total = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        info_Food_EatAt = new javax.swing.JLabel();
        info_Food_TotalFood = new javax.swing.JLabel();
        info_Food_TotalPots = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        info_Prayer_Protect = new javax.swing.JLabel();
        info_Prayer_Stat = new javax.swing.JLabel();
        info_Prayer_Potion = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        btn_Preset = new javax.swing.JButton();
        btn_Task = new javax.swing.JButton();
        btn_Gear = new javax.swing.JButton();
        btn_Loot = new javax.swing.JButton();
        btn_Food = new javax.swing.JButton();
        btn_Prayer = new javax.swing.JButton();
        btn_StartScript = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lets make sure we got everything right!");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Presets!");

        info_Preset_Name.setText("Preset Name: " + SetupManager.currentConfig.presetName);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tasks!");

        info_Task_Difficulty.setText("Difficulty: " + SetupManager.currentConfig.Difficulty);

        info_Task_Kill.setText("Tasks to Kill: " + SetupManager.currentConfig.ToKill.length);

        info_Task_Skip.setText("Tasks to Skip: " + SetupManager.currentConfig.ToSkip.length);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Gear!");

        info_Gear_Melee.setText("Melee Preset: " + SetupManager.currentConfig.MeleePreset);

        info_Gear_Range.setText("Range Preset: " + SetupManager.currentConfig.RangePreset);

        info_Gear_Mage.setText("Magic Preset: " + SetupManager.currentConfig.MagePreset);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Loot!");

        info_Loot_Total.setText("Total Items to Loot: " + SetupManager.currentConfig.ToLoot.length);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Consumables!");

        info_Food_EatAt.setText("HP to Eat At: " + SetupManager.currentConfig.HPToEatAt);

        info_Food_TotalFood.setText("Total Food: " + SetupManager.currentConfig.Food.length);

        info_Food_TotalPots.setText("Total Potions: NULL");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Prayer!");

        info_Prayer_Protect.setText("Protection Prayers: " + SetupManager.currentConfig.ToProtect);

        info_Prayer_Stat.setText("Stat Boosting Prayers: " + SetupManager.currentConfig.ToBoost);

        info_Prayer_Potion.setText("Use Prayer Potions: " + SetupManager.currentConfig.ToPrayerPotion);

        btn_Preset.setText("Jump");
        btn_Preset.addActionListener(this);

        btn_Task.setText("Jump");
        btn_Task.addActionListener(this);

        btn_Gear.setText("Jump");
        btn_Gear.addActionListener(this);

        btn_Loot.setText("Jump");
        btn_Loot.addActionListener(this);

        btn_Food.setText("Jump");
        btn_Food.addActionListener(this);

        btn_Prayer.setText("Jump");
        btn_Prayer.addActionListener(this);

        btn_StartScript.setText("Start Script");
        btn_StartScript.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator7)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Task))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(info_Preset_Name))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(btn_Preset))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(info_Task_Kill)
                                    .addComponent(info_Task_Difficulty)
                                    .addComponent(info_Task_Skip)
                                    .addComponent(info_Gear_Range)
                                    .addComponent(info_Gear_Melee)
                                    .addComponent(info_Gear_Mage)
                                    .addComponent(info_Loot_Total)
                                    .addComponent(info_Food_TotalFood)
                                    .addComponent(info_Food_EatAt)
                                    .addComponent(info_Food_TotalPots)
                                    .addComponent(info_Prayer_Stat)
                                    .addComponent(info_Prayer_Protect)
                                    .addComponent(info_Prayer_Potion))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Gear))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Loot))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Food))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Prayer)))))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_StartScript)
                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(info_Preset_Name))
                    .addComponent(btn_Preset, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btn_Task, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Task_Difficulty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Task_Kill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Task_Skip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btn_Gear, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Gear_Melee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Gear_Range)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Gear_Mage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(btn_Loot, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Loot_Total)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(btn_Food, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Food_EatAt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Food_TotalFood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Food_TotalPots)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(btn_Prayer, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Prayer_Protect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Prayer_Stat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_Prayer_Potion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_StartScript)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Food;
    private javax.swing.JButton btn_Gear;
    private javax.swing.JButton btn_Loot;
    private javax.swing.JButton btn_Prayer;
    private javax.swing.JButton btn_Preset;
    private javax.swing.JButton btn_StartScript;
    private javax.swing.JButton btn_Task;
    private javax.swing.JLabel info_Food_EatAt;
    private javax.swing.JLabel info_Food_TotalFood;
    private javax.swing.JLabel info_Food_TotalPots;
    private javax.swing.JLabel info_Gear_Mage;
    private javax.swing.JLabel info_Gear_Melee;
    private javax.swing.JLabel info_Gear_Range;
    private javax.swing.JLabel info_Loot_Total;
    private javax.swing.JLabel info_Prayer_Potion;
    private javax.swing.JLabel info_Prayer_Protect;
    private javax.swing.JLabel info_Prayer_Stat;
    private javax.swing.JLabel info_Preset_Name;
    private javax.swing.JLabel info_Task_Difficulty;
    private javax.swing.JLabel info_Task_Kill;
    private javax.swing.JLabel info_Task_Skip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;


    public void saveToFile() {
        try {


            File dir = new File(Constants.USER_DIR + "/presets/Ikov/" + SetupManager.currentConfig.presetName + "/");
            if (!dir.exists()){
                dir.mkdirs();
            }

            File taskFile = new File(dir + "/tasks.cfg");
            if (taskFile.exists()) {
                taskFile.delete();
                taskFile.createNewFile();
            }
            File gearFile = new File(dir + "/equipment.cfg");
            if (gearFile.exists()) {
                gearFile.delete();
                gearFile.createNewFile();
            }
            File lootFile = new File(dir + "/loot.cfg");
            if (lootFile.exists()) {
                lootFile.delete();
                lootFile.createNewFile();
            }
            File foodFile = new File(dir + "/consumables.cfg");
            if (foodFile.exists()) {
                foodFile.delete();
                foodFile.createNewFile();
            }
            File prayerFile = new File(dir + "/prayer.cfg");
            if (prayerFile.exists()) {
                prayerFile.delete();
                prayerFile.createNewFile();
            }

            FileWriter fw = new FileWriter(taskFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Difficulty:" + SetupManager.currentConfig.Difficulty);
            bw.newLine();
            bw.write("-- [KILL] --");
            bw.newLine();
            for (String task_Kill : SetupManager.currentConfig.ToKill) {
                bw.write(task_Kill);
                bw.newLine();
            }
            bw.write("-- [SKIP] --");
            bw.newLine();
            for (String task_Skip : SetupManager.currentConfig.ToSkip) {
                bw.write(task_Skip);
                bw.newLine();
            }
            bw.close();

            fw = new FileWriter(gearFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            bw.write("Melee-Preset:" + SetupManager.currentConfig.MeleePreset);
            bw.newLine();
            bw.write("Range-Preset:" + SetupManager.currentConfig.RangePreset);
            bw.newLine();
            bw.write("Mage-Preset:" + SetupManager.currentConfig.MagePreset);
            bw.newLine();
            bw.close();

            fw = new FileWriter(lootFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            if (SetupManager.currentConfig.ToLoot != null) {
                for (String item : SetupManager.currentConfig.ToLoot) {
                    bw.write(item);
                    bw.newLine();
                }
            }
            bw.close();

            fw = new FileWriter(foodFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);


            bw.write("Heal:" + SetupManager.currentConfig.HPToEatAt);
            bw.newLine();
            if (SetupManager.currentConfig.Food != null) {
                for (String item : SetupManager.currentConfig.Food) {
                    bw.write(item);
                    bw.newLine();
                }
            }
            bw.close();

            fw = new FileWriter(prayerFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            bw.write("Protect:" + SetupManager.currentConfig.ToProtect);
            bw.newLine();
            bw.write("Boost:" + SetupManager.currentConfig.ToBoost);
            bw.newLine();
            bw.write("Potion:" + SetupManager.currentConfig.ToPrayerPotion);
            bw.newLine();
            bw.close();

        } catch (IOException ioE) {

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object s = e.getSource();

        if (s.equals(btn_Preset)) {
            new A_Presets().setVisible(true);
            this.dispose();
        } else if (s.equals(btn_Task)) {
            new B_Tasks().setVisible(true);
            A_Presets.tasksWindowOpen = true;
            this.dispose();
        } else if (s.equals(btn_Gear)) {
            B_Tasks.gearWindowOpen = true;
            new C_Equipment().setVisible(true);
            this.dispose();
        } else if (s.equals(btn_Loot)) {
            C_Equipment.lootWindowOpen = true;
            new D_Loot().setVisible(true);
            this.dispose();
        } else if (s.equals(btn_Food)) {
            D_Loot.foodWindowOpen = true;
            new E_Food().setVisible(true);
            this.dispose();
        } else if (s.equals(btn_Prayer)) {
            E_Food.prayerWindowOpen = true;
            new F_Prayer().setVisible(true);
            this.dispose();
        }

        else if (s.equals(btn_StartScript)) {
            this.dispose();
            SetupManager.finished();
        }
    }
    // End of variables declaration//GEN-END:variables
}
