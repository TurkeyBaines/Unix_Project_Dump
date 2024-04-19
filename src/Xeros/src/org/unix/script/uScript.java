package org.unix.script;

import org.unix.script.manager.ScriptManager;
import org.unix.script.ui.SetupUI;
import simple.api.events.ChatMessageEvent;
import simple.api.listeners.SimpleMessageListener;
import simple.api.script.Category;
import simple.api.script.Script;
import simple.api.script.ScriptManifest;

@ScriptManifest(author = "unix", name = "uScript Pro", category = Category.OTHER, version = "0", description = "Makes Scripting... Simple!", discord = "", servers = {"Xeros"})
public class uScript extends Script implements SimpleMessageListener {


    public static ScriptManager sm;
    public static boolean RunScript = false;


    @Override
    public boolean onExecute() {
        sm = new ScriptManager();
        new SetupUI().setVisible(true);
        return true;
    }

    @Override
    public void onProcess() {
        if (RunScript) {
            ScriptManager.getCurrentTask().run();
        }
    }

    @Override
    public void onTerminate() {

    }


    @Override
    public void onChatMessage(ChatMessageEvent chatMessageEvent) {
        if (chatMessageEvent.getSender() == null) {
            ScriptManager.LastChatMessageRecieved = chatMessageEvent.getMessage();
        }
    }
}
