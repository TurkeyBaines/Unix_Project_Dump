package org.unix.runex.agility;


import org.unix.runex.agility.course.*;
import simple.api.ClientContext;
import simple.api.coords.WorldPoint;
import simple.api.filters.SimpleSkills;

import java.util.Hashtable;

public class TaskMaster {

    ClientContext c;
    WorldPoint loc;

    public Hashtable<String, Course> taskList = new Hashtable<>();

    public Course currentTask;

    public TaskMaster(ClientContext client) {
        c = client;
        taskList.put("gnome", new Gnome());
        taskList.put("draynor", new Draynor());
        taskList.put("alkharid", new Alkharid());
        taskList.put("varrock", new Varrock());
        taskList.put("canifis", new Canifis());
        taskList.put("falador", new Falador());
        taskList.put("seers", new Seers());
        taskList.put("pollnivneach", new Pollnivneach());
        taskList.put("rellekka", new Rellekka());
        taskList.put("ardougne", new Ardougne());
    }

    public void setTask(String n) {
        currentTask = taskList.get(n);
    }

    public void processTask() {

        if (!c.pathing.running() && c.pathing.energyLevel() > 50) {
            c.pathing.running();
        }

        if (!currentTask.name().equals("Ardougne")) {
            if (c.skills.getLevel(SimpleSkills.Skill.AGILITY) >= currentTask.upgradeLevel()) {
                setTask(currentTask.nextText());
                return;
            }
        }

        loc = c.players.getLocal().getLocation();
        if (Constants.HOME_AREA.containsPoint(loc)) {
            currentTask.travelTo();
            c.sleep(2500);
        } else if (currentTask.inArea()) {
            System.out.println("We are at the task!");
            currentTask.runtime();
        } else {
            currentTask.travelTo();
            c.sleep(2500);
        }
    }
}
