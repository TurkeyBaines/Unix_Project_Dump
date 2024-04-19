package org.unix.runex.defender.tasks;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;

public abstract class Task {

    ClientContext c = ClientContext.instance();
    WorldPoint loc;
    TaskManager tm;

    public Task(TaskManager taskManager) {
        tm = taskManager;
    }

    public abstract void run();

    public void process(TaskManager taskManager) {
        loc = c.players.getLocal().getLocation();
        run();
    }

}
