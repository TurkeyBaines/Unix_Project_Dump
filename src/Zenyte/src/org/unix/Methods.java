package org.unix;


import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class Methods {
    private static ClientContext c = ClientContext.instance();

    public static void teleportTo(String Tab, String Sub) {
        if (Database.areas.HOME.get().containsPoint(c.players.getLocal().getLocation())) {
            if (c.portalTeleports.portalOpen()) {
                c.portalTeleports.sendTeleport(Tab, Sub);
                c.sleep(500);
            } else {
                try {
                    SimpleObject so = c.objects.populate().filter(Database.objects.TELEPORTER).next();
                    if (so.visibleOnScreen()) {
                        so.click(0);
                        c.sleep(2000);
                    } else if (so.getLocation().distanceTo(c.players.getLocal().getLocation()) < 7) {
                        so.turnTo();
                        c.sleep(1000);
                    } else {
                        so.clickOnMinimap();
                        c.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println("Error trying to find the Teleporter, we are at home!");
                }
            }
        } else {
            c.magic.castHomeTeleport();
            c.sleep(11500);
        }
    }

    public static void activateAutoRetaliate() {
        if (!c.combat.autoRetaliate()) {
            c.combat.toggleAutoRetaliate(true);
        }
    }

    public static String formatTimeRunning(long start, long current) {
        StringBuilder sb = new StringBuilder();

        long running = current - start;

        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        sb.append(hour);
        sb.append(" : ");
        sb.append(minute);
        sb.append(" : ");
        sb.append(second);

        return sb.toString();
    }

    public static String formatExperienceGained(int start, int current) {
        StringBuilder sb = new StringBuilder();

        int total = current - start;
        String s = "" + total;
        char[] t = s.toCharArray();
        if (total > 99999999) {
            sb.append(t[0]); sb.append(t[1]); sb.append(t[2]);
            sb.append(",");
            sb.append(t[3]); sb.append(t[4]); sb.append(t[5]);
            sb.append(",");
            sb.append(t[6]); sb.append(t[7]); sb.append(t[8]);
        } else if (total > 9999999) {
            sb.append(t[0]); sb.append(t[1]);
            sb.append(",");
            sb.append(t[2]); sb.append(t[3]); sb.append(t[4]);
            sb.append(",");
            sb.append(t[5]); sb.append(t[6]); sb.append(t[7]);
        } else if (total > 999999) {
            sb.append(t[0]);
            sb.append(",");
            sb.append(t[1]); sb.append(t[2]); sb.append(t[3]);
            sb.append(",");
            sb.append(t[4]); sb.append(t[5]); sb.append(t[6]);
        } else if (total > 99999) {
            sb.append(t[0]); sb.append(t[1]); sb.append(t[2]);
            sb.append(",");
            sb.append(t[3]); sb.append(t[4]); sb.append(t[5]);
        } else if (total > 9999) {
            sb.append(t[0]); sb.append(t[1]);
            sb.append(",");
            sb.append(t[2]); sb.append(t[3]); sb.append(t[4]);
        } else {
            sb.append(total);
        }
        sb.append("xp");
        return sb.toString();
    }

    public static String formatLevelGained(int start, int current) {
        return "(+" + (current - start) + ")";
    }


    public static void interactWithObject(int id, String interaction) {
        try {
            SimpleObject so = c.objects.populate().filter(id).nearest().next();
            if (!so.visibleOnScreen()) {
                so.turnTo();
                c.sleepCondition(() -> so.visibleOnScreen(), 2500);
            }

            so.click(interaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void interactWithObject(int... id) {
        try {
            SimpleObject so = c.objects.populate().filter(id).nearest().next();
            if (!so.visibleOnScreen()) {
                if (c.players.getLocal().getLocation().distanceTo(so.getLocation()) > 7) {
                    so.clickOnMinimap();
                    c.sleep(2500);
                }
                so.turnTo();
                c.sleepCondition(() -> so.visibleOnScreen(), 2500);
            }

            so.click(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void interactWithNPC(String interaction, int... id) {
        try {
            SimpleNpc snpc = c.npcs.populate().filter(id).nearest().next();
            if (!snpc.visibleOnScreen()) {
                snpc.turnTo();
                c.sleepCondition(() -> snpc.visibleOnScreen(), 2500);
            }

            snpc.click(interaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void interactWithNPC(int... id) {
        try {
            SimpleNpc snpc = c.npcs.populate().filter(id).nearest().next();
            if (!snpc.visibleOnScreen()) {
                snpc.turnTo();
                c.sleepCondition(() -> snpc.visibleOnScreen(), 2500);
            }

            snpc.click(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
