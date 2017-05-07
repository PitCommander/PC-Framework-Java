package net.came20.pitcommander.moduleframework.announce;

/**
 * Created by cameronearle on 5/7/17.
 */
public class Announcement {
    private String id;
    private Object payload;

    public Announcement(String id, Object payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public Object getPayload() {
        return payload;
    }
}
