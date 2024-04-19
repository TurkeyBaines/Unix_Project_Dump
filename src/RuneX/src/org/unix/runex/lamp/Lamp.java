package org.unix.lamp;

import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@ScriptManifest(category = Category.OTHER, author = "unix", version = "0.01", discord = "", servers = { "RuneX" }, description = "Uses Lamps on requested skill", name = "uLamp")
public class Lamp extends Script implements SimplePaintable {

    LAMP selected;
    boolean waiting;

    @Override
    public void onProcess() {
        if (waiting) { return; }
        if (ctx.bank.bankOpen()) {
            if (ctx.inventory.populate().filter(2528).size() < 28 && ctx.inventory.populate().filter(2528).size() > 0) {
                ctx.menuActions.sendAction(646, 0, 0, 26012);
                ctx.sleep(1000);
            } else if (ctx.inventory.populate().size() == 0) {
                ctx.menuActions.sendAction(431, 2528, 7, 5382);
            } else {
                ctx.bank.closeBank();
            }
            return;
        }
        if (ctx.inventory.populate().filter(2528).size() > 0) {
            for (int i = 0; i < 7; i++) {
                ctx.menuActions.sendAction(315, 0, 0, selected.child);
                ctx.menuActions.sendAction(315, 0, 0, 2831);
            }

        } else {
            ctx.bank.openBank();
        }
    }

    @Override
    public boolean onExecute() {
        selected = LAMP.SMITHING;
        waiting = true;
        new GUI();
        return true;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onPaint(Graphics2D graphics2D) {

    }

    public enum LAMP {

        ATTACK(2808, 2812, "Attack"),
        STRENGTH(2808, 2813, "Strength"),
        RANGE(2808, 2814, "Range"),
        MAGIC(2808, 2815, "Magic"),
        DEFENCE(2808, 2816, "Defence"),
        HP(2808, 2817, "Hitpoints"),
        PRAYER(2808, 2818, "Prayer"),
        AGILITY(2808, 2819, "Agility"),
        HERBLORE(2808, 2820, "Herblore"),
        THIEVING(2808, 2821, "Thieving"),
        CRAFTING(2808, 2822, "Crafting"),
        RUNECRAFT(2808,2823, "Runecrafting"),
        SLAYER(2808, 12034, "Slayer"),
        FARMING(2808, 13914, "Farming"),
        MINING(2808, 2824, "Mining"),
        SMITHING(2808, 2825, "Smithing"),
        FISHING(2808, 2826, "Fishing"),
        COOKING(2808, 2827, "Cooking"),
        FIREMAKING(2808, 2828, "Firemaking"),
        WOODCUTTING(2808, 2829, "Woodcutting"),
        FLETCHING(2808, 2830, "Fletching"),
        HUNTER(2808, 18558, "hunter"),
        CONSTRUCTION(2808, 18562, "construction"),
        SUMMONING(2808, 10517, "summoning");

        LAMP(int PARENT, int CHILD, String INTERACTION) { parent = PARENT; child = CHILD; inter = INTERACTION; }

        int parent, child;
        String inter;

        int getParent() { return parent; }
        int getChild() { return child; }
        String getUse() { return inter; }
    }

    public String[] getLampSkillNames() {
        return new String[] {"Attack", "Strength", "Range", "Magic", "Defence", "Hitpoints", "Prayer", "Agility", "Herblore", "Thieving", "Crafting", "Runecrafting", "Slayer", "Farming", "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting", "Fletching", "Hunter", "Construction", "Summoning", };
    }

    public class GUI extends JFrame {

        /**
         * Creates new form GUI3
         */
        public GUI() {
            initComponents();
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setVisible(true);
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            BOX_Selection = new javax.swing.JComboBox<>();
            BTN_Go = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            BOX_Selection.setModel(new javax.swing.DefaultComboBoxModel<>(getLampSkillNames()));

            BTN_Go.setText("GO");
            BTN_Go.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected = LAMP.values()[BOX_Selection.getSelectedIndex()];
                    waiting = false;
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(BOX_Selection, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(53, 53, 53)
                                                    .addComponent(BTN_Go)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(BOX_Selection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                    .addComponent(BTN_Go)
                                    .addContainerGap())
            );

            pack();
        }
        private javax.swing.JComboBox<String> BOX_Selection;
        private javax.swing.JButton BTN_Go;
    }

}
