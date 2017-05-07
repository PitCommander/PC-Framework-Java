package net.came20.pitcommander.moduleframework.announce;

import com.google.gson.Gson;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cameronearle on 5/7/17.
 */
public class AnnounceListener implements Runnable {
    private String address;
    private int port;
    private Gson gson = new Gson();

    public AnnounceListener(String address, int port) {
        this.address = address;
        this.port = port;
    }

    private HashMap<String, ArrayList<AnnounceTask>> listeners = new HashMap<>();

    public void addListener(String id, AnnounceTask r) {
        if (!listeners.containsKey(id)) {
            listeners.put(id, new ArrayList<>());
        }

        listeners.get(id).add(r);
    }

    private void callListeners(Announcement a) {
        if (listeners.containsKey(a.getId())) {
            ArrayList<AnnounceTask> toExec = listeners.get(a.getId());
            for (AnnounceTask r : toExec) {
                r.onAnnounce(a);
            }
        }
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.SUB);
        socket.subscribe("".getBytes());
        socket.connect("tcp://" + address + ":" + port);

        String currentData = "";
        Announcement currentAnnouncement = null;

        while (!Thread.interrupted()) {
            currentData = socket.recvStr();
            if (!currentData.equals("")) {
                currentAnnouncement = gson.fromJson(currentData, Announcement.class);
                callListeners(currentAnnouncement);
            }
        }
    }
}
