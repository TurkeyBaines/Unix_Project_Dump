package org.unix.fishingguild.tasks;

import net.runelite.api.coords.WorldPoint;
import org.unix.fishingguild.Config;
import org.unix.fishingguild.uGuildFisher;
import simple.hooks.wrappers.SimpleNpc;

import static org.unix.Database.*;

public class Fish extends Task {
    @Override
    void runtime() {
        if (areas.FISHING_GUILD_SPOT.get().containsPoint(loc)) {
            if (c.inventory.populate().size() == 28) {
                System.out.println("We need to change the task to BANK because our inventory is full");
                uGuildFisher.ScriptController.setTask("bank");
                return;
            }

            if (c.inventory.populate().filter(Config.UTENSIL).isEmpty()) {
                System.out.println("We are missing our utensil... going to bank!");
                uGuildFisher.ScriptController.setTask("bank");
                return;
            }

            if (!p.isAnimating()) {
                if (!c.npcs.populate().filter(Config.FISHING_SPOT_ID).isEmpty()) {
                    SimpleNpc n = c.npcs.nearest().next();
                    if (!n.visibleOnScreen()) {
                        n.turnTo();
                        c.sleepCondition(() -> n.visibleOnScreen(), 1500);
                    }
                    c.sleep(1500);
                    n.click(Config.CONTEXTUAL);
                } else {
                    System.out.println("Something is very wrong... stopping script!");
                    c.sendLogout();
                    c.stopScript();
                }
            }
        } else if (areas.FISHING_GUILD_REDUNDANT.get().containsPoint(loc)) {
            if (!c.npcs.populate().filter(Config.FISHING_SPOT_ID).isEmpty()) {
                SimpleNpc n = c.npcs.nearest().next();
                n.clickOnMinimap();
                c.sleepCondition(() -> n.visibleOnScreen(), 1500);
            }
        } else if (areas.FISHING_GUILD_BANK.get().containsPoint(loc)) {
            WorldPoint wp = areas.FISHING_GUILD_REDUNDANT.get().randomTile();
            c.pathing.step(wp);
            c.sleepCondition(() -> wp.equals(loc), 1500);
        }
    }
}
