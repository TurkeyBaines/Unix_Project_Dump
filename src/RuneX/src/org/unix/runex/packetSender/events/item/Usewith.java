package org.unix.packetSender.events.item;

import org.unix.packetSender.events.Event;
import simple.api.actions.SimpleItemActions;
import simple.api.wrappers.SimpleItem;

public class Usewith extends Event {
    @Override
    public void run(String info) {
        String[] split = info.split(",");
        int first = Integer.parseInt(split[0]);
        int second = Integer.parseInt(split[1]);
        int amt = Integer.parseInt(split[2]);

        for (int i = 0; i < amt; i++) {
            SimpleItem item_a = c.inventory.populate().filter(first).next();
            SimpleItem item_b = c.inventory.populate().filter(second).next();
            if (item_a == null || item_b == null) {
                System.out.println("Something went wrong, cant find the item requested...");
                return;
            }
            c.menuActions.sendAction(447, item_a.getId(), item_a.getSlot(), 3214);
            c.sleep(250);
            c.menuActions.sendAction(870, item_b.getId(), item_b.getSlot(), 3214);
            c.sleep(1000);
        }

    }
}
