package org.unix.barbarian.tasks;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class Task {

    ClientContext c = ClientContext.instance();
    SimplePlayer p;
    WorldPoint loc;
    int anim;

    abstract void run();

    public void runtime() {
        p = c.players.getLocal();
        loc = p.getLocation();
        anim = p.getAnimation();
        run();
    }

}
