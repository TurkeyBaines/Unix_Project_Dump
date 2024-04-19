package org.unix.runex.agility.course;

import simple.api.ClientContext;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.queries.SimpleEntityQuery;
import simple.api.wrappers.SimpleSceneObject;

public abstract class Course {
    public ClientContext c = ClientContext.instance();
    public WorldPoint loc;

    public abstract String name();
    public abstract int reqLevel();
    public abstract int upgradeLevel();
    public abstract WorldPoint tpLoc();
    public abstract void runtime();
    public abstract String nextText();

    public abstract WorldArea[] worldArea();

    public abstract void travelTo();

    public boolean inArea() {
        loc = c.players.getLocal().getLocation();
        for (WorldArea a : worldArea()) {
            if (a.containsPoint(loc)) {
                return true;
            }
        }
        return false;
    }

    public void interactWithID(int id) {
        SimpleEntityQuery<SimpleSceneObject> fm = c.objects.populate().filter((n) -> n.getId() == id);
        if (!fm.isEmpty()) {
            SimpleSceneObject sso = fm.next();
            System.out.println("<"+fm.size()+"> [Course.java > interactWithID("+id+") x[502] y["+sso.getName()+"]");
            sso.interact(502);
            c.sleep(2500);
        } else {
            System.out.println("No Results on Next Step");
        }
    }



}
