/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.D;

import org.unix.slayer.setup.B.B_Tasks;
import org.unix.slayer.setup.C.C_Equipment;
import org.unix.slayer.setup.E.E_Food;
import org.unix.slayer.setup.SetupManager;
import simple.api.ClientContext;
import simple.api.wrappers.SimpleItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class D_Loot extends JFrame implements ActionListener {

    public boolean newPresetWindowOpen = false;
    public static boolean foodWindowOpen = false;

    public D_Loot() {
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        list_LootItems = new JList<>();
        btn_Remove = new JButton();
        btn_AddNew = new JButton();
        btn_Back = new JButton();
        btn_Next = new JButton();
        list_Loot_Model = new DefaultListModel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Add items to the list you would like the script to collect");

        list_LootItems.setModel(list_Loot_Model);
        jScrollPane1.setViewportView(list_LootItems);
        if (SetupManager.currentConfig.ToLoot != null) {
            list_Loot_Model.removeAllElements();
            for (String s : SetupManager.currentConfig.ToLoot) {
                list_Loot_Model.addElement(s);
            }
            list_LootItems.updateUI();
        }

        btn_Remove.setText("Remove Item");
        btn_Remove.addActionListener(this);

        btn_AddNew.setText("Add New Item");
        btn_AddNew.addActionListener(this);

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Remove)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_AddNew)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Back)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Next)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Remove)
                    .addComponent(btn_AddNew))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Back, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Next, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btn_AddNew;
    private JButton btn_Back;
    private JButton btn_Next;
    private JButton btn_Remove;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JList<String> list_LootItems;
    private DefaultListModel list_Loot_Model;

    public void addNewItem(int id) {
        SimpleItem si = new SimpleItem(ClientContext.instance(), id, 1);
        String name = si.getName();
        list_Loot_Model.addElement("" + id + " - " + name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s.equals(btn_AddNew)) {
            if (!newPresetWindowOpen) {
                new AddItem(this).setVisible(true);
                newPresetWindowOpen = true;
            }
        } else if (s.equals(btn_Remove)) {
            list_Loot_Model.removeElementAt(list_LootItems.getSelectedIndex());
            list_LootItems.updateUI();
        } else if (s.equals(btn_Back)) {
            new C_Equipment().setVisible(true);
            this.dispose();
            B_Tasks.gearWindowOpen = true;
            C_Equipment.lootWindowOpen = false;
        } else if (s.equals(btn_Next)) {
            if (!foodWindowOpen) {
                String[] toLoot = new String[list_Loot_Model.size()];
                for (int i = 0; i < list_Loot_Model.size(); i++) {
                    toLoot[i] = list_Loot_Model.getElementAt(i).toString().split(" ")[0];
                }
                SetupManager.currentConfig.ToLoot = toLoot;
                new E_Food().setVisible(true);
                this.dispose();
                foodWindowOpen = true;
                C_Equipment.lootWindowOpen = false;
            }
        }

    }
    // End of variables declaration//GEN-END:variables
}
