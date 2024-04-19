package org.unix.fighter;


import simple.api.filters.SimpleSkills;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleActor;
import simple.api.wrappers.SimpleNpc;

import java.awt.*;

@ScriptManifest(author = "unix", name = "uFighter", category = Category.COMBAT, version = "0.0D",
        description = "my scripts description", discord = "myDiscord#0000", servers = { "Zenyte" })
public class uKiller extends Script implements SimplePaintable {

    State state;
    int YAK_ID = 5529;
    int waitcycle = 0;
    SimpleActor target;
    long startTime;
    int[] startXP;
    int[] startLVL;

    @Override
    public boolean onExecute() {
        state = State.SEARCHING;
        startTime = System.currentTimeMillis();
        startXP = new int[6];
        startXP[0] = ctx.skills.getExperience(SimpleSkills.Skill.ATTACK);
        startXP[1] = ctx.skills.getExperience(SimpleSkills.Skill.STRENGTH);
        startXP[2] = ctx.skills.getExperience(SimpleSkills.Skill.DEFENCE);
        startXP[3] = ctx.skills.getExperience(SimpleSkills.Skill.RANGED);
        startXP[4] = ctx.skills.getExperience(SimpleSkills.Skill.MAGIC);
        startXP[5] = ctx.skills.getExperience(SimpleSkills.Skill.HITPOINTS);
        startLVL = new int[6];
        startLVL[0] = ctx.skills.getLevel(SimpleSkills.Skill.ATTACK);
        startLVL[1] = ctx.skills.getLevel(SimpleSkills.Skill.STRENGTH);
        startLVL[2] = ctx.skills.getLevel(SimpleSkills.Skill.DEFENCE);
        startLVL[3] = ctx.skills.getLevel(SimpleSkills.Skill.RANGED);
        startLVL[4] = ctx.skills.getLevel(SimpleSkills.Skill.MAGIC);
        startLVL[5] = ctx.skills.getLevel(SimpleSkills.Skill.HITPOINTS);

        return true;
    }


    @Override
    public void onProcess() {
        target = ctx.players.getLocal().getInteracting();

        if (target != null) {
            ctx.sleep(250);
        } else {
            ctx.npcs.populate().filter(YAK_ID).nearest().next().interact(412);
            ctx.sleep(250);
        }


    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onPaint(Graphics2D g) {
        g.drawString("IsInCombat: " + (target != null), 25, 25);
        try {
            g.drawString("Opponent HP: " + target.getHealth(), 25, 40);
        } catch (NullPointerException e) {}
        g.drawString("Time Running: " + formatTimeRunning(startTime), 25, 55);
        formatExperience(startXP, g);
    }


    public enum State {
        FIGHTING,
        SEARCHING
    }

    public String formatTimeRunning(long st) {
        long running = System.currentTimeMillis() - st;
        long millis = running % 1000;
        long second = (running / 1000) % 60;
        long minute = (running / (1000 * 60)) % 60;
        long hour = (running / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }

    public void formatExperience(int[] st, Graphics2D g) {
        String finale = "";
        int count = 0;
        int atk = ctx.skills.getExperience(SimpleSkills.Skill.ATTACK) - st[0];
        int str = ctx.skills.getExperience(SimpleSkills.Skill.STRENGTH) - st[1];
        int def = ctx.skills.getExperience(SimpleSkills.Skill.DEFENCE) - st[2];
        int rang = ctx.skills.getExperience(SimpleSkills.Skill.RANGED) - st[3];
        int mage = ctx.skills.getExperience(SimpleSkills.Skill.MAGIC) - st[4];
        int hits = ctx.skills.getExperience(SimpleSkills.Skill.HITPOINTS) - st[5];
        if (atk > 0) { g.drawString( "Attack: " + atk, 25, (70 + (15*count))); count++; }
        if (str > 0) { g.drawString( "Strength: " + str, 25, (70 + (15*count))); count++; }
        if (def > 0) { g.drawString( "Defence: " + def, 25, (70 + (15*count))); count++; }
        if (rang > 0) { g.drawString( "Range: " + rang, 25, (70 + (15*count))); count++; }
        if (mage > 0) { g.drawString( "Magic: " + mage, 25, (70 + (15*count))); count++; }
        if (hits > 0) { g.drawString( "HP: " + hits, 25, (70 + (15*count))); count++; }
    }
}
