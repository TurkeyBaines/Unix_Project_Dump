package org.unix.runex.zitt.task;

import org.unix.runex.zitt.Constants;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

import java.awt.*;

public class Jellys extends Task {

    WorldPoint tpLoc = new WorldPoint(1665, 10047, 0);
    WorldPoint[] path = new WorldPoint[] {
            new WorldPoint(1677, 10049, 0),
            new WorldPoint(1687, 10042, 0),
            new WorldPoint(1690, 10031, 0),
            new WorldPoint(1692, 10021, 0),
            new WorldPoint(1691, 10011, 0),
            new WorldPoint(1691, 10002, 0)
    };

    WorldPoint[] path2 = new WorldPoint[] {
            new WorldPoint(1664, 10061, 0), // PRAY MAGE
            new WorldPoint(1664, 10075, 0),
            new WorldPoint(1672, 10088, 0),
            new WorldPoint(1675, 10096, 0)
    };

    @Override
    public void travelToTask() {

        if (path2[3].equals(loc)) {
            c.pathing.step(path2[2]);
            c.sleep(500);
        } else if (path2[2].equals(loc)) {
            c.pathing.step(path2[1]);
            c.sleep(500);
        } else if (path2[1].equals(loc)) {
            c.pathing.step(path2[0]);
            c.sleep(500);
        } else if (path2[0].equals(loc)) {
            c.pathing.step(tpLoc);
            c.sleep(500);
        }

        if (Constants.HOME_AREA.containsPoint(loc)) {
            // TODO Add House Instanced Teleports
        } else if (tpLoc.equals(loc)) {
            c.pathing.step(path[0]);
            c.sleep(250);
        } else if (path[0].equals(loc)) {
            c.pathing.step(path[1]);
            c.sleep(250);
        } else if (path[1].equals(loc)) {
            c.pathing.step(path[2]);
            c.sleep(250);
        } else if (path[2].equals(loc)) {
            c.pathing.step(path[3]);
            c.prayers.disableAll();
            activateRetaliate();
            c.sleep(250);
        } else if (path[3].equals(loc)) {
            c.pathing.step(path[4]);
            c.sleep(250);
        } else if (path[4].equals(loc)) {
            c.pathing.step(path[5]);
            c.sleep(250);
        }
    }

    @Override
    public int getID() {
        return 1640;
    }

    @Override
    public String getName() {
        return "Jelly";
    }

    @Override
    public int getFightingStyle() {
        return -1;
    }

    @Override
    public int[] getTeleportInfo() {
        return new int[0];
    }

    @Override
    public WorldArea getFightingArea() {
        return new WorldArea(new WorldPoint(1695, 10005, 0), new WorldPoint(1675, 9985, 0));
    }

    @Override
    public WorldPoint cannonLocation() {
        return new WorldPoint(1688, 10001, 0);
    }

    @Override
    public void debugPaint(Graphics2D g) {

    }

    public void setupCannon() {

    }

    public void walkBack() {

    }
}
