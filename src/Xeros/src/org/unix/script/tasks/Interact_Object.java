package org.unix.script.tasks;

import org.unix.script.manager.ScriptManager;

public class Interact_Object extends Task {
    int action;
    String objName;

    @Override
    public void run() {
        int id = c.objects.populate().filter(objName).nearest().next().getId();
        c.menuActions.sendAction(id, 0, 0, action);
        c.sleepCondition(() -> c.players.getLocal().getAnimation() == -1, 2500);
        ScriptManager.forceNextTask();
    }

    public Interact_Object(String Obj_Name, String Obj_Action) {
        objName = Obj_Name;
        action = Integer.parseInt(Obj_Action);
    }

    @Override
    public String getUIOutput() {
        return "[INT_OBJ] " + objName + ", " + action;
    }
}
