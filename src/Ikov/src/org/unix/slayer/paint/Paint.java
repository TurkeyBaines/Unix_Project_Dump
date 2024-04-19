package org.unix.slayer.paint;

import org.unix.slayer.uSlayer;
import simple.api.ClientContext;
import simple.api.filters.SimpleSkills;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Paint {

    ClientContext c = ClientContext.instance();

    long startTime;
    int startXP;
    int startLvl;
    int startPoints;
    uSlayer client;
    public static String Location = "";
    public static int Total_Task = 0;
    public static int TOTAL_COMPLETE = 0;



    public Paint(uSlayer instance) {
        client = instance;
        startTime = System.currentTimeMillis();
        startXP = c.skills.getExperience(SimpleSkills.Skill.SLAYER);
        startLvl = c.skills.getLevel(SimpleSkills.Skill.SLAYER);
        startPoints = getPoints();
    }

    public void onDraw(Graphics2D g) {
        try {
            g.drawImage(img1, 1, 302, null);
            g.setFont(font1);
            g.setColor(color1);
            g.drawString(client.taskHandler.getCurrentTask().getName(), 240, 390);
            g.drawString(Location, 127, 408);
            g.drawString(getRemaining() + "/" + Total_Task, 127, 424);
            g.drawString("104 Streak", 127, 440);
            g.drawString(formatGained(startXP) + "(+" + getLevels() + ")", 426, 408);
            g.drawString("" + getPoints() + " (+" + (getPoints() - startPoints) + ")", 426, 423);
            g.drawString("" + TOTAL_COMPLETE, 426, 439);
            g.drawString(formatTimeRunning(startTime), 98, 468);
        } catch (NullPointerException e) {}
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

    private final Font font1 = new Font("Calibri", 1, 15);

    private final Image img1 = getImage("https://i.ibb.co/P9YkNMt/3G7KHgh.png");

    public int getPoints() {
        return Integer.parseInt(c.widgets.populate().filter(16043).next().getText().split("@gre@")[1]);
    }

    public int getRemaining() {
        return Integer.parseInt(c.widgets.populate().filter(16044).next().getText().split("@gre@")[1].split(" ")[0]);
    }

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public String formatGained(int sxp) {
        int gained = c.skills.getExperience(SimpleSkills.Skill.SLAYER) - sxp;
        String output = "";
        if (gained > 1000 && gained < 10000) { output = String.valueOf(gained).substring(0,1) + "K"; }
        else if (gained > 9999 && gained < 100000) { output = String.valueOf(gained).substring(0, 2) + "K"; }
        else if (gained > 99999 && gained < 1000000) { output = String.valueOf(gained).substring(0, 3) + "K"; }
        else if (gained > 999999 && gained < 10000000) { output = String.valueOf(gained).substring(0, 1) + "M"; }
        else if (gained > 9999999) { output = String.valueOf(gained).substring(0, 2) + "M"; }
        else { output = String.valueOf(gained); }

        return output;
    }

    public int getLevels() {
        return c.skills.getLevel(SimpleSkills.Skill.SLAYER) - startLvl;
    }
}
