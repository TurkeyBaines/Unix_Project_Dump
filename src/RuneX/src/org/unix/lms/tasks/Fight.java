package org.unix.lms.tasks;

import org.unix.lms.Constants;
import simple.api.filters.SimplePrayers;
import simple.api.queries.SimpleItemQuery;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimplePlayer;

import java.util.Random;

public class Fight extends Task {

    Opponent e;
    int food[] = new int[] {385};
    int comboF[] = new int[] {3144};
    int potions[] = new int[] {6685, 3024, 2444, 42695, 10925};
    int magic_gear[];
    int range_gear[] = new int[] {9185, 2503, 1079};
    int melee_gear[] = new int[] {4151, 1079, 20072};
    int spec_weapon[] = new int[] {5698};
    int style = 2;
    Random r;

    @Override
    public boolean execute() {
        return c.players.getLocal().inCombat();
    }

    @Override
    public void run() {
        if (r == null) { r = new Random(System.currentTimeMillis()); }
        if (e == null) {

            try {
                e = new Opponent(c.players.populate().filter((n) -> n.getInteracting() != null && n.inCombat() && n.getInteracting().equals(c.players.getLocal())).nearest().next());
            } catch (Exception e) {
                return;
            }

        }

        // if we're praying wrong for their weapon
        checkPrayers();


        // if e is holding a spec weapon (ags, gmaul, dds etc) then change eat food then change prayers

        if (c.combat.healthPercent() < 50) {
            if (e.usingSpecWeapon()) {
                eatFood(true);
            } else {
                eatFood(false);
            }
        }

        // if we need to pot (poison, venom, stat boosts < 75%)


        // now we attack
            // if they're praying vs our attack style then we should change styles
            // if they're below 50% hp and wearing mage robes, spec them
            // barrage if available, start a timer if we get above the base xp. if timer > 20s then reset and barrage again when off mage prayer
            // use melee instead of mage if they close, step away if they are closing gap to you

        switchGear();


    }

    public void eatFood(boolean combo) {
        if (combo) {
            SimpleItem i = c.inventory.populate().filter(food).next();
            if (i != null) {
                i.interact("Eat");
            }
            i = c.inventory.populate().filter(comboF).next();
            if (i != null) {
                i.interact("Eat");
            }
        } else {
            SimpleItem i = c.inventory.populate().filter(food).next();
            if (i != null) {
                i.interact("Eat");
            }
        }
    }

    public void equip(int style) {
        switch (style) {
            case 0:
                SimpleItemQuery<SimpleItem> smelee = c.inventory.populate().filter(this.melee_gear);
                for (SimpleItem i : smelee) {
                    c.menuActions.sendAction(454, i.getId(), i.getSlot(), 3214);
                    c.sleep(50);
                }
                style = 0;
                break;

            case 1:
                SimpleItemQuery<SimpleItem> srange = c.inventory.populate().filter(this.range_gear);
                for (SimpleItem i : srange) {
                    c.menuActions.sendAction(454, i.getId(), i.getSlot(), 3214);
                    c.sleep(50);
                }
                style = 1;
                break;

            case 2:
                SimpleItemQuery<SimpleItem> smage = c.inventory.populate().filter(this.magic_gear);
                for (SimpleItem i : smage) {
                    c.menuActions.sendAction(454, i.getId(), i.getSlot(), 3214);
                    c.sleep(50);
                }
                style = 2;
                break;
        }
    }

    public void switchGear() {
        if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES)) {
            if (style == 1) {
                Random r = new Random();
                if (r.nextInt(2) == 1) {
                    equip(2);
                } else {
                    equip(0);
                }
            }
        } else if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MELEE)) {
            if (style == 0) {
                Random r = new Random();
                if (r.nextInt(2) == 1) {
                    equip(2);
                } else {
                    equip(1);
                }
            }
        } else if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
            if (style == 2) {
                Random r = new Random();
                if (r.nextInt(2) == 1) {
                    equip(1);
                } else {
                    equip(0);
                }
            }
        }
    }

    public void checkPrayers() {
        if (c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
            if (!e.usingMage()) {
                if (e.usingMelee() || e.usingSpecWeapon()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE);
                } else if (e.usingRange()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES);
                }
            }
        } else if (c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES)) {
            if (!e.usingRange()) {
                if (e.usingMelee() || e.usingSpecWeapon()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE);
                } else if (e.usingMage()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC);
                }
            }
        } else if (c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MELEE)) {
            if (!e.usingMelee() || !e.usingSpecWeapon()) {
                if (e.usingMage()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC);
                } else if (e.usingRange()) {
                    c.sleep(Constants.GAME.HUMAN);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES);
                }
            }
        }
    }

    public boolean triggerSpec() {
        switch (melee_gear[2]) {
            case 5698:
                if (c.combat.getSpecialAttackPercentage() < 25) { return false; }
                if (e.gear[3] == magic_gear[3] && e.gear[5] == magic_gear[5]) {
                    if (e.getOpp().getHealthRatio() < 50) {
                        if (r.nextInt(100) < 40) {
                            return true;
                        }
                    }
                }
        }
        return false;
    }
}
