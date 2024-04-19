package org.unix.thief.uMasterFarmer.tasks;

import org.unix.Database;
import org.unix.Task;
import org.unix.thief.uMasterFarmer.Config;
import org.unix.thief.uMasterFarmer.uMasterFarmer;
import simple.hooks.wrappers.SimpleNpc;

import static org.unix.Database.*;
import static org.unix.Methods.*;

public class Steal extends Task {
    @Override
    public void run() {

        if (c.inventory.populate().size() == 28) {
            if (areas.DRAYNOR_BANK.get().containsPoint(loc)) {
                uMasterFarmer.sc.setTask("bank");
            } else {
                if (tiles.DRAYNOR_THIEVING_SPOT.get().equals(loc)) {
                    c.pathing.step(areas.DRAYNOR_BANK.get().randomTile());
                    c.sleepCondition(() -> areas.DRAYNOR_BANK.get().containsPoint(loc), 2000);
                } else {
                    System.out.println(loc.getX() + " x " + loc.getY() + " x " + loc.getPlane());
                    c.pathing.step(tiles.DRAYNOR_THIEVING_SPOT.get());
                    c.sleepCondition(() -> tiles.DRAYNOR_THIEVING_SPOT.get().equals(loc), 1000);
                }
            }
            return;
        }

        if (c.combat.healthPercent() < 25 && c.inventory.populate().filter(Config.SELECTED_FOOD).isEmpty()) {
            uMasterFarmer.sc.setTask("bank");
            return;
        }

        if (c.combat.healthPercent() < 25) {
            while (c.combat.healthPercent() < 75) {
                c.inventory.populate().filter(Config.SELECTED_FOOD).next().click(0);
                c.sleep(2000);
            }
            return;
        }

        SimpleNpc sNpc = c.npcs.populate().filter(Database.npcs.MASTER_FARMER).nearest().next();
        if (sNpc == null) { return; }
        if (loc.distanceTo(sNpc.getLocation()) > 5) {
            sNpc.clickOnMinimap();
            c.sleepCondition(() -> sNpc.visibleOnScreen(), 1500);
            return;
        }

        if (!sNpc.visibleOnScreen()) {
            sNpc.turnTo();
            c.sleepCondition(() -> sNpc.visibleOnScreen(), 250);
            return;
        }

        interactWithNPC(npcs.MASTER_FARMER);
    }
}
