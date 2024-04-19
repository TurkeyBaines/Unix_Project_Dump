package org.unix.fishingguild.tasks;

import net.runelite.api.coords.WorldPoint;
import org.unix.Database;
import org.unix.fishingguild.Config;
import org.unix.fishingguild.uGuildFisher;
import simple.hooks.filters.SimpleBank;
import simple.hooks.wrappers.SimpleObject;

import static org.unix.Database.*;

public class Bank extends Task {
    @Override
    void runtime() {
        if (areas.FISHING_GUILD_BANK.get().containsPoint(loc)) {
            if (c.bank.bankOpen()) {
                if (!c.inventory.populate().filter(Config.FISH_ID).isEmpty()) {
                    System.out.println("Depositing our catch!");
                    c.bank.deposit(Config.FISH_ID, SimpleBank.Amount.ALL);
                    c.sleepCondition(() -> c.inventory.populate().filter(Config.FISH_ID).isEmpty(), 1000);
                } else if (c.inventory.populate().filter(Config.UTENSIL).isEmpty()) {
                    System.out.println("We're withdrawing our weapon... it seems to be missing");
                    c.bank.withdraw(Config.UTENSIL, 1);
                    c.sleepCondition(() -> !c.inventory.populate().filter(Config.UTENSIL).isEmpty(), 1000);
                } else {
                    System.out.println("All Set!! closing the bank...");
                    c.bank.closeBank();
                    c.sleepCondition(() -> !c.bank.bankOpen(), 1000);
                }
            } else {
                if (c.inventory.populate().filter(Config.FISH_ID).isEmpty()) {
                    uGuildFisher.ScriptController.setTask("fish");
                    System.out.println("We have banked... swapping back to the fishing task!");
                } else {
                    System.out.println("We're trying to open the bank...");
                    SimpleObject o = c.objects.populate().filter(objects.BANK_BOOTH).nearest().next();
                    if (o != null) {
                        if (o.visibleOnScreen()) {
                            o.click(0);
                            c.sleepCondition(() -> c.bank.bankOpen(), 1000);
                        } else {
                            System.out.println("Turning.......");
                            o.turnTo();
                            c.sleepCondition(() -> o.visibleOnScreen(), 1000);
                        }
                    }
                }
            }
        } else if (areas.FISHING_GUILD_SPOT.get().containsPoint(loc)) {
            System.out.println("Walking to random redundant tile");
            WorldPoint wp = areas.FISHING_GUILD_REDUNDANT.get().randomTile();
            c.pathing.step(wp);
            c.sleepCondition(() -> wp.equals(loc), 1500);
        } else if (areas.FISHING_GUILD_REDUNDANT.get().containsPoint(loc)) {
            SimpleObject o = c.objects.populate().filter(objects.BANK_BOOTH).nearest().next();
            if (o != null) {
                if (o.visibleOnScreen()) {
                    System.out.println("Trying to open the bank!");
                    o.click(0);
                    c.sleepCondition(() -> c.bank.bankOpen(), 1500);
                } else {
                    System.out.println("Turning.....");
                    o.turnTo();
                    c.sleepCondition(() -> o.visibleOnScreen(), 1500);
                }
            }
        }
    }
}
