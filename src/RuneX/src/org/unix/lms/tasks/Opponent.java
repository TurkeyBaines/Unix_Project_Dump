package org.unix.lms.tasks;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;
import simple.api.filters.SimplePrayers;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimplePlayer;

public class Opponent {

    ClientContext c;
    SimplePlayer opp;
    WorldPoint loc;
    WorldPoint lastLoc;
    int hp, weapon, foodLeft, anim, dist, skull;
    int[] gear;
    boolean usedSpec, frozen;
    long frozenTimer;
    SimplePrayers activePrayers;


    public Opponent(SimplePlayer opp) {
        c = ClientContext.instance();
        opp = opp;
        if (opp != null) {
            usedSpec = false;
            frozen = false;
            frozenTimer = -1;
            foodLeft = 18;
            update();
        }
    }

    public void update() {
        loc = opp.getLocation();
        hp = opp.getHealth();
        weapon = opp.getEquipment()[2];
        gear = opp.getEquipment();
        activePrayers = opp.ctx.prayers;
        anim = opp.getAnimation();
        dist = opp.distanceTo(c.players.getLocal().getLocation());
        skull = opp.getSkullIcon();
    }

    public void useSpec() {
        usedSpec = true;
    }

    public void eatFood() {
        foodLeft--;
    }

    public boolean usingSpecWeapon() {
        switch(weapon) {
            case 11694: // ags
                return true;
            case 14484: // claws
                return true;
            case 5698: // DDS (p++)
                return true;
        }
        return false;
    }

    public boolean usingMage() {
        switch (weapon) {
            case 4675: // ancient staff
                return true;
            case 25654: // virtus wand
                return true;
        }
        return false;
    }

    public boolean usingRange() {
        switch (weapon) {
            case 9185:
                break;
        }
        return false;
    }

    public boolean usingMelee() {
        switch (weapon) {
            case 4151: // whip
                return true;
        }
        return false;
    }

    public void lastLoc() {
        lastLoc = loc;
    }

    public SimplePlayer getOpp() {
        return this.opp;
    }

}
