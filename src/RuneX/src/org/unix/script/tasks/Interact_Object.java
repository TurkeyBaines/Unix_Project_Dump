package org.unix.script.tasks;

public class Interact_Object extends Task {
    String action;
    String npcName;

    @Override
    public void run() {
    }

    public Interact_Object(String Obj_Name, String Obj_Action) {
        npcName = Obj_Name;
        action = Obj_Action;
    }

    @Override
    public String getUIOutput() {
        return "[INT_OBJ] " + npcName + ", " + action;
    }
}
