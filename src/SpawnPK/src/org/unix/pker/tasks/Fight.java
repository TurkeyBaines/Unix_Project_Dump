package org.unix.pker.tasks;

import org.unix.pker.Constants;
import simple.api.filters.SimplePrayers;
import simple.api.queries.SimpleEntityQuery;
import simple.api.queries.SimpleItemQuery;
import simple.api.queries.SimplePlayerQuery;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimplePlayer;

import java.util.Random;

public class Fight extends Task {

    Opponent e;
    int food[] = new int[] {391};
    int comboF[] = new int[] {6685};
    int potions[] = new int[] {2440, 2436, 3040};
    int magic_gear[] = new int[] {4675, 4712, 4714, 2414};
    int range_gear[] = new int[] {9185, 4736, 4759, 10499};
    int melee_gear[] = new int[] {5698, 4736, 4759};
    int spec_weapon[] = new int[] {5698};
    int style = 2;
    Random r;

    @Override
    public boolean execute() {
        return c.players.getLocal().inCombat();
    }

    @Override
    public void run() {
        System.out.println("[Fight.java<run()>]");
        if (r == null) { r = new Random(System.currentTimeMillis()); }
        if (e == null) {
            System.out.println("[Fight.java<run()>] - Enemy is null, trying to grab now... ");

            SimplePlayerQuery<SimplePlayer> seq = c.players.populate().filter((n) -> n != null && n.getInteracting() != null && n.getInteracting().equals(c.players.getLocal()));
            System.out.println("[Fight.java<run()>] seq: " + seq.size());
            if (seq.isEmpty())
                return;
            e = new Opponent(seq.next());
            System.out.println("[Fight.java<run()] We found an Opponent: " + e.getOpp().getName());
            return;
        } else {
            e.update();
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

        if (e != null)
            switchGear();

        if (triggerSpec()) {
            c.menuActions.sendAction(1107, 0, 0, 0);
            c.sleep(250);
            c.menuActions.sendAction(315, 149, 510, 7562);
            c.sleep(250);
        }

        if (style == 2) {
            c.menuActions.sendAction(626, 149, 0, 12891);
            c.sleep(250);
            e.getOpp().interact(365);
            c.sleep(250);
        } else {
            e.getOpp().interact(2027);
            c.sleep(250);
        }

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
        if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES) || e.activePrayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MISSILE)) {
            if (style == 1) {
                Random r = new Random();
                if (r.nextInt(2) == 1) {
                    equip(2);
                } else {
                    equip(0);
                }
            }
        } else if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MELEE) || e.activePrayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MELEE)) {
            if (style == 0) {
                Random r = new Random();
                if (r.nextInt(2) == 1) {
                    equip(2);
                } else {
                    equip(1);
                }
            }
        } else if (e.activePrayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC) || e.activePrayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
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
        System.out.println("[Fight.java<checkPrayers()>] - checking we are praying correct");
        if (c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
            System.out.println("\t\t[We are Praying Mage]");
            if (!e.usingMage()) {
                if (e.usingMelee() || e.usingSpecWeapon()) {
                    System.out.println("\t\t[Enemy is using melee!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Melee Protect");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MELEE);
                } else if (e.usingRange()) {
                    System.out.println("\t\t[Enemy is using range!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Range Protect");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MISSILE);
                }
            }
        } else if (c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MISSILE)) {
            System.out.println("\t\t[We are Praying Range]");
            if (!e.usingRange()) {
                if (e.usingMelee() || e.usingSpecWeapon()) {
                    System.out.println("\t\t[Enemy is using melee!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Melee Protect]");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MELEE);
                } else if (e.usingMage()) {
                    System.out.println("\t\t[Enemy is using mage!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Mage Protect]");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC);
                }
            }
        } else if (c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MELEE)) {
            System.out.println("\t\t[We are Praying Melee]");
            if (!e.usingMelee() || !e.usingSpecWeapon()) {
                if (e.usingMage()) {
                    System.out.println("\t\t[Enemy is using mage!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Mage Protect]");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC);
                } else if (e.usingRange()) {
                    System.out.println("\t\t[Enemy is using range!]");
                    c.sleep(Constants.GAME.HUMAN);
                    System.out.println("\t\t[Switching to Range Protect");
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MISSILE);
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
