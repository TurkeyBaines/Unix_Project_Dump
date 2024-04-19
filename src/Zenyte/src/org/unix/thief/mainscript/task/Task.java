package org.unix.thief.task;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.wrappers.SimplePlayer;
import simple.robot.api.ClientContext;

public abstract class Task {
    public ClientContext c;
    public WorldPoint loc;
    public int anim;
    public int graph;
    public SimplePlayer p;

    abstract void run();

    public void runTask() {
        c = ClientContext.instance();
        loc = c.players.getLocal().getLocation();
        anim = c.players.getLocal().getAnimation();
        graph = c.players.getLocal().getGraphic();
        p = c.players.getLocal();
        run();
    }
}
