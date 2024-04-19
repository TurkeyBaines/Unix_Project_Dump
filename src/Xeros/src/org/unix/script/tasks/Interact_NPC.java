package org.unix.script.tasks;

public class Interact_NPC extends Task {

    int action;
    String npcName;

    @Override
    public void run() {
        int id = c.npcs.populate().filter(npcName).nearest().next().getId();
        c.menuActions.sendAction(id, 0, 0, action);
    }

    public Interact_NPC(String NPC_Name, String NPC_Action) {
        npcName = NPC_Name;
        action = Integer.parseInt(NPC_Action);
    }

    @Override
    public String getUIOutput() {
        return "[INT_NPC] " + npcName + ", " + action;
    }
}
