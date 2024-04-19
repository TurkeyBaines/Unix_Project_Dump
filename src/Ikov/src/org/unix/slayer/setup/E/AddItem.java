/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.E;

import org.unix.slayer.setup.SetupManager;
import simple.api.ClientContext;
import simple.api.wrappers.SimpleItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 0x181
 */
public class AddItem extends javax.swing.JFrame implements ActionListener {


    boolean validated = false;

    public AddItem(E_Food Source) {
        source = Source;
        setLocationRelativeTo(source);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        in_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        in_Name = new javax.swing.JTextField();
        btn_Validate = new javax.swing.JButton();
        info_ValidationResult = new javax.swing.JLabel();
        btn_Cancel = new javax.swing.JButton();
        btn_Add = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        combi_Type = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Item ID:");

        in_ID.setText("");

        jLabel2.setText("Item Name:");

        in_Name.setText("");

        btn_Validate.setText("Validate");
        btn_Validate.addActionListener(this);

        info_ValidationResult.setText("Item: No Item Validated Yet");

        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(this);

        btn_Add.setText("Add");
        btn_Add.addActionListener(this);

        jLabel3.setText("Item Type:");

        combi_Type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Food", "Potion" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btn_Validate))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btn_Cancel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_Add))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(info_ValidationResult)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel3)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jLabel2)))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(in_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                                        .addComponent(in_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                                                        .addComponent(combi_Type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(combi_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(in_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(in_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_Validate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(info_ValidationResult)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_Validate;
    private javax.swing.JComboBox<String> combi_Type;
    private javax.swing.JTextField in_ID;
    private javax.swing.JTextField in_Name;
    private javax.swing.JLabel info_ValidationResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private E_Food source;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

        if (s.equals(btn_Validate)) {
            try {
                if (!in_ID.getText().isEmpty()) {
                    int id = Integer.parseInt(in_ID.getText());
                    SimpleItem i = new SimpleItem(ClientContext.instance(), id, 1);
                    if (i != null) {
                        in_Name.setText(i.getName());
                        info_ValidationResult.setText("Item validated!");
                        validated = true;
                    }
                }
            } catch (Exception exception) { info_ValidationResult.setText("Item: Error Loading Item, Try Again!"); }
        } else if (s.equals(btn_Cancel)) {
            source.newPresetWindowOpen = false;
            this.dispose();
        } else if (s.equals(btn_Add)) {
            source.addNewItem(combi_Type.getSelectedIndex(), Integer.parseInt(in_ID.getText()));
            this.dispose();
            source.newPresetWindowOpen = false;
        }
    }
    // End of variables declaration//GEN-END:variables
}
