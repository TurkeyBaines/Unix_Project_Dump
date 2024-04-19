package org.unix.winter.tasks;

import org.unix.winter.Constants;
import org.unix.winter.tasks.util.Task;

public class Bank extends Task {
    @Override
    public void runtime() {
        if (Constants.AREAS.IN_BANK.containsPoint(loc)) {
            if (c.inventory.populate().filter(Constants.ITEMS.CRATE).isEmpty()) {

            }
        }
    }
}
