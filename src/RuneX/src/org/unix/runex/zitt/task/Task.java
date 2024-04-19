package org.unix.runex.zitt.task;

import org.unix.runex.zitt.BrimstoneSlayer;
import org.unix.runex.zitt.Constants;
import simple.api.ClientContext;
import simple.api.actions.SimpleItemActions;
import simple.api.actions.SimpleNpcActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.filters.SimplePrayers;
import simple.api.filters.SimpleSkills;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleGroundItem;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimpleNpc;

import java.awt.*;

public abstract class Task {

    public ClientContext c = ClientContext.instance();
    public WorldPoint loc;
    public SimpleNpc currentTarget;

    public WorldPoint houseLoc = new WorldPoint(52, 50, 0);

    public int npcPoolCount = -1;

    public abstract void travelToTask();

    public abstract int getID();

    public abstract String getName();

    public abstract int getFightingStyle(); // 0 = melee, 1 = range, 2 = mage

    public abstract int[] getTeleportInfo();

    public abstract WorldArea getFightingArea();

    public abstract WorldPoint cannonLocation();

    public abstract void debugPaint(Graphics2D g);

    public String[] loot_table = new String[]{
            "Coins",
            "Green charm",
            "Crystal key",
            "Abyssal whip",
            "Abyssal head",
            "Slayer task scroll",
            "Point mystery box",
            "Bag of tokens",
            "Abyssal dagger",
            "Imbued heart",
            "Twisted heart",
            "Sack of riches",
            "Imbuing stone",
            "Grand crest",
            "Dark totem base",
            "Dark totem middle",
            "Dark totem top"
    };


    public int food_id = -1;
    public int[] prayer_id = new int[] { 143, 141, 139, 2434 };


    public void processTask() {
        loc = c.players.getLocal().getLocation();



        if (currentTarget != null && currentTarget.isDead() && currentTarget.getInteracting() == c.players.getLocal()) {
            currentTarget = null;
        }

        if (getFightingArea().containsPoint(loc)) {
            activateRetaliate();
            activatePrayers(-1);
            potup();
            if (BrimstoneSlayer.cannonInInventory) {
                if (cannonLocation().equals(loc)) {
                    c.inventory.populate().filter(6).next().interact(74);
                    c.sleep(7500);
                } else {
                    c.pathing.step(cannonLocation());
                    c.sleep(500);
                }
            }
            if (loot()) { c.sleep(1000); return; }
            try {
                if (c.players.getLocal().getInteracting() != null) {
                    c.sleep(2500);
                } else {
                    getNextTarget().interact(SimpleNpcActions.ATTACK);
                    c.sleep(2500);
                }
            } catch (NullPointerException e) {
                System.out.println("No Enemies To Attack!!!");
            }
        } else {
            if (Constants.HOME_AREA.containsPoint(loc)) {
                if (houseKeeping()) {
                    System.out.println("A");
                    return;
                }
                if (c.inventory.populate().filter(prayer_id[3]).size() < 4 || c.inventory.inventoryFull()) {
                    if (!c.bank.bankOpen()) {
                        c.bank.openBank();
                        c.sleep(5000);
                    } else {
                        c.menuActions.sendAction(169, 21764, 42, 5294);
                        c.sleep(1000);
                        c.menuActions.sendAction(315, 20611, 51, 56023);
                        c.sleep(2500);
                    }
                } else {
                    c.menuActions.sendAction(867, 13280, 1, 1688);
                    c.sleep(1000);
                    c.menuActions.sendAction(315, 4710, 3, 2472);
                    c.sleep(1000);
                    System.out.println("We got here somehow");
                }
            } else if (houseLoc.equals(loc)) {
                c.objects.populate().filter("Kourend portal").next().interact(502);
                c.sleep(5000);
                c.menuActions.sendAction(315, 229474304, 215, 2461);
                c.sleep(2500);
            }
            travelToTask();
        }
    }

    public SimpleNpc getNextTarget() {
        try {
            SimpleEntityQuery<SimpleNpc> fm = c.npcs.populate().filter((n) -> n.getId() == getID() && n.getInteracting() == null && !n.isDead() && !n.isAnimating() && getFightingArea().containsPoint(n.getLocation()));
            npcPoolCount = fm.size();
            SimpleNpc npc = fm.nearest().next();
            if (npc == null || npc.isDead() || npc.getInteracting() != null) {
                npc = fm.nextNearest();
            }
            currentTarget = npc;
            return npc;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return c.npcs.populate().filter(getID()).next();
    }

    public void activateRetaliate() {
        if (!c.combat.autoRetaliate()) {
            c.combat.toggleAutoRetaliate(true);
        }
    }
    public void deactivateRetaliate() {
        if (c.combat.autoRetaliate()) {
            c.combat.toggleAutoRetaliate(false);
        }
    }

    public void potup() {
        if (c.inventory.populate().filter("Vial").size() > 0) {
            for (SimpleItem s : c.inventory.populate().filter("Vial")) {
                s.interact(SimpleItemActions.DROP);
                c.sleep(500);
            }
        }
        if (c.prayers.prayerPercent() <= 50) {
            if (c.inventory.populate().filter(prayer_id[0]).size() > 0) {
                c.inventory.next().interact("Drink");
                c.sleep(250);
            } else if (c.inventory.populate().filter(prayer_id[1]).size() > 0) {
                c.inventory.next().interact("Drink");
                c.sleep(250);
            } else if (c.inventory.populate().filter(prayer_id[2]).size() > 0) {
                c.inventory.next().interact("Drink");
                c.sleep(250);
            } else if (c.inventory.populate().filter(prayer_id[3]).size() > 0) {
                c.inventory.next().interact("Drink");
                c.sleep(250);
            } else {
                if (c.inventory.populate().filter(prayer_id[0]).size() < 1) {
                    c.menuActions.sendAction(315, 0, 0, 12856);
                }
            }
        }
    }

    public void activatePrayers(int override) {

        if (c.prayers.points() <= 0) {
            return;
        }
        if (override == -1) {
            if (getFightingStyle() == 0) {
                if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 42) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE);
                    c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                }
            } else if (getFightingStyle() == 1) {
                if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 39) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES);
                    c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                }
            } else if (getFightingStyle() == 2) {
                if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 36) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC);
                    c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                }
            }
        } else  {
            switch (override) {
                case 0:
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 42) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE);
                        c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                    }
                    break;
                case 1:
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 39) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES);
                        c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                    }
                    break;
                case 2:
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 36) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC);
                        c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT);
                    }
                    break;
            }
        }
    }

    public boolean houseKeeping() {
        c.prayers.disableAll();
        if (c.players.getLocal().getHealth() < c.players.getLocal().getMaxHealth() || c.prayers.prayerPercent() < 100) {
            c.npcs.populate().filter(373).next().interact(20);
            c.sleep(2500);
            System.out.println("YES");
            return true;
        }
        c.pathing.running(true);
        System.out.println("NO");
        return false;
    }

    public boolean loot() {
        SimpleEntityQuery<SimpleGroundItem> fm = null;
        for (String s : loot_table) {
             fm = c.groundItems.populate().filter((n) -> (n.getName().contains(s) && getFightingArea().containsPoint(n.getLocation())));

            for (SimpleGroundItem gi : fm) {
                if (gi != null) {
                    if (gi.distanceTo(loc) < 8) {
                        gi.interact("Take");
                        c.sleep(500);
                        BrimstoneSlayer.totalItemsLooted+=1;
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
