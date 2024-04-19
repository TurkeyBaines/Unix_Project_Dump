package org.unix.script.tasks;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;

public abstract class Task {

    ClientContext c = ClientContext.instance();
    WorldPoint loc;

    public abstract void run();

    public abstract String getUIOutput();
}
