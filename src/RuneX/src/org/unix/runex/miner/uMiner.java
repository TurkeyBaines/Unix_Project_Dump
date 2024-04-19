package org.unix.miner;

import simple.api.ClientContext;
import simple.api.queries.SimpleEntityQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleGameObject;
import simple.api.wrappers.SimpleGroundItem;
import simple.api.wrappers.SimpleGroundObject;
import simple.api.wrappers.SimpleSceneObject;

@ScriptManifest(author = "unix", name = "uMiner", category = Category.MINING, version = "0.01",
        description = "my scripts description", discord = "myDiscord#0000", servers = { "RuneX" })
public class uMiner extends Script {

    int mode; // 0 = power | 1 = bank
    ClientContext c = ctx;

    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {
        try {
            c.objects.populate();
            for (int i = 0; i < c.objects.size(); i++) {
                System.out.println("---------------------------------");
                System.out.println("\t[ROCK FOUND]");
                System.out.println("---------------------------------");
                System.out.println("|\t ID: " + c.objects.list().get(i).getId() + "\t|");
                System.out.println("|\t Name: " + c.objects.list().get(i).getName() + "\t|");
                System.out.println("|\t Loc: " + c.objects.list().get(i).getLocation() + "\t|");
                System.out.println("|\t Actions: " + "\t\t|");
                for (String s : c.objects.list().get(i).getActions()) {
                    if (s != null) {
                        System.out.println("|\t\t " + s + "\t |");
                    }
                }
                System.out.println("---------------------------------");
                System.out.println("");
            }
        } catch (NullPointerException e) {
            System.out.println(" We Major fked up!");
        }

        this.ctx.stopScript();
    }

    @Override
    public void onTerminate() {

    }
}
