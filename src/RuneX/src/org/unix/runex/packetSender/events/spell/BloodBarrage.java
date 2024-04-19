package org.unix.packetSender.events.spell;

import org.unix.packetSender.events.Event;

public class BloodBarrage extends Event {
    @Override
    public void run(String info) {
        c.menuActions.sendAction(626, 0, 0, 12929);
    }
}
