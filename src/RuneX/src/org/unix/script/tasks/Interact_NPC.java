package org.unix.script.tasks;

public class Interact_NPC extends Task {

    String action;
    String npcName;

    @Override
    public void run() {
    }

    public Interact_NPC(String NPC_Name, String NPC_Action) {
        npcName = NPC_Name;
        action = NPC_Action;
    }

    @Override
    public String getUIOutput() {
        return "[INT_NPC] " + npcName + ", " + action;
    }
}
