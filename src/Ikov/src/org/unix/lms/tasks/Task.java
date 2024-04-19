package org.unix.lms.tasks;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;
import simple.api.queries.SimplePlayerQuery;
import simple.api.wrappers.SimplePlayer;

public abstract class Task {

    public abstract boolean execute();
    public abstract void run();
    ClientContext c;
    WorldPoint loc;

    public Task() {
        c = ClientContext.instance();
    }

    public void process() {
        loc = c.players.getLocal().getLocation();

        if (execute()) { run(); }
        System.out.println("execute? " + execute());
    }

}
