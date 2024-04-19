package org.unix.runex.defender.tasks;

import org.unix.runex.defender.Constants;
import simple.api.ClientContext;
import simple.api.events.ChatMessageEvent;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

        List<Task> taskList;
        Task currentTask;
        ClientContext c = ClientContext.instance();

        public int total_tokens = 0;

        public TaskManager() {
            taskList = new ArrayList<>();

            taskList.add(new GetTokens(this));
            taskList.add(new FightCyclops(this));
        }

        public void processAllTasks() {
            if (currentTask == null) {
                if (Constants.GUILD_AREA[0].containsPoint(c.players.getLocal().getLocation())) {
                    currentTask = getTask("tokens");
                } else {
                    c.menuActions.sendAction(315, 0, 0, 1540);
                    c.sleep(250);
                    c.menuActions.sendAction(315, 0, 0, 18522);
                    c.sleep(250);
                    c.menuActions.sendAction(315, 0, 0, 63745);
                    c.sleep(2500);
                    currentTask = getTask("tokens");
                }
            }
            currentTask.process(this);
        }

        public void newMessage(ChatMessageEvent e) {
            if (e.getMessageType() == 0) {
                if (e.getMessage().contains("crumble to dust")) {
                    total_tokens += 10;
                }
            }
        }

        public Task getTask(String pre) {
            if (pre.equals("cyclops")) { return taskList.get(1); }
            if (pre.equals("tokens")) { return taskList.get(0); }
            return null;
        }

}
