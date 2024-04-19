package org.unix.shaco;

import org.unix.Database;
import org.unix.Methods;
import simple.hooks.wrappers.SimpleWidget;

public class Bank extends Task {
    @Override
    public void runtime() {
        System.out.println("[Bank / runtime()] - Top of Method");
        if (c.bank.bankOpen()) {
            System.out.println("[Bank / runtime()] - Bank Open");
            if (hasPrayerPots()) {
                System.out.println("[Bank / runtime()] - We Have Pots");
                // WE HAVE POTS
                if (hasRunes()) {
                    System.out.println("[Bank / runtime()] - We Have Runes");
                    // WE HAVE RUNES
                    if (hasFood()) {
                        if (hasSpade()) {
                            System.out.println("[Bank / runtime()] - We Have Food");
                            System.out.println("[Bank / runtime()] - We Are Closing The Bank");
                            // WE HAVE FOOD
                            c.bank.closeBank();
                            c.sleepCondition(() -> !c.bank.bankOpen(), 1000);
                        } else {
                            System.out.println("[Bank / runtime()] - We Are Withdrawing A Spade");
                            c.bank.withdraw(Database.items.SPADE, 1);
                            c.sleepCondition(() -> !c.inventory.populate().filter(Database.items.SPADE).isEmpty(), 500);
                        }
                    } else {
                        System.out.println("[Bank / runtime()] - We Are Withdrawing 2 Monkfish");
                        for (int i = 0; i < 2; i++) {
                            System.out.println("[Bank / runtime()] - \t [" + (i+1) + "]");
                            c.bank.withdraw(Database.food.MONKFISH, 1);
                            c.sleep(500);
                        }
                    }
                } else {
                    System.out.println("[Bank / runtime()] - We Are Withdrawing Runes");
                    if (c.inventory.populate().filter(Database.runes.DEATH_RUNE).isEmpty()) {
                        System.out.println("[Bank / runtime()] - \t DEATH RUNES");
                        c.bank.withdraw(Database.runes.DEATH_RUNE, 0);
                        c.sleepCondition(() -> !c.inventory.populate().filter(Database.runes.DEATH_RUNE).isEmpty(), 500);
                        return;
                    }
                    if (c.inventory.populate().filter(Database.runes.FIRE_RUNE).isEmpty()) {
                        System.out.println("[Bank / runtime()] - \t FIRE RUNES");
                        c.bank.withdraw(Database.runes.FIRE_RUNE, 0);
                        c.sleepCondition(() -> !c.inventory.populate().filter(Database.runes.FIRE_RUNE).isEmpty());
                        return;
                    }
                }
            } else {
                if (c.inventory.populate().size() != 8 && !c.inventory.populate().isEmpty()) {
                    System.out.println("[Bank / runtime()] - Depositing Inventory");
                    c.bank.depositInventory();
                    c.sleepCondition(() -> c.inventory.populate().isEmpty(), 1000);
                    return;
                }
                System.out.println("[Bank / runtime()] - Depositing Inventory");
                c.bank.withdraw(Database.potions.PRAYER_POTION[4], 5);
                c.sleepCondition(() -> !c.inventory.populate().filter(Database.potions.PRAYER_POTION[4]).isEmpty(), 500);
            }
        } else {
            if (hasPrayerPots() && hasRunes() && hasFood() && hasSpade()) {
                System.out.println("[Bank / runtime()] - We Good To Go!");
                if (Database.areas.BARROWS.get().containsPoint(loc)) {
                    uBarrows.ScriptManager.forceKill();
                    return;
                }

                if (c.teleporter.opened()) {
                    System.out.println("[Bank / runtime()] - Teleport Menu Is Open");
                    /*if (Database.widgets.TELEPORT_TOP_ENTRY.get().getText().toLowerCase().contains("barrow")) {
                        Database.widgets.TELEPORT_TOP_ENTRY.get().click("");
                        c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 1500);
                    } else {
                        if (Database.widgets.TELEPORT_FAV_1.get().getText().toLowerCase() .contains("barrow")) {
                            Database.widgets.TELEPORT_FAV_1.get().click("");
                        }
                        if (Database.widgets.TELEPORT_FAV_2.get().getText().toLowerCase().contains("barrow")) {
                            Database.widgets.TELEPORT_FAV_2.get().click("");
                        }

                    }*/
                    c.teleporter.teleportStringPath("Minigames", "Barrows");
                    c.sleepCondition(() -> Database.areas.BARROWS.get().containsPoint(loc), 1500);
                } else {
                    System.out.println("[Bank / runtime()] - Opening Teleport Menu");
                    c.teleporter.open();
                    c.magic.castHomeTeleport();
                    c.sleepCondition(() -> c.teleporter.opened(), 1500);
                }
            }
            if (Database.areas.FEROX_BANK.get().containsPoint(loc)) {
                Methods.intEntity(0, Database.objects.FEROX_BANK_CHEST, "Use");
                c.sleepCondition(() -> c.bank.bankOpen(), 500);
            } else {
                if (Database.widgets.TELEPORT_MAIN.get().visibleOnScreen()) {
                    if (Database.widgets.TELEPORT_TOP_ENTRY.get().getText().toLowerCase().contains("ferox")) {
                        Database.widgets.TELEPORT_TOP_ENTRY.get().click("");
                        c.sleepCondition(() -> Database.areas.FEROX_BANK.get().containsPoint(loc), 2500);
                    } else {
                        if (Database.widgets.TELEPORT_FAV_1.get().getText().toLowerCase().contains("ferox")) {
                            Database.widgets.TELEPORT_FAV_1.get().click("");
                        } else if (Database.widgets.TELEPORT_FAV_2.get().getText().toLowerCase().contains("ferox")) {
                            Database.widgets.TELEPORT_FAV_2.get().click("");
                        }
                    }
                } else {
                    c.magic.castHomeTeleport();
                    c.sleepCondition(() -> Database.widgets.TELEPORT_MAIN.get().visibleOnScreen(), 1500);
                }
            }
        }

    }

    boolean hasPrayerPots() {
        return c.inventory.populate().filter(Database.potions.PRAYER_POTION[4]).size() == 5;
    }
    boolean hasRunes() {
        return true;
    }
    boolean hasFood() {
        return !c.inventory.populate().filter(Database.food.MONKFISH).isEmpty();
    }
    boolean hasSpade() {
        return !c.inventory.populate().filter(Database.items.SPADE).isEmpty();
    }

}
