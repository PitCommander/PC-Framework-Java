package net.came20.pitcommander.moduleframework.announce;

import java.util.HashMap;

/**
 * Created by cameronearle on 5/7/17.
 */
public class Announcement {
    private String id;
    private HashMap<String, Object> payload;

    public Announcement(String id, HashMap<String, Object> payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public HashMap<String, Object> getPayload() {
        return payload;
    }
}
