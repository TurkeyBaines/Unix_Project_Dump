package org.unix.thief.mainscript.task;

import org.unix.thief.mainscript.Config;
import org.unix.thief.mainscript.uThief;
import org.unix.Task;

public class Eat extends Task {
    @Override
    public void run() {
        if (!c.inventory.populate().filter(Config.foodID).isEmpty()) {
            while (c.combat.healthPercent() < 20) {
                c.inventory.next().click(0);
                c.sleepCondition(() -> c.combat.healthPercent() >= 50, 1500);
            }
            uThief.sc.setTask("steal");
        }
    }
}
