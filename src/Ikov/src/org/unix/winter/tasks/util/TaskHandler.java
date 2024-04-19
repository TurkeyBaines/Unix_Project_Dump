package org.unix.winter.tasks.util;

import java.util.HashMap;

public class TaskHandler {

    private static HashMap<String, Task> taskMap;
    private static Task currentTask;

    public TaskHandler() {
        taskMap = new HashMap<>();
    }

    public static void setTask(String s) {
        try {
            currentTask = taskMap.get(s);
        } catch (NullPointerException e) {
            System.out.println("We can't set the task to [" + s + "] because its a NPE.");
        }
    }
}
