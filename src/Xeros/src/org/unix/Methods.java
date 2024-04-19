package org.unix;

import simple.api.ClientContext;
import simple.api.coords.WorldPoint;

public class Methods {
    private static ClientContext c = ClientContext.instance();


    public static void grabBankPreset(int slot) {

        switch(slot) {
            case -1: // something bad happened...
                break;
            case 1:
                c.menuActions.sendAction(315, 1161, 54, 56003);
                break;

            case 2:
                c.menuActions.sendAction(315, 1161, 54, 56004);
                break;

            case 3:
                c.menuActions.sendAction(315, 1161, 54, 56005);
                break;

            case 4:
                c.menuActions.sendAction(315, 1161, 54, 56006);
                break;

            case 5:
                c.menuActions.sendAction(315, 1161, 54, 56007);
                break;

            case 6:
                c.menuActions.sendAction(315, 1161, 54, 56008);
                break;

            case 7:
                c.menuActions.sendAction(315, 1161, 54, 56009);
                break;

            case 8:
                c.menuActions.sendAction(315, 1161, 54, 56010);
                break;

            case 9:
                c.menuActions.sendAction(315, 1161, 54, 56011);
                break;

            case 10:
                c.menuActions.sendAction(315, 1161, 54, 56038);
                break;

            case 19:
                c.menuActions.sendAction(315, 9777, 55, 56048);
                break;
        }
        c.sleep(250);
        c.menuActions.sendAction(315, 1161, 54, 56023);
        c.sleep(1000);
        c.menuActions.sendAction(315, 9779, 48, 63740);
        c.sleep(1000);
    }

    public static void activateAutoRetaliate() {
        if (!c.combat.autoRetaliate()) {
            c.combat.toggleAutoRetaliate(true);
        }
    }

    public static void houseKeeping() {
        c.prayers.disableAll();
        c.menuActions.sendAction(20, 733, 0, 0);
        c.pathing.running(true);
    }

    public static void teleport(String Dest) {
        switch (Dest) {
            case "home":
                c.menuActions.sendAction(315, 526, 513, 12856);
                break;

            case "zulrah":
                c.menuActions.sendAction(315, 0, 0, 13053);
                c.menuActions.sendAction(315, 0, 0, 60416);
                c.menuActions.sendAction(315, 0, 0, 60448);
                c.sleepCondition(() -> new WorldPoint(2268, 3069, 0).equals(c.players.getLocal().getLocation()), 2500);
                break;

            case "agil_gnome":
                break;
            case "agil_draynor":
                break;
            case "agil_alkarid":
                break;
            case "agil_varrock":
                break;
            case "agil_canifis":
                break;
            case "agil_pollniveach":
                 break;
            case "agil_falador":
                break;
            case "agil_seers":
                break;
            case "agil_rellika":
                break;
            case "agil_ardougne":
                break;
        }
    }

    public static void teleport(String master, String dest) {
        ClientContext.instance().teleporter.teleportStringPath(master, dest);
    }
}
