package org.unix.shaco;

import net.runelite.api.coords.WorldPoint;
import simple.robot.api.ClientContext;

public abstract class Task {

    ClientContext c = ClientContext.instance();
    WorldPoint loc;

    public void run() {
        loc = c.players.getLocal().getLocation();
        runtime();
    }
    abstract void runtime();

}
