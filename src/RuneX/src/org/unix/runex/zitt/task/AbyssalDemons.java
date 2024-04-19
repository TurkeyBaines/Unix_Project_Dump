package org.unix.runex.zitt.task;

import org.unix.runex.zitt.Constants;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

import java.awt.*;

public class AbyssalDemons extends Task {

    WorldPoint tpLoc = new WorldPoint(1665, 10047, 0);
    WorldPoint[] path = new WorldPoint[] {
            new WorldPoint(1664, 10061, 0), // PRAY MAGE
            new WorldPoint(1664, 10075, 0),
            new WorldPoint(1672, 10088, 0),
            new WorldPoint(1675, 10096, 0)
    };

    WorldPoint[] path2 = new WorldPoint[]{
            new WorldPoint(1677, 10049, 0),
            new WorldPoint(1687, 10042, 0),
            new WorldPoint(1690, 10031, 0),
            new WorldPoint(1692, 10021, 0),
            new WorldPoint(1691, 10011, 0)
    };

    @Override
    public void travelToTask() {

        if (path2[4].equals(loc)) {
            c.pathing.step(path2[3]);
            c.sleep(500);
        } else if (path2[3].equals(loc)) {
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
            activatePrayers(2);
            deactivateRetaliate();
            c.pathing.step(path[2]);
            c.sleep(250);
        } else if (path[2].equals(loc)) {
            c.prayers.disableAll();
            activateRetaliate();
            c.pathing.step(path[3]);
            c.sleep(250);
        }
    }

    @Override
    public int getID() {
        return 1615;
    }

    @Override
    public String getName() {
        return "Abyssal Demon";
    }

    @Override
    public int getFightingStyle() {
        return 0;
    }

    @Override
    public int[] getTeleportInfo() {
        return new int[] {315,1333,1,62010};
    }

    @Override
    public WorldArea getFightingArea() {
        return new WorldArea(new WorldPoint(1680, 10093, 0), new WorldPoint(1669,10101,0));
    }

    @Override
    public WorldPoint cannonLocation() {
        return new WorldPoint(1674, 10096, 0);
    }

    @Override
    public void debugPaint(Graphics2D g) {

    }
}
