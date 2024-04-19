package org.unix.thief.mainscript.task;

import org.unix.Database;
import org.unix.Task;
import org.unix.thief.mainscript.Config;
import org.unix.thief.mainscript.uThief;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;

public class Steal extends Task {
    @Override
    public void run() {

        if (!c.inventory.populate().filter(Database.items.COIN_POUCH).isEmpty()) {
            SimpleItem item = c.inventory.next();
            if (item.getQuantity() >= 27) {
                item.click("Open-all");
                c.sleepCondition(() -> c.inventory.populate().filter(Database.items.COIN_POUCH).isEmpty(), 500);
            }
        }

        if (!c.inventory.populate().filter(Database.items.ROGUES_OUTFIT).isEmpty()) {
            for (SimpleItem item : c.inventory.populate().filter(Database.items.ROGUES_OUTFIT)) {
                item.click("Wear");
                c.sleep(500);
            }
            return;
        }

        if (!c.inventory.populate().filter(Database.items.DROP_THIEVING).isEmpty()) {
            for (SimpleItem item : c.inventory.populate().filter(Database.items.DROP_THIEVING)) {
                item.click("Drop");
                c.sleep(250);
            }
            return;
        }

        if (c.combat.healthPercent() < 20) {
            if (Config.useFood) {
                uThief.sc.setTask("eat");
                c.sleep(250);
                return;
            }
        }

        if (Config.isStall) {
            return;
        }

        if (graph == -1 && anim != 80) {
            if (!c.npcs.populate().filter(Config.npcID).isEmpty()) {
                SimpleNpc n = c.npcs.nearest().next();
                if (n.visibleOnScreen()) {
                    n.click(0);
                } else {
                    n.turnTo();
                    c.sleepCondition(() -> n.visibleOnScreen(), 500);
                }
                c.sleepCondition(() -> (anim == -1 && graph != 80), 250);
                return;
            }
        }

    }
}
