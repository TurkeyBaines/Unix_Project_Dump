/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.utility;

import org.unix.utility.area.AreaGen;
import org.unix.utility.object.GUI;
import org.unix.utility.path.PathGen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class MainGUI extends JFrame implements ActionListener {

    public MainGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jSeparator1 = new JSeparator();
        btn_Area = new JButton();
        btn_Path = new JButton();
        btn_Npc = new JButton();
        btn_Obj = new JButton();
        btn_Item = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("uTilities by unix");
        setResizable(false);

        jLabel1.setText("Welcome to uTility!");

        jLabel2.setText("Please select you option");

        btn_Area.setText("Area Generator");
        btn_Area.addActionListener(this);

        btn_Path.setText("Path Creator");
        btn_Path.addActionListener(this);

        btn_Npc.setText("NPC Search");
        btn_Npc.addActionListener(this);

        btn_Obj.setText("Object Search");
        btn_Obj.addActionListener(this);

        btn_Item.setText("Item Search");
        btn_Item.addActionListener(this);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(50, 50, 50))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Area, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Path, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Npc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Obj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Item, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Area)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Path)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Npc)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Obj)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Item)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btn_Area;
    private JButton btn_Item;
    private JButton btn_Npc;
    private JButton btn_Obj;
    private JButton btn_Path;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JSeparator jSeparator1;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_Area)) {
            new AreaGen().setVisible(true);
        } else if (o.equals(btn_Path)) {
            new PathGen().setVisible(true);
        } else if (o.equals(btn_Obj)) {
            new GUI().setVisible(true);
        } else if (o.equals(btn_Item)) {
            new org.unix.utility.item.GUI().setVisible(true);
        } else if (o.equals(btn_Npc)) {
            new org.unix.utility.npc.GUI().setVisible(true);
        }
    }

}
