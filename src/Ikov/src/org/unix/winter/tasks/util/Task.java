package org.unix.winter.tasks.util;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;

public abstract class Task {

    public ClientContext c;
    public WorldPoint loc;
    public abstract void runtime();

    public void complete() {
        c = ClientContext.instance();
        loc = c.players.getLocal().getLocation();
        runtime();
    }

}
