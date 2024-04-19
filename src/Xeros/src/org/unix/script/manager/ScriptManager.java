package org.unix.script.manager;

import org.unix.script.tasks.Task;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

import java.util.ArrayList;
import java.util.List;

public class ScriptManager {

    static int currentTaskIndex = 0;
    static Task currentTask;
    static Task previousTask;
    static Task nextTask;

    public static String LastChatMessageRecieved = "";

    public static List<Task> list;
    public static List<Vars.Area> areaList;

    public ScriptManager() {
        list = new ArrayList<>();
        areaList = new ArrayList<>();
    }

    public static void addTask(Task t) {
        list.add(t);
        System.out.println("[addTask] " + t.getClass().getName());
    }

    public static void removeTask(int index) {
        if (list.size() < index) {
            System.out.println("[removeTask] " + list.getClass().getName());
            list.remove(index);
        }
    }

    public static void addArea(String name, WorldPoint a, WorldPoint b) {
        areaList.add(new Vars.Area(name, a, b));
        System.out.println("Added " + name + " to the Area List... There are now " + areaList.size() + " entries!");
    }
    public static void removeArea(int a) {
        areaList.remove(a);
    }

    public static Vars.Area getArea(int index) {
        try {
            return areaList.get(index);
        } catch (IndexOutOfBoundsException exc) {
            return null;
        }
    }
    public static Vars.Area[] getAreas() {
        if (areaList.isEmpty()) {
            return null;
        }
        Vars.Area[] rtn = new Vars.Area[areaList.size()];
        for(int i = 0; i < rtn.length; i++) {
            rtn[i] = areaList.get(i);
        }
        return rtn;
    }

    public static Task getLastAdded() {
        return list.get(list.size()-1);
    }

    public static Task getCurrentTask() {
        return list.get(currentTaskIndex);
    }

    public static String[] getTasksAsString() {

        String[] lStr = new String[list.size()];

        for(int i = 0; i < list.size(); i++) {
            lStr[i] = list.get(i).getUIOutput();
        }

        return lStr;
    }

    public static void forceNextTask() {
        currentTask = nextTask;

        if (currentTaskIndex+1 > list.size()) {
            currentTaskIndex = 0;
        } else {
            currentTaskIndex++;
        }

        if (currentTaskIndex+1 > list.size()) {
            nextTask = list.get(0);
        } else {
            nextTask = list.get(currentTaskIndex+1);
        }
    }

    public static void repeatPreviousTask() {
        nextTask = currentTask;
        currentTask = previousTask;
    }

    public static String[] getAreasByName() {
        String[] rtn = new String[areaList.size()];

        if (areaList == null || areaList.isEmpty()) {
            rtn = new String[]{"None..."};
        }

        for (int i = 0; i < areaList.size(); i++) {
            rtn[i] = areaList.get(i).getName();
        }
        return rtn;
    }

    public static void startScript() {
        currentTask = list.get(0);
        currentTaskIndex = 0;
    }
}
