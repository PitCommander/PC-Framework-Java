package net.came20.pitcommander.moduleframework.command;

import java.util.HashMap;

/**
 * Created by cameronearle on 5/7/17.
 */
public class Command {
    private String command;
    private HashMap<String, Object> payload;

    public Command(String command, HashMap<String, Object> payload) {
        this.command = command;
        this.payload = payload;
    }

    public String getCommand() {
        return command;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }

}
