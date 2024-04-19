/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.unix.slayer.setup.D;

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

    public AddItem(D_Loot Source) {
        source = Source;
        setLocationRelativeTo(source);
        initComponents();
    }

    boolean validated = false;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Item ID:");

        in_ID.setText("");

        jLabel2.setText("Item Name:");

        in_Name.setText("");
        in_Name.setEditable(false);

        btn_Validate.setText("Validate");
        btn_Validate.addActionListener(this);

        info_ValidationResult.setText("Item: No Item Validated Yet");

        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(this);

        btn_Add.setText("Add");
        btn_Add.addActionListener(this);

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_Cancel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Add))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(in_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(in_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(info_ValidationResult))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
    private javax.swing.JTextField in_ID;
    private javax.swing.JTextField in_Name;
    private javax.swing.JLabel info_ValidationResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private D_Loot source;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();

         if (s.equals(btn_Cancel)) {
            this.dispose();
            source.newPresetWindowOpen = false;
        } else if (s.equals(btn_Add)) {
             source.addNewItem(Integer.parseInt(in_ID.getText()));
             source.newPresetWindowOpen = false;
             this.dispose();
        } else if (s.equals(btn_Validate)) {
            try {
                if (!in_ID.getText().isEmpty()) {
                    int id = Integer.parseInt(in_ID.getText());
                    SimpleItem i = new SimpleItem(ClientContext.instance(), id, 1);
                    if (i != null) {
                        in_Name.setText(i.getName());
                        info_ValidationResult.setText("Item Validated!");
                        validated = true;
                    }
                }
            } catch (Exception exception) { info_ValidationResult.setText("Item: Error Loading Item, Try Again!"); }
        }
    }
    // End of variables declaration//GEN-END:variables
}
