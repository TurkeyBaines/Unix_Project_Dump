package org.unix.cooker.tasks;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class Task {

    public ClientContext c = ClientContext.instance();
    public SimplePlayer p;
    public int anim;
    public WorldPoint loc;

    abstract void run();

    public void runtime() {
        p = c.players.getLocal();
        anim = p.getAnimation();
        loc = p.getLocation();
        run();
    }

}
