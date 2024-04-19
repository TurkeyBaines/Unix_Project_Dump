package org.unix;

import java.util.HashMap;

public class ScriptController {
    private static HashMap<String, Task> tasks;
    private static Task currentTask;

    public ScriptController() {
        tasks = new HashMap<>();
    }

    public void addTask(String name, Task task) {
        tasks.put(name, task);
        System.out.println("Added Task: " + name);
    }

    public void setTask(String name) {
        currentTask = tasks.get(name);
    }

    public void runTask() {
        currentTask.runtime();
    }

    public Task getTask() {
        return currentTask;
    }

}
