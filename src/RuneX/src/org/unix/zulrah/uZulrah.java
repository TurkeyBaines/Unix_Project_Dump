package org.unix.zulrah;

import org.unix.Database;
import org.unix.Methods;
import simple.W;
import simple.api.ClientContext;
import simple.api.actions.SimpleNpcActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.events.ProjectileSpawnedEvent;
import simple.api.filters.SimpleInventory;
import simple.api.filters.SimplePrayers;
import simple.api.listeners.SimpleProjectileSpawnedListener;
import simple.api.queries.SimpleItemQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleItem;

import java.awt.*;

@ScriptManifest(author = "unix", name = "uZulrah", category = Category.COMBAT, version = "1", description = "2", discord = "", servers = {"RuneX"})
public class uZulrah extends Script implements SimplePaintable {

    ClientContext c = ctx;
    WorldPoint loc;



    SimpleItemQuery presetInventory;

    int bankID = 10517;
    int jState = 0; // 0 = mage, 1 = range
    int jAnim = 5069;
    int mAnim = 5806;
    int zState = -1; // -1 = null, 0 = range, 1 = mage, 2 = melee, 3 = jad
    int[] ppots = {2434, 139, 141, 143};
    int[] venom = {/*NORMAL*/ 12908, 12909, 12910, 12911,  /*EXTEND*/ 22383, 22385, 22387, 22389 };

    boolean moved = false;

    public static final WorldPoint[] killLoc = new WorldPoint[] {
            new WorldPoint(2270, 3068, 0),
            new WorldPoint(2266, 3068, 0)
    };

    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {
        loc = c.players.getLocal().getLocation();
        System.out.println("------------------------------------");
        if (Database.areas.home.containsPoint(loc)) {
            // Were at home!
            System.out.println("[LOC] We are at home!");
            if (presetInventory == null) { presetInventory = c.inventory.populate(); System.out.println("[INV] Stored our Inventory for later checks"); }

            if (presetInventory.equals(c.inventory.populate())) {
                System.out.println("[INV] Our Inventory matches our Stored Inventory");
                System.out.println("[STAT] PP: " + c.prayers.points() + "/" + c.prayers.maxPoints());
                System.out.println("[STAT] HP: " + c.combat.health() + "/" + c.combat.maxHealth());
                System.out.println("[STAT] VNM: " + c.combat.isVenomed());
                if ((c.prayers.points() == c.prayers.maxPoints()) && (c.combat.health() == c.combat.maxHealth()) && (!c.combat.isVenomed())) {
                    System.out.println("[TELE] Teleporting to Zulrah!");
                    Methods.teleport("zulrah");
                } else {
                    System.out.println("[STAT] Our Stats aren't full... We should restore them!");
                    c.npcs.populate().filter(Database.npcs.nurse).nearest().next().interact(20);
                    c.sleepCondition(() -> (loc.equals(new WorldPoint(3091, 3485, 0)) || loc.equals(new WorldPoint(3090, 3486, 0))), 5000);
                }
            } else {
                System.out.println("[INV] Our Inventory does not match our Stored Inventory... We should grab preset!");
                c.objects.populate().filter(bankID).nearest().next().interact(900);
                c.sleepCondition(() -> presetInventory.equals(c.inventory.populate()), 5000);
            }
            return;
        }

        if (killLoc[0].distanceTo(loc) < 10) {
            System.out.println("[LOC] We are at Zulrah!");

            printZState();
            updateJState();
            checkPrayer();
            checkInventory();
            checkLocation();
            attack();
        }
        System.out.println("------------------------------------");
        System.out.println("");
    }

    @Override
    public void onTerminate() {

    }

    public int getZulrahState() {
        int[] zid = Database.npcs.zulrah;
        if (!c.npcs.populate().filter(zid[0]).isEmpty()) {
            return 0;
        }
        if (!c.npcs.populate().filter(zid[1]).isEmpty()) {
            return 1;
        }
        if (!c.npcs.populate().filter(zid[2]).isEmpty()) {
            return 2;
        }
        if (!c.npcs.populate().filter(zid[3]).isEmpty()) {
            return 3;
        }
        return -1;
    }



    public void updateJState() {
        if (!c.npcs.populate().filter(Database.npcs.zulrah[3]).isEmpty()) {
            if (c.npcs.next().getAnimation() == jAnim) {
                if (jState == 0) {
                    System.out.println("[JState] Range");
                    jState = 1;
                } else {
                    System.out.println("[JState] Mage");
                    jState = 0;
                }
                c.sleep(1000);
            }
        } else {
            System.out.println("[JState] Empty");
        }
    }
    public void checkPrayer() {

        if (getZulrahState() == -1) {
            System.out.println("[Prayer] No Zulrah Found...");
            c.prayers.disableAll();
            return;
        }
        System.out.print("[Prayer] ");

        if (getZulrahState() == 0) {
            if (c.prayers.prayerBook().toString().equals("NORMAL")) {
                System.out.print("<Normal> Range Prayer \n");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES)) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES, true);
                }
            } else {
                System.out.print("<Curses> Range Prayer \n");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MISSILE)) {
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MISSILE, true);
                }
            }
        }
        if (getZulrahState() == 1) {
            if (c.prayers.prayerBook().toString().equals("NORMAL")) {
                System.out.print("<Normal> Magic Prayer \n");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC, true);
                }
            } else {
                System.out.print("<Curses> Magic Prayer \n");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC, true);
                }
            }
        }
        if (getZulrahState() == 2) {
            if (c.prayers.prayerBook().toString().equals("NORMAL")) {
                System.out.print("<normal> No Protection Prayer");
                if (c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES) || c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES, false);
                    c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC, false);
                }
            } else {
                System.out.print("<Curses> No Protection Prayer");
                if (c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MISSILE) || c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MISSILE, false);
                    c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC, false);
                }
            }
        }
        if (getZulrahState() == 3) {
            if (c.prayers.prayerBook().toString().equals("NORMAL")) {
                if (jState == 0) {
                    System.out.print("<Normal> Range Prayer");
                    if (!c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MISSILES)) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES, true);
                    }
                } else {
                    System.out.print("<Normal> Magic Prayer");
                    if (!c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
                        c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC, true);
                    }
                }
            } else {
                if (jState == 0) {
                    System.out.print("<Curses> Range Prayer");
                    if (!c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MISSILE)) {
                        c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MISSILE, true);
                    }
                } else {
                    System.out.print("<Curses> Magic Prayer");
                    if (!c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
                        c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC, true);
                    }
                }
            }
        }

        if (!c.prayers.prayerActive(SimplePrayers.Prayers.EAGLE_EYE) && !c.prayers.prayerActive(SimplePrayers.Prayers.LEECH_RANGE)) {
            if (c.prayers.prayerBook().toString().equals("NORMAL")) {
                System.out.print("<Normal> Eagle Eye Prayer");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.EAGLE_EYE))
                    c.prayers.prayer(SimplePrayers.Prayers.EAGLE_EYE, true);
            } else {
                System.out.println("<Curses> Leech Prayer");
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.LEECH_RANGE))
                    c.prayers.prayer(SimplePrayers.Prayers.LEECH_RANGE);
            }

        }
    }

    public void checkInventory() {
        if (c.prayers.points() < 20) {
            if (c.inventory.populate().filter(Database.items.potions.prayer_potion).isEmpty()) {
                Methods.teleport("home");
                System.out.println("[TELE] We're out of ppots... Teleporting Home!");
                c.sleepCondition(() -> !c.npcs.populate().filter(Database.npcs.nurse).isEmpty(), 5000);
                return;
            }
            c.inventory.populate().filter(Database.items.potions.prayer_potion).next().interact(74);
            c.sleep(500);
            return;
        }

        if (c.combat.isVenomed()) {
            if (c.inventory.populate().filter(Database.items.potions.anti_venom).isEmpty()) {
                Methods.teleport("home");
                System.out.println("[TELE] We're out of venom pots... Teleporting Home!");
                c.sleepCondition(() -> !c.npcs.populate().filter(Database.npcs.nurse).isEmpty(), 5000);
                return;
            }
            c.inventory.populate().filter(Database.items.potions.anti_venom).next().interact(74);
            c.sleep(500);
            return;
        }

        if (c.combat.healthPercent() < 45) {
            if (c.inventory.populate().filter(Database.items.food.monkfish).isEmpty()) {
                Methods.teleport("home");
                System.out.println("[TELE] We're out of food... Teleporting Home!");
                c.sleepCondition(() -> !c.npcs.populate().filter(Database.npcs.nurse).isEmpty(), 5000);
                return;
            }
            c.inventory.populate().filter(Database.items.food.monkfish).next().interact("Eat");
            c.sleep(500);
            return;
        }

        if (!c.inventory.populate().filter(Database.items.potions.vial).isEmpty()) {
            for (SimpleItem si : c.inventory) {
                si.interact(847);
                c.sleep(250);
            }
        }

    }

    public void checkLocation() {
        if (getZulrahState() == 2) {
            if (c.npcs.populate().filter(Database.npcs.zulrah).next().getAnimation() == mAnim) {
                System.out.println("[Melee] Zulrah is animating");
                if (!moved) {
                    System.out.println("\t | We haven't moved yet...");
                    if (loc.equals(killLoc[0])) {
                        System.out.println("\t[Path] Moving to killLoc[1] now!");
                        c.pathing.step(killLoc[1]);
                        c.sleep(750);
                        moved = true;
                    } else if (loc.equals(killLoc[1])) {
                        System.out.println("\t[Path] Moving to killLoc[0] now!");
                        c.pathing.step(killLoc[0]);
                        c.sleep(750);
                        moved = true;
                    }
                }
            } else if (c.npcs.populate().filter(Database.npcs.zulrah).next().getAnimation() == -1) {
                if (moved) {
                    System.out.println("[Melee] Resetting the moved boolean");
                    moved = false;
                }
            }
        } else if (getZulrahState() != -1) {
            if (!loc.equals(killLoc[0]) || !loc.equals(killLoc[1])) {
                System.out.println("[Path] Moving to killLoc[0] now!");
                c.pathing.step(killLoc[0]);
                c.sleep(500);
            }
        }
    }

    public void attack() {
        if (c.players.getLocal().getInteracting() == null) {
            System.out.println("[Fight] Attacking Zulrah now!");
            c.npcs.populate().filter(Database.npcs.zulrah).next().interact(SimpleNpcActions.ATTACK);
            c.sleep(500);
        } else {
            System.out.println("[Fight] We're in combat... Sleeping!");
            c.sleep(500);
        }
    }

    public void printZState() {
        System.out.print("[ZState] ");
        if (getZulrahState() == -1) {
            System.out.print("Null...\n");
        } else if (getZulrahState() == 0) {
            System.out.print("Range \n");
        } else if (getZulrahState() == 1) {
            System.out.print("Magic \n");
        } else if (getZulrahState() == 2) {
            System.out.print("Melee \n");
        } else if (getZulrahState() == 3) {
            System.out.print("Jad \n");
        }
    }




    @Override
    public void onPaint(Graphics2D graphics2D) {

    }
}
