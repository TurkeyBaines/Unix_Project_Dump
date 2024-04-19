package org.unix.fuckaround;

import simple.api.queries.SimpleEntityQuery;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleSceneObject;

@ScriptManifest(author = "unix", name = "find action id", category = Category.OTHER, version = "1", description = "No Description Required", discord = "", servers = {"RuneX"})

public class D extends Script {
    @Override
    public boolean onExecute() {
        return true;
    }

    @Override
    public void onProcess() {
        int objectId = 2313; // <- The object ID to search for, and retrieve the actions from


        SimpleEntityQuery<SimpleSceneObject> fm = ctx.objects.populate().filter((n) -> n.getId() == objectId);
        if (!fm.isEmpty()) {
            SimpleSceneObject sso = fm.next();
            if (sso != null) {
                if (sso.getActions() != null) {
                    for (int i = 0; i < sso.getActions().length; i++) {
                        if (sso.getActions()[i] != null) {
                            System.out.println("Action #"+ i + " -- " + sso.getActions()[i]);
                        } else {
                            System.out.println("Action #NULL");
                        }
                    }
                } else {
                    System.out.println("SSO Actions Null");
                }
            }
        } else {
            System.out.println("No Results...");
        }

        ctx.stopScript();
    }

    @Override
    public void onTerminate() {

    }
}
