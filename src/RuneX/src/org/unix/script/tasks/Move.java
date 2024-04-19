package org.unix.script.tasks;

import simple.api.coords.WorldPoint;

public class Move extends Task {

    WorldPoint wp;

    public Move(WorldPoint point) { wp = point; }

    @Override
    public void run() {
        try {
            if (wp.isReachable(c)) {
                c.pathing.step(wp);
            }
        } catch (Exception e) {
            System.out.println("[Error]<Exception e> run() - Task.Move");
        }
    }

    @Override
    public String getUIOutput() {
        return "[MOVE] " + wp.x + ", " + wp.y + ", " + wp.plane;
    }
}
