package org.unix.slayer.tasks;

import org.unix.slayer.Constants;
import simple.api.ClientContext;
import simple.api.actions.SimpleNpcActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.filters.SimplePrayers;
import simple.api.filters.SimpleSkills;
import simple.api.queries.SimpleEntityQuery;
import simple.api.queries.SimpleItemQuery;
import simple.api.wrappers.SimpleGroundItem;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimpleNpc;

public abstract class Task {

    public TaskHandler th;

    public ClientContext c = ClientContext.instance();
    public WorldPoint loc;
    public SimpleNpc currentTarget;

    public boolean skip = false;
    public int taskRemaining = -1;
    public int taskTotal = -1;
    public boolean restored = false;

    public abstract void travel();
    public abstract int getId();
    public abstract String getName();
    public abstract int getStyle();
    public abstract boolean useProtPray();
    public abstract WorldArea getBounds();
    public abstract int forcedStyle(); // | -1=No | 0=Melee | 1=Range | 2=Mage |
    public abstract void tpToTask();

    boolean bankPresetGrabbed = false;

    public Task(TaskHandler taskHandler) {
        th = taskHandler;
    }

    public void process() {
        loc = c.players.getLocal().getLocation();
        System.out.println("\t [process()]");
        if (Constants.HOME_AREA.containsPoint(loc)) {
            System.out.println("\t\t [We Are At Home]");
            // we are at home, we should do the following:
                /*
                    check the preset we have on is required for the task (i.e. forced loadout required)
                    check our health/prayer/run and use housekeeping() if they are depleted
                    teleport out to the task
                 */

            //grab preset from bank
            if (!bankPresetGrabbed) {
                if (forcedStyle() == -1) {bankPresetGrabbed = true; return;}
                System.out.println("\t\t\t[Bank Preset Not Yet Grabbed]");
                if (c.bank.bankOpen()) {
                    System.out.println("\t\t\t[Bank Open]");
                    grabBankPreset(forcedStyle());
                } else {
                    System.out.println("\t\t\t[Need To Open Bank]");
                    c.bank.openBank();
                    c.sleep(1000);
                }
                return;
            }

            // restore stats at home if needed
            if (!restored) {
                System.out.println("\t\t\t[Need Housekeeping]");
                houseKeeping();
                c.sleep(4000);
                restored = true;
                return;
            }

            // finally teleport to task
            System.out.println("\t\t\t[Traveling to Task]");
            travel();
        }

        if (getBounds().containsPoint(loc)) {
            System.out.println("\t\t [getBounds()] We are in the Tasks getBounds()");
            // we are in the correct area, we should:
                /*
                    reset the preset check boolean back to false for next task/banking trip
                    check if we need to eat
                    check if we need to drink a prayer potion
                    if not in combat:
                        - check for loot in the surrounding area
                        - check if we need to drink a stat boost potion
                        - get nearest monster -> attack
                 */

            if (bankPresetGrabbed || restored) {  bankPresetGrabbed = false; restored = false; }

            activatePrayers();
            activateRetaliate();

            System.out.println("\t\t\t [EATING=" + Constants.EATING + "]");
            if (Constants.EATING) {
                SimpleItemQuery<SimpleItem> fm = c.inventory.populate().filter(Constants.FOOD_IDS);
                if (c.combat.health() < Constants.EAT_AT) {
                    System.out.println("\t\t\t\t [Constants.EATING] We need to Eat Food!");
                    if (fm.isEmpty()) {
                        System.out.println("\t\t\t\t [Constants.EATING] We are out of Food! Going Home...");
                        c.menuActions.sendAction(315, 0, 0, 12856);
                        c.sleep(2500);
                    } else  {
                        fm.next().interact(74);
                    }
                    c.sleep(750);
                    return;
                }
            }

            System.out.println("\t\t\t [PRAY_POTION=" + Constants.PRAY_POTION + "]");
            if (Constants.PRAY_POTION) {
                if (c.prayers.prayerPercent() < 50) {
                    System.out.println("\t\t\t\t [Constants.PRAY_POTION] We need to Drink a Prayer Potion");
                    SimpleItemQuery<SimpleItem> fm = c.inventory.populate().filter(2434, 139, 141, 143);
                    if (fm.isEmpty()) {
                        System.out.println("\t\t\t\t[Constants.PRAY_POTION] we are out of prayer potions... going home!");
                        c.menuActions.sendAction(315, 0, 0, 12856);
                        c.sleep(2500);
                    } else {
                        fm.next().interact(74);
                    }
                    c.sleep(750);
                    return;
                }
            }

            System.out.println("\t\t\t [LOOT_ENABLE=" + Constants.LOOT_ENABLE + "]");
            if (Constants.LOOT_ENABLE) {
                SimpleEntityQuery<SimpleGroundItem> fm = c.groundItems.populate().filter(Constants.LOOT_IDS);
                if (!fm.isEmpty()) {
                    System.out.println("\t\t\t\t [Constants.LOOT_ENABLE] We have an Item to Pick-up");
                    fm.next().interact("Take");
                    c.sleep(2500);
                    return;
                }
                /*for (int i : Constants.LOOT_IDS) {
                    if (c.groundItems.populate().filter(i).size() > 0 && c.groundItems.next().distanceTo(loc) < 10) {
                        System.out.println("\t\t\t\t [Constants.LOOT_ENABLE] We have an Item to Pick-up");
                        c.groundItems.next().interact(234);
                        c.sleep(500);
                        return;
                    }
                }*/
            }

            if (c.players.getLocal().getInteracting() != null) {
                c.sleep(2500);
            } else {
                try {
                    System.out.println("\t\t\t\t [getNextTarget()] Attacking Nearest NPC");
                    getNextTarget().interact(SimpleNpcActions.ATTACK);
                    c.sleep(2500);
                } catch (NullPointerException e) {
                    System.out.println("\t\t\t\t[getNextTarget()] No Targets Available...");
                    c.sleep(500);
                }
            }
        } else {
            travel();
        }
    }

    public void activatePrayers() {
        if (useProtPray()) {
            if (c.prayers.points() > 0) {
                if (getStyle() == 0) {
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 42) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE);
                    }
                } else if (getStyle() == 1) {
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 39) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES);
                    }
                } else {
                    if (c.skills.getLevel(SimpleSkills.Skill.PRAYER) > 36) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC);
                    }
                }
            }
        }
    }

    public void activateRetaliate() {
        if (!c.combat.autoRetaliate()) {
            c.combat.toggleAutoRetaliate(true);
        }
    }
    public void houseKeeping() {
        c.prayers.disableAll();
        c.menuActions.sendAction(20, 733, 0, 0);
        c.pathing.running(true);
    }

    public void grabBankPreset(int style) {
        int preset = -1;
        if (style == 0) { preset = Constants.MELEE_PRESET;}
        if (style == 1) { preset = Constants.RANGE_PRESET;}
        if (style == 2) { preset = Constants.MAGE_PRESET;}
        if (style == 6) { preset = Constants.DRAGON_PRESET; }

        switch(preset) {
            case -1: // something bad happened...
                break;
            case 0:
                c.menuActions.sendAction(315, 1161, 54, 56003);
                break;

            case 1:
                c.menuActions.sendAction(315, 1161, 54, 56004);
                break;

            case 2:
                c.menuActions.sendAction(315, 1161, 54, 56005);
                break;

            case 3:
                c.menuActions.sendAction(315, 1161, 54, 56006);
                break;

            case 4:
                c.menuActions.sendAction(315, 1161, 54, 56007);
                break;

            case 5:
                c.menuActions.sendAction(315, 1161, 54, 56008);
                break;

            case 6:
                c.menuActions.sendAction(315, 1161, 54, 56009);
                break;

            case 7:
                c.menuActions.sendAction(315, 1161, 54, 56010);
                break;

            case 8:
                c.menuActions.sendAction(315, 1161, 54, 56011);
                break;

            case 9:
                c.menuActions.sendAction(315, 1161, 54, 56038);
                break;
        }
        c.sleep(250);
        c.menuActions.sendAction(315, 1161, 54, 56023);
        c.sleep(1000);
        c.menuActions.sendAction(315, 9779, 48, 63740);
        c.sleep(1000);
        bankPresetGrabbed = true;
    }

    public SimpleNpc getNextTarget() {
        try {
            SimpleEntityQuery<SimpleNpc> fm = c.npcs.populate().filter((n) -> n.getId() == getId() && n.getInteracting() == null && !n.isDead() && !n.isAnimating() && getBounds().containsPoint(n.getLocation()));
            SimpleNpc npc = fm.nearest().next();
            if (npc == null || npc.isDead() || npc.getInteracting() != null) {
                npc = fm.nextNearest();
            }
            currentTarget = npc;
            return npc;
        } catch (NullPointerException e) {
            System.out.println("[Task.java:NPE] - <SimpleNpc> getNextTarget()");
        }
        return c.npcs.populate().filter(getId()).next();
    }

    public void walk(WorldPoint[] path) {
        for (int i = 0; i < path.length; i++) {
            if (path[i].equals(loc)) {
                if (i != path.length) {
                    c.pathing.step(path[i+1]);
                }
            }
        }
    }

}
