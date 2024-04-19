package org.unix.kraken;

import org.unix.Methods;
import simple.api.ClientContext;
import simple.api.actions.SimpleNpcActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.filters.SimpleInventory;
import simple.api.filters.SimplePrayers;
import simple.api.filters.SimpleSkills;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleItem;
import simple.api.wrappers.SimpleNpc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@ScriptManifest(category = Category.COMBAT, description = "Kills Kraken, Banks when out of ppots and loads preset 20 to restock!", version = "0", servers = {"RuneX"}, discord = "", author = "unix", name = "uKrakenSlayer")
public class uKraken extends Script implements SimplePaintable {

    long startTime;
    int totalKills;
    int totalLoot;
    int startMage;
    int startHP;
    String currentTask = "null";

    final WorldArea HOME_AREA = new WorldArea(
            new WorldPoint(3075,3481,0),
            new WorldPoint(3096, 3512, 0));

    public WorldArea KRAKEN_AREA = new WorldArea(new WorldPoint(3704, 5809, 0), new WorldPoint(3691, 5796, 0));

    public WorldPoint KRAKEN_LOC = new WorldPoint(3696, 5807, 0);

    SimpleInventory lastInv;

    ClientContext c = ctx;
    WorldPoint loc;
    boolean needsPreset = true;
    boolean needsHeals = true;
    boolean firstBank = true;
    boolean canUseAugury = true;
    int[] prayerPotion = {2434, 139, 141, 143};

    @Override
    public void onProcess() {
        loc = c.players.getLocal().getLocation();
        if (!c.combat.autoRetaliate()) { c.combat.toggleAutoRetaliate(true); }
        if (HOME_AREA.containsPoint(loc)) {
            c.prayers.disableAll();
            if (needsPreset) {
                if (c.bank.bankOpen()) {
                    Methods.grabBankPreset(19);
                    c.sleep(500);
                    c.bank.closeBank();
                    c.sleep(2500);
                    needsPreset = false;
                    return;
                } else {
                    c.bank.openBank();
                    c.sleep(2500);
                    return;
                }
            } else if (needsHeals) {
                c.menuActions.sendAction(20, 743, 0, 0);
                c.sleep(2);
                if (c.prayers.prayerPercent() == 100) { needsHeals = false; c.menuActions.sendAction(169, 261, 368, 350); c.menuActions.sendAction(315, 261, 368, 13136); sleep(0);  }
                return;
            } else {
                c.menuActions.sendAction(315, 0, 0, 13053);
                c.sleep(0);
                c.menuActions.sendAction(315, 0, 0, 60418);
                c.sleep(0);
                c.menuActions.sendAction(315, 0, 0, 60448);
                c.sleep(2);
                return;
            }
        } else if (KRAKEN_AREA.containsPoint(loc)) {
            System.out.println("C");

            if (!needsPreset || !needsHeals) { needsPreset = true; needsHeals = true; }

            if (c.players.populate().size() > 1) { goHome(); c.stopScript(); return; }

            if (c.inventory.populate().size() == 28) { goHome(); return; }

            checkPrayers();
            loot();

            if (!KRAKEN_LOC.equals(loc)) {
                currentTask = "Moving To Tile";
                c.pathing.step(KRAKEN_LOC);
                c.sleep(250);
                return;
            }

            if (c.players.getLocal().getInteracting() != null) { sleep(1); currentTask = "Fighting..."; if (c.players.getLocal().getInteracting().isDead()) { totalKills++; currentTask = "Its Dead!!"; c.sleep(2500); }  return; }

            if (!c.npcs.populate().filter(4154).isEmpty()) {
                SimpleNpc n = c.npcs.next();
                if (n != null) {
                    currentTask = "Attacking!";
                    n.interact(SimpleNpcActions.ATTACK);
                    c.sleep(1);
                }
            }
        }
    }

    @Override
    public boolean onExecute() {
        startTime = System.currentTimeMillis();
        startHP = c.skills.getExperience(SimpleSkills.Skill.HITPOINTS);
        startMage = c.skills.getExperience(SimpleSkills.Skill.MAGIC);
        totalLoot = 0;
        totalKills = 0;
        return true;
    }

    @Override
    public void onTerminate() {
        System.out.println(formatTimeRunning(startTime));
    }

    void sleep(int x) {
        switch (x) {
            case 0:
                c.sleep(250);

            case 1:
                c.sleep(1000);

            case 2:
                c.sleep(2500);
        }
    }

    public void checkPrayers() {
        if (c.prayers.prayerPercent() < 10) {
            if (c.inventory.populate().filter(prayerPotion).isEmpty()) {
                currentTask = "Going Home!";
                goHome();
            } else {
                currentTask = "Drinking PPot";
                c.inventory.next().interact(74);
                sleep(1);
                c.inventory.populate().filter(prayerPotion);
                c.inventory.next().interact(74);
                sleep(0);
            }
        }

        if (c.npcs.populate().filter(4154).isEmpty()) {
            c.prayers.disableAll();
            return;
        }

        if (c.prayers.prayerBook().toString().contains("NORMAL")) {
            if (!c.prayers.prayerActive(SimplePrayers.Prayers.PROTECT_FROM_MAGIC)) {
                currentTask = "Activate Pray";
                c.prayers.prayer(SimplePrayers.Prayers.PROTECT_FROM_MAGIC, true);
            }
            int x = c.skills.getLevel(SimpleSkills.Skill.PRAYER);
            if (x < 27) {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.MYSTIC_WILL)) { c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT, true); }
                return;
            }
            if (x < 45) {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.MYSTIC_LORE)) { c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_LORE, true); }
                return;
            }
            if (x < 77) {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.MYSTIC_MIGHT)) { c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT, true); }
                return;
            }
            if (x > 76 && canUseAugury) {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.AUGURY)) { c.prayers.prayer(SimplePrayers.Prayers.AUGURY, true); }
            } else {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.MYSTIC_MIGHT)) { c.prayers.prayer(SimplePrayers.Prayers.MYSTIC_MIGHT, true); }
            }
        } else if (c.prayers.prayerBook().toString().contains("CURSES")) {
            if (!c.prayers.prayerActive(SimplePrayers.Prayers.DEFLECT_MAGIC)) {
                currentTask = "Activate Pray";
                c.prayers.prayer(SimplePrayers.Prayers.DEFLECT_MAGIC, true);
            }
            int x = c.skills.getLevel(SimpleSkills.Skill.PRAYER);
            if (x > 77) {
                if (!c.prayers.prayerActive(SimplePrayers.Prayers.LEECH_MAGIC)) { c.prayers.prayer(SimplePrayers.Prayers.LEECH_MAGIC, true); }
            }
        }
    }

    public void loot() {
        if (!c.groundItems.populate().isEmpty()) {
            currentTask = "Looting...";
            c.groundItems.next().interact("Take");
            c.sleep(250);
            try {
                if (!lastInv.equals(c.inventory)) {
                    totalLoot++;
                    lastInv = c.inventory;
                }
            } catch (NullPointerException e) {
                lastInv = c.inventory;
            }
        }

    }

    public void goHome() {
        currentTask = "Going Home!";
        ClientContext.instance().menuActions.sendAction(315, 526, 513, 12856);
        sleep(2);
    }

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    //START: Code generated using Enfilade's Easel
    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

    private final Color color1 = new Color(255, 255, 255);

    private final Font font1 = new Font("Calibri", 1, 12);

    private final Image img1 = getImage("https://i.ibb.co/N68qBwm/u-Kraken-Slayer-Paint.png");

    public void onPaint(Graphics2D g) {
        g.drawImage(img1, 1, 302, null);
        g.setFont(font1);
        g.setColor(color1);
        g.drawString("" + formatTimeRunning(startTime), 126, 368);
        g.drawString("" + totalKills, 154, 393);
        g.drawString("" + totalLoot, 185, 423);
        g.drawString("" + formatGained(SimpleSkills.Skill.MAGIC, startMage), 395, 367);
        g.drawString("" + formatGained(SimpleSkills.Skill.HITPOINTS, startHP), 412, 393);
        g.drawString("" + currentTask, 335, 436);
    }
    //END: Code generated using Enfilade's Easel

    public String formatGained(SimpleSkills.Skill skill, int sxp) {
        int gained = c.skills.getExperience(skill) - sxp;
        String output = "";
        if (gained > 1000 && gained < 10000) { output = String.valueOf(gained).substring(0,1) + "K"; }
        else if (gained > 9999 && gained < 100000) { output = String.valueOf(gained).substring(0, 2) + "K"; }
        else if (gained > 99999 && gained < 1000000) { output = String.valueOf(gained).substring(0, 3) + "K"; }
        else if (gained > 999999 && gained < 10000000) { output = String.valueOf(gained).substring(0, 1) + "M"; }
        else if (gained > 9999999) { output = String.valueOf(gained).substring(0, 2) + "M"; }
        else { output = String.valueOf(gained); }

        return output;
    }
}
