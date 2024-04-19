package org.unix.thieving;

import simple.api.coords.WorldPoint;
import simple.api.filters.SimpleSkills;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleSceneObject;

@ScriptManifest(author = "unix", name = "uThief", category = Category.MONEYMAKING, version = "0.0D",
        description = "my scripts description", discord = "myDiscord#0000", servers = { "RuneX" })
public class uThief extends Script {



    String stall1 = "Stall (Lv. 1)";
    String stall25 = "Stall (Lv. 25)";
    String stall50 = "Stall (Lv. 50)";
    String stall75 = "Stall (Lv. 75)";
    String stall99 = "Stall (Lv. 99)";

    String currentStall = "";

    int buffer = 0;

    @Override
    public boolean onExecute() {
        updateStall();
        return true;
    }

    @Override
    public void onProcess() {
        buffer++;
        if (buffer == 10) { updateStall(); buffer = 0;
            System.out.println("triggered"); }

        if (!ctx.players.getLocal().isAnimating()) {
            System.out.println(currentStall);
            System.out.println("Total Objects in Pool: " + ctx.objects.populate().filter(currentStall).size());
            for (SimpleSceneObject o : ctx.objects.populate()) {
                if (o == null || o.getName() == null) { return; }
                if (o.getName().toLowerCase().contains("stall")) {
                    System.out.println("STALL FOUND: " + o.getName() + "\t" + o.getActions() + " \t" + o.getId());
                }
            }
            SimpleSceneObject o = ctx.objects.populate().filter(currentStall).next();
            //o.interact(502);
            ctx.sleep(2500);
        }

    }

    @Override
    public void onTerminate() {

    }

    public WorldPoint getPoint(int[] loc) {
        return new WorldPoint(loc[0], loc[1], loc[2]);
    }

    public void updateStall() {
        if (ctx.skills.getLevel(SimpleSkills.Skill.THIEVING) < 25) { currentStall = stall1; return; }
        System.out.println("were not below 25");
        if (ctx.skills.getLevel(SimpleSkills.Skill.THIEVING) < 50) { currentStall = stall25; return; }
        System.out.println("were not below 50");
        if (ctx.skills.getLevel(SimpleSkills.Skill.THIEVING) < 75) { currentStall = stall50; return; }
        System.out.println("were not below 75");
        if (ctx.skills.getLevel(SimpleSkills.Skill.THIEVING) < 99) { currentStall = stall75; return; }
        System.out.println("were not below 99");
        if (ctx.skills.getLevel(SimpleSkills.Skill.THIEVING) >= 99) { currentStall = stall99; return; }
        System.out.println("we are 99.");
    }
}
