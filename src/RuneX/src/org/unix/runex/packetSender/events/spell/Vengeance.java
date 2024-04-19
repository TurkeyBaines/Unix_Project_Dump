package org.unix.packetSender.events.spell;

import org.unix.packetSender.events.Event;

public class Vengeance extends Event {
    @Override
    public void run(String info) {
        c.menuActions.sendAction(646, 0, 0, 30306);
    }
}
