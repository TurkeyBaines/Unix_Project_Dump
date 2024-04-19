/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.B;

import org.unix.slayer.setup.A.A_Presets;
import org.unix.slayer.setup.C.C_Equipment;
import org.unix.slayer.setup.SetupManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class B_Tasks extends JFrame implements ActionListener {

    public static boolean gearWindowOpen = false;

    public B_Tasks() {
        setLocationRelativeTo(null);
        initComponents();
    }

    public String[] easyTasks = new String[]{
            "Banshee", "Cave Crawler", "Chicken", "Cockatrice", "Crawling Hand", "Hill Giant", "Infernal Mage", "Pyrefiend", "Rock Crab", "Rock Slug", "Yak"
    };

    public String[] medTasks = new String[]{
            "Abyssal Demon", "Aviansie", "Basilisk", "Bloodveld", "Cave Horror", "Dark Beasts", "Dust Devil", "Experiments", "Fire Giant", "Gargoyle", "Jelly", "Kurask", "Lesser Demon", "Nechryaels", "Skeletal Wyvern", "Smoke Devil", "Spitting Wyvern", "Turoth", "Wyrm"
    };

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        combi_Difficulty = new JComboBox<>();
        jScrollPane1 = new JScrollPane();
        list_Kill = new JList<>();
        list_Kill_Model = new DefaultListModel();
        jScrollPane2 = new JScrollPane();
        list_Skip = new JList<>();
        list_Skip_Model = new DefaultListModel();
        btn_MoveToKill = new JButton();
        btn_MoveToSkip = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        btn_Back = new JButton();
        btn_Next = new JButton();



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Please select a Task Difficulty:");

        combi_Difficulty.setModel(new DefaultComboBoxModel<>(new String[] { "Easy", "Medium"/*, "Hard", "Brimstone", "Boss"*/ }));
        combi_Difficulty.addActionListener(this);
        if (SetupManager.currentConfig.Difficulty != -1) {
            combi_Difficulty.setSelectedIndex(SetupManager.currentConfig.Difficulty);
            list_Kill_Model.removeAllElements();
            list_Skip_Model.removeAllElements();
            for(String s : SetupManager.currentConfig.ToKill) {
                list_Kill_Model.addElement(s);
            }
            for (String s : SetupManager.currentConfig.ToSkip) {
                list_Skip_Model.addElement(s);
            }
            combi_Difficulty.updateUI();
            list_Kill.updateUI();
            list_Skip.updateUI();
            System.out.println("We found an existing file and used it");
        } else {
            for (String s : easyTasks) {
                list_Kill_Model.addElement(s);
            }
            list_Kill.updateUI();
        }

        list_Kill.setModel(list_Kill_Model);
        jScrollPane1.setViewportView(list_Kill);

        list_Skip.setModel(list_Skip_Model);
        jScrollPane2.setViewportView(list_Skip);

        btn_MoveToKill.setText("<<");
        btn_MoveToKill.addActionListener(this);

        btn_MoveToSkip.setText(">>");
        btn_MoveToSkip.addActionListener(this);


        jLabel2.setText("Kill");

        jLabel3.setText("Skip");

        btn_Back.setText("Back");
        btn_Back.addActionListener(this);

        btn_Next.setText("Next");
        btn_Next.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(btn_MoveToKill, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(btn_MoveToSkip, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(combi_Difficulty, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel3)
                                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btn_Next))))
                                        .addComponent(jLabel2)
                                        .addComponent(btn_Back))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(combi_Difficulty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btn_MoveToKill, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_MoveToSkip, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_Back, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_Next, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btn_Back;
    private JButton btn_MoveToKill;
    private JButton btn_MoveToSkip;
    private JButton btn_Next;
    private JComboBox<String> combi_Difficulty;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList<String> list_Kill;
    private DefaultListModel list_Kill_Model;
    private JList<String> list_Skip;
    private DefaultListModel list_Skip_Model;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        try {

            if (s.equals(combi_Difficulty) && e.getActionCommand().equalsIgnoreCase("comboboxchanged")) {
                list_Kill_Model.removeAllElements();
                list_Skip_Model.removeAllElements();
                if (combi_Difficulty.getSelectedIndex() == 0) { // EASY
                    for (String mobs : easyTasks) {
                        list_Kill_Model.addElement(mobs);
                    }
                    list_Kill.updateUI();
                    list_Skip.updateUI();
                } else if (combi_Difficulty.getSelectedIndex() == 1) { // MEDIUM
                    for (String mobs : medTasks) {
                        list_Kill_Model.addElement(mobs);
                    }
                    list_Kill.updateUI();
                    list_Skip.updateUI();
                } else {
                    return;
                }
            } else if (s.equals(btn_MoveToSkip)) {
                String mob = list_Kill_Model.getElementAt(list_Kill.getSelectedIndex()).toString();
                list_Kill_Model.removeElement(mob);
                list_Skip_Model.addElement(mob);
                list_Kill.updateUI();
                list_Skip.updateUI();
            } else if (s.equals(btn_MoveToKill)) {
                String mob = list_Skip_Model.getElementAt(list_Skip.getSelectedIndex()).toString();
                list_Skip_Model.removeElement(mob);
                list_Kill_Model.addElement(mob);
                list_Kill.updateUI();
                list_Skip.updateUI();
            } else if (s.equals(btn_Back)) {
                new A_Presets().setVisible(true);
                this.dispose();
                A_Presets.tasksWindowOpen = false;
            } else if (s.equals(btn_Next)) {
                if (!gearWindowOpen) {
                    String[] toKill = new String[list_Kill_Model.size()];
                    String[] toSkip = new String[list_Skip_Model.size()];
                    for (int i = 0; i < list_Kill_Model.size(); i++) {
                        toKill[i] = list_Kill_Model.getElementAt(i).toString();
                    }
                    for (int i = 0; i < list_Skip_Model.size(); i++) {
                        toSkip[i] = list_Skip_Model.getElementAt(i).toString();
                    }
                    SetupManager.currentConfig.ToKill = null;
                    SetupManager.currentConfig.ToKill = toKill;
                    SetupManager.currentConfig.ToSkip = null;
                    SetupManager.currentConfig.ToSkip = toSkip;
                    SetupManager.currentConfig.Difficulty = combi_Difficulty.getSelectedIndex();
                    new C_Equipment().setVisible(true);
                    this.dispose();
                    A_Presets.tasksWindowOpen = false;
                    gearWindowOpen = true;
                }
            }
        } catch (Exception exception) {exception.printStackTrace();}
    }
    // End of variables declaration//GEN-END:variables
}
