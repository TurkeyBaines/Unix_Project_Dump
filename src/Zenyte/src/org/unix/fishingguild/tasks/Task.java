package org.unix.fishingguild.tasks;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class Task {
    ClientContext c = ClientContext.instance();
    SimplePlayer p;
    int anim;
    WorldPoint loc;

    abstract void runtime();

    public void run() {
        p = c.players.getLocal();
        anim = p.getAnimation();
        loc = p.getLocation();
        runtime();
    }
}
