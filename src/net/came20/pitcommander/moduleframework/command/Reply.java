package net.came20.pitcommander.moduleframework.command;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cameronearle on 5/7/17.
 */
public class Reply {
    private String reply;
    private HashMap<String, Object> payload;

    public Reply(String reply, HashMap<String, Object> payload) {
        this.reply = reply;
        this.payload = payload;
    }

    public String getReply() {
        return reply;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }
}
