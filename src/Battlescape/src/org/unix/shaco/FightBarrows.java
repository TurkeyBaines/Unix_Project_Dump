package org.unix.shaco;

import net.runelite.api.Actor;
import net.runelite.api.Prayer;
import net.runelite.api.coords.WorldPoint;
import org.unix.Database;
import org.unix.Methods;
import simple.hooks.filters.SimplePrayers;
import simple.hooks.queries.SimpleEntityQuery;
import simple.hooks.wrappers.SimpleNpc;

public class FightBarrows extends Task {


    boolean dh = false, ah = false, to = false, gu = false, ka = false, vr = false;

    @Override
    public void runtime() {
        System.out.println("[FightBarrows / runtime()] - ");


        if (c.combat.inCombat()) {
            System.out.println("[FightBarrows / runtime()] - We're In Combat!  " + c.players.getLocal().getInteracting().getName());

            c.sleepCondition(() -> !c.combat.inCombat(), 1000);
            return;
        } else {

        }

        if (Database.areas.BARROWS_LOOT.get().containsPoint(loc)) {
            System.out.println("[FightBarrows / runtime()] - In Loot Room");
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("80")) { vr = true; }
            if (dh && ah && to && gu && ka && vr) {
                System.out.println("[FightBarrows / runtime()] - All Enemies Dead! Looting...");
                c.objects.populate().filter(Database.objects.BARROWS_CHEST).next().click(0);
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
                dh = false;
                ah = false;
                to = false;
                gu = false;
                ka = false;
                vr = false;
            } else {
                System.out.println("[FightBarrows / runtime()] - Spawning Verac From Chest");
                c.objects.populate().filter(Database.objects.BARROWS_CHEST).next().click(0);
                c.sleep(1000);
            }
            return;
        }

        if (Database.areas.BARROWS_DHAROK.get().containsPoint(loc)) {
            System.out.println("[FightBarrows / runtime()] - Inside Dharok Room");
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("12")) { dh = true; }

            if (!dh) {
                Methods.intEntity(0, Database.objects.DHAROK_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> c.combat.inCombat(), 2000);
            } else {
                Methods.intEntity(0, Database.objects.DHAROK_STAIRS, "Climb-up");
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
            }
            return;
        }

        if (Database.areas.BARROWS_AHRIM.get().containsPoint(loc)) {
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("26")) { ah = true; }
            if (!ah) {
                Methods.intEntity(0, Database.objects.AHRIM_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> c.combat.inCombat(), 2000);
            } else {
                Methods.intEntity(0, Database.objects.AHRIM_STAIRS, "Climb-up");
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
            }
            return;
        }

        if (Database.areas.BARROWS_GUTHAN.get().containsPoint(loc)) {
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("40")) { gu = true; }

            if (!gu) {
                c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE, true);
                c.sleep(1000);
                Methods.intEntity(0, Database.objects.GUTHAN_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> c.combat.inCombat(), 2500);
            } else {
                Methods.intEntity(0, Database.objects.GUTHAN_STAIRS, "Climb-up");
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
            }
            return;
        }

        if (Database.areas.BARROWS_KARIL.get().containsPoint(loc)) {
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("52")) { ka = true; }
            if (!ka) {
                c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MISSILES, true);
                c.sleep(1000);
                Methods.intEntity(0, Database.objects.KARIL_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> c.combat.inCombat(), 2500);
            } else {
                Methods.intEntity(0, Database.objects.KARIL_STAIRS, "Climb-up");
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
            }
            return;
        }

        if (Database.areas.BARROWS_TORAG.get().containsPoint(loc)) {
            if (Database.widgets.BARROWS_POTENTIAL.get().getText().contains("66")) { to = true; }
            if (!to) {
                c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MELEE, true);
                c.sleep(1000);
                Methods.intEntity(0, Database.objects.TORAG_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> c.combat.inCombat(), 2500);
            } else {
                Methods.intEntity(0, Database.objects.TORAG_STAIRS, "Climb-up");
                c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 2000);
            }
            return;
        }

        if (Database.areas.BARROWS_VERAC.get().containsPoint(loc)) {
            if (!vr) {
                Methods.intEntity(0, Database.objects.VERAC_SARCOPHAGUS, "Search");
                c.sleepCondition(() -> Database.areas.BARROWS_LOOT.get().containsPoint(loc), 2000);
            }
        }

        if (Database.areas.BARROWS.get().containsPoint(loc)) {
            System.out.println("[FightBarrows / runtime()] - We're In The Main Barrows Area...");


            if (c.prayers.prayerPercent() < 50) {
                System.out.println("[FightBarrows / runtime()] - We Need To Drink A Potion");
                if (!c.inventory.populate().filter(Database.potions.PRAYER_POTION).isEmpty()) {
                    System.out.println("[FightBarrows / runtime()] - We Have One! Clicking...");
                    c.inventory.next().click("Drink");
                    return;
                }
                System.out.println("[FightBarrows / runtime()] - We Have No Potions But Will Finish The Run!");
            }



            if ((!dh && !ah && !to && !gu && !ka && !vr) && c.inventory.populate().filter(Database.potions.PRAYER_POTION).size() < 2) {
                System.out.println("[FightBarrows / runtime()] - We've Just Finished... Time To Bank!");
                if (c.teleporter.opened()) {
                    c.teleporter.teleportStringPath("Wilderness", "Ferox Enclave");
                    c.sleepCondition(() -> Database.areas.FEROX_BANK.get().containsPoint(loc), 1500);
                    uBarrows.ScriptManager.forceBank();
                } else {
                    c.teleporter.open();
                    c.sleepCondition(() -> c.teleporter.opened(), 500);
                }
                return;
            }
            if (!dh) {
                if (Database.areas.BARROWS_DHAROK_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Dharok");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_DHAROK.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Dharok Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_DHAROK_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_DHAROK_DIG.get().containsPoint(loc), 1500);
                }
            } else if (!ah) {
                if (Database.areas.BARROWS_AHRIM_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Ahrim");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_AHRIM.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Ahrim Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_AHRIM_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_AHRIM_DIG.get().containsPoint(loc), 1500);
                }
            } else if (!gu) {
                if (Database.areas.BARROWS_GUTHAN_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Guthan");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_GUTHAN.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Guthan Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_GUTHAN_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_GUTHAN_DIG.get().containsPoint(loc), 1500);
                }
            } else if (!ka) {
                if (Database.areas.BARROWS_KARIL_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Karil");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_KARIL.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Karil Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_KARIL_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_KARIL_DIG.get().containsPoint(loc), 1500);
                }
            } else if (!to) {
                if (Database.areas.BARROWS_TORAG_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Torag");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_TORAG.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Torag Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_TORAG_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_TORAG_DIG.get().containsPoint(loc), 1500);
                }
            } else if (!vr) {
                if (Database.areas.BARROWS_VERAC_DIG.get().containsPoint(loc)) {
                    System.out.println("[FightBarrows / runtime()] - Digging To Verac");
                    c.inventory.populate().filter(Database.items.SPADE).next().click("Dig");
                    c.sleepCondition(() -> Database.areas.BARROWS_VERAC.get().containsPoint(loc), 1000);
                } else {
                    System.out.println("[FightBarrows / runtime()] - Walking To Verac Dig Spot");
                    c.pathing.step(Database.areas.BARROWS_VERAC_DIG.get().randomTile());
                    c.sleepCondition(() -> Database.areas.BARROWS_VERAC_DIG.get().containsPoint(loc), 1500);
                }
            }
            return;
        }


    }
}
