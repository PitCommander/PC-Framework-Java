package net.came20.pitcommander.moduleframework.command;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cameronearle on 5/7/17.
 */
class NetworkJob {
    CountDownLatch latch = new CountDownLatch(1);
    Command request;
    Reply response;

    NetworkJob(Command request) {
        this.request = request;
    }
}
