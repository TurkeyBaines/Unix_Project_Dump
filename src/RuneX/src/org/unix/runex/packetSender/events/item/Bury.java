package org.unix.packetSender.events.item;

import org.unix.packetSender.events.Event;

public class Bury extends Event {
    @Override
    public void run(String info) {
        String[] a = info.split(",");
        int id = Integer.parseInt(a[0]);
        int amt = Integer.parseInt(a[1]);
        int slot;
        for (int i = 0; i < amt; i++) {
            slot = c.inventory.populate().filter(id).next().getSlot();
            c.menuActions.sendAction(74, id, slot, 3214);
            c.sleep(1000);
        }
    }
}
