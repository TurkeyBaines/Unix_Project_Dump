package org.unix.script;

import org.unix.script.manager.ScriptManager;
import org.unix.script.ui.SetupUI;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "uScript Pro", category = Category.OTHER, version = "0", description = "Makes Scripting... Simple!", discord = "", servers = {"RuneX"})
public class uScript extends Script {


    public static ScriptManager sm;


    @Override
    public boolean onExecute() {
        sm = new ScriptManager();
        new SetupUI().setVisible(true);
        return true;
    }

    @Override
    public void onProcess() {

    }

    @Override
    public void onTerminate() {

    }



}
