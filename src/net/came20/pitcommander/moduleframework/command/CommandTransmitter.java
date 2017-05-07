package net.came20.pitcommander.moduleframework.command;

import com.google.gson.Gson;
import org.zeromq.ZMQ;

import java.util.Vector;

/**
 * Created by cameronearle on 5/7/17.
 */
public class CommandTransmitter implements Runnable {
    private String address;
    private int port;
    private Gson gson = new Gson();

    public CommandTransmitter(String address, int port) {
        this.address = address;
        this.port = port;
    }

    private Vector<NetworkJob> jobQueue = new Vector<>();

    public Reply sendCommand(Command command) {
        NetworkJob job = new NetworkJob(command);
        jobQueue.add(job);
        try {
            job.latch.await();
        } catch (InterruptedException e) {}
        return job.response;
    }

    @Override
    public void run() {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://" + address + ":" + port);
        socket.setReceiveTimeOut(10000);

        NetworkJob currentJob = null;


        while (!Thread.interrupted()) {
            if (jobQueue.size() > 0) {
                currentJob = jobQueue.remove(0);
                socket.send(gson.toJson(currentJob.request));
                currentJob.response = gson.fromJson(socket.recvStr(), Reply.class);
                currentJob.latch.countDown();
            }
        }
    }
}
