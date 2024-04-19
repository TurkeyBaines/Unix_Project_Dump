package org.unix.slayer.tasks;

import org.unix.slayer.Constants;
import org.unix.slayer.paint.Paint;
import simple.api.ClientContext;
import simple.api.coords.WorldPoint;
import simple.api.events.ChatMessageEvent;

import java.util.HashMap;

public class TaskHandler {

    HashMap<String, Task> taskList;

    ClientContext c = ClientContext.instance();
    WorldPoint loc;
    boolean firstRun = true;

    public int difficulty = -1;

    Task currentTask;

    public TaskHandler() {
        taskList = new HashMap<>();
        addTasksToPool();
    }

    public void runtime() {
        loc = c.players.getLocal().getLocation();
        System.out.println("[TaskHandler]");

        if (currentTask == null) {
            System.out.println("\t currentTask : Null");

            String RTask = c.widgets.populate().filter(16044).next().getText().split("@gre@")[1];
            System.out.println("\t RTask : " + RTask);

            if (RTask.contains("None")) {
                System.out.println("\t We need a new Task");
                // we don't actually have a task, we should get a new one!
                getNewTask();
                return;
            } else {
                System.out.println("\t We have a Task but it isn't Assigned");
                // we have a task but it isn't assigned
                int Amount = Integer.parseInt(RTask.split(" ")[0]);
                Paint.Total_Task = Amount;
                String[] Taska = RTask.split(" ");
                String Task = "";
                if (Taska.length > 2) {
                    for (int i = 1; i < Taska.length; i++) {
                        Task = Task + "" + Taska[i];
                    }
                } else { Task = Taska[1]; }
                if (Task.contains(" ")) {
                    Task.replace(" ", "");
                }
                setTask(Task.toLowerCase());
                System.out.println("\t\t setTask : " + Task.toLowerCase());
                return;
            }
        }

        if (currentTask != null) {
            System.out.println("\t We have a task, we are processing it now...");
            currentTask.process();
        }
    }

    public void getNewTask() {
        System.out.println("\t\t Getting new Task for Difficulty : " + difficulty);
        switch (difficulty) {
            case -1: // this should be an error... uninitialized value
                break;

            case 0: // Easy Difficulty
                c.menuActions.sendAction(315, 1157, 0, 54109);
                c.sleep(250);
                c.menuActions.sendAction(315, 1157, 0, 54113);
                c.sleep(250);
                break;

            case 1: // Medium Difficulty
                c.menuActions.sendAction(315, 1357, 0, 54110);
                c.sleep(250);
                c.menuActions.sendAction(315, 1157, 0, 54113);
                c.sleep(250);
                break;

            case 2: // Hard Difficulty
                c.menuActions.sendAction(315, 1357, 0, 54111);
                c.sleep(250);
                c.menuActions.sendAction(315, 1157, 0, 54113);
                c.sleep(250);
                break;

            case 3: // Brimstone Difficulty
                c.menuActions.sendAction(315, 1357, 0, 54196);
                c.sleep(250);
                c.menuActions.sendAction(315, 1157, 0, 54113);
                c.sleep(250);
                break;

            case 4:
                c.menuActions.sendAction(315, 1357, 0, 54112);
                c.sleep(250);
                c.menuActions.sendAction(315, 1157, 0, 54113);
                c.sleep(250);
                break;
        }
    }

    public void handleChatEvent(ChatMessageEvent chatMessageEvent) {
        if (chatMessageEvent.getMessageType() == 0 && chatMessageEvent.getMessage().contains("completing your task")) {
            Constants.TELE_HOME();
            Paint.TOTAL_COMPLETE += 1;
            c.sleep(1500);
            currentTask = null;
        }
    }

    public void setTask(String taskName) {
        currentTask = taskList.get(taskName);
        System.out.println("\t currentTask = taskList[" + taskName +"]");
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void addTasksToPool() {

        System.out.println("\t addTasksToPool() finished, total: " + taskList.size());
    }

}
