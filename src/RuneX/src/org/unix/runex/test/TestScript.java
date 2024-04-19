package org.unix.runex.test;

import simple.api.actions.SimpleObjectActions;
import simple.api.queries.SimpleEntityQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleGameObject;
import simple.api.wrappers.SimpleSceneObject;

@ScriptManifest(servers = {"RuneX"}, discord = "", description = "Testing Perposes", category = Category.OTHER, name = "TEST", author = "Me", version = "0.01")
public class TestScript extends Script {
    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onProcess() {
        printViaTextSearch();
    }

    public void printViaTextSearch() {
        SimpleEntityQuery<SimpleSceneObject> seq = ctx.objects.populate().filter("Dwarf multicannon");
        if (seq.size() > 0) {
            SimpleSceneObject o = ctx.objects.populate().filter("Dwarf multicannon").next();
            System.out.println("[Name: " + o.getName() + "]");
            System.out.println("[Id: " + o.getId() + "]");
            System.out.println("[ACTIONS]");
            for (int i = 0; i < o.getActions().length; i++) {
                System.out.println("\t[" + i + ": " + o.getActions()[i] + "]");
            }
            System.out.println("[Within 10 Tiles: " + o.withinRange(ctx.players.getLocal().getLocation(), 10) + "]");
            System.out.println("[Loc: " + o.getLocation().x + ".." + o.getLocation().y + ".." + o.getLocation().plane + "]");
        }
    }
}
