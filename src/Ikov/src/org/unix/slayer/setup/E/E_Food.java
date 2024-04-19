/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.E;

import org.unix.slayer.setup.C.C_Equipment;
import org.unix.slayer.setup.D.D_Loot;
import org.unix.slayer.setup.F.F_Prayer;
import org.unix.slayer.setup.SetupManager;
import simple.api.ClientContext;
import simple.api.wrappers.SimpleItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 0x181
 */
public class E_Food extends JFrame implements ActionListener {

    public boolean newPresetWindowOpen = false;
    public static boolean prayerWindowOpen = false;

    public E_Food() {
        setLocationRelativeTo(null);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        list_Consumables = new JList<>();
        btn_Remove = new JButton();
        btn_AddNew = new JButton();
        btn_Back = new JButton();
        btn_Next = new JButton();
        jLabel2 = new JLabel();
        in_EatAt = new JTextField();
        jLabel3 = new JLabel();
        list_Food_Model = new DefaultListModel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Add Consumables to the list such as Food and Potions");

        list_Consumables.setModel(list_Food_Model);
        jScrollPane1.setViewportView(list_Consumables);
        if (SetupManager.currentConfig.Food != null) {
            list_Food_Model.removeAllElements();
            for (String f : SetupManager.currentConfig.Food) {
                list_Food_Model.addElement(f);
            }
            list_Consumables.updateUI();
        }

        btn_Remove.setText("Remove Item");
        btn_Remove.addActionListener(this);

        btn_AddNew.setText("Add New Item");
        btn_AddNew.addActionListener(this);

        btn_Back.setText("Back");
        btn_Back.addActionListener(this);

        btn_Next.setText("Next");
        btn_Next.addActionListener(this);

        jLabel2.setText("Eat At:");

        in_EatAt.setText("30");
        if (SetupManager.currentConfig.HPToEatAt != -1) {
            in_EatAt.setText("" + SetupManager.currentConfig.HPToEatAt);
            in_EatAt.updateUI();
        }

        jLabel3.setText("HP");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(btn_Remove)
                                .addComponent(btn_Back)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_AddNew, GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_Next, GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(in_EatAt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(in_EatAt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Back, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Next, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void addNewItem(int type, int id) {
        if (type == 0) {
            SimpleItem si = new SimpleItem(ClientContext.instance(), id, 1);
            String name = si.getName();
            list_Food_Model.addElement("" + id + " - " + name);
            food.add("" + id + " - " + name);
        } else {
            SimpleItem si = new SimpleItem(ClientContext.instance(), id, 1);
            String name = si.getName();
            list_Food_Model.addElement("" + id + " - " + name);
            potions.add("" + id + " - " + name);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btn_AddNew;
    private JButton btn_Back;
    private JButton btn_Next;
    private JButton btn_Remove;
    private JTextField in_EatAt;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JList<String> list_Consumables;
    private DefaultListModel list_Food_Model;
    private List food = new ArrayList<String>();
    private List potions = new ArrayList<String>();

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s.equals(btn_AddNew)) {
            new AddItem(this).setVisible(true);
        } else if (s.equals(btn_Remove)) {
            try {
                food.remove(list_Food_Model.getElementAt(list_Consumables.getSelectedIndex()));
                potions.remove(list_Food_Model.getElementAt(list_Consumables.getSelectedIndex()));
            } catch (Exception error) {}
            list_Food_Model.removeElementAt(list_Consumables.getSelectedIndex());
            list_Consumables.updateUI();

        } else if (s.equals(btn_Back)) {
            new D_Loot().setVisible(true);
            this.dispose();
            D_Loot.foodWindowOpen = false;
            C_Equipment.lootWindowOpen = true;
        } else if (s.equals(btn_Next)) {
            if (!prayerWindowOpen) {
                String[] Afood = new String[food.size()];
                for (int i = 0; i < food.size(); i++) {
                    Afood[i] = food.get(i).toString().split(" ")[0];
                }
                SetupManager.currentConfig.Food = Afood;
                SetupManager.currentConfig.HPToEatAt = Integer.parseInt(in_EatAt.getText());
                new F_Prayer().setVisible(true);
                this.dispose();
                prayerWindowOpen = true;
                D_Loot.foodWindowOpen = false;
            }
        }
    }

    // End of variables declaration//GEN-END:variables
}
