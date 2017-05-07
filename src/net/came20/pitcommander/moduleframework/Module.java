package net.came20.pitcommander.moduleframework;

import com.google.gson.internal.LinkedTreeMap;
import net.came20.pitcommander.moduleframework.announce.AnnounceListener;
import net.came20.pitcommander.moduleframework.announce.AnnounceTask;
import net.came20.pitcommander.moduleframework.announce.Announcement;
import net.came20.pitcommander.moduleframework.command.Command;
import net.came20.pitcommander.moduleframework.command.CommandTransmitter;
import net.came20.pitcommander.moduleframework.command.Reply;

import java.util.HashMap;

/**
 * Created by cameronearle on 5/7/17.
 */
public class Module {
    private static AnnounceListener announceListener;
    private static CommandTransmitter commandTransmitter;

    public static AnnounceListener getAnnounceListener() {
        return announceListener;
    }

    public static CommandTransmitter getCommandTransmitter() {
        return commandTransmitter;
    }

    public static void main(String[] args) {
        /// NETWORK CONFIG ///
        String address = "10.0.0.2"; //Default server ip
        int port = 5800; //Default server port

        if (args.length == 1) { //We have a custom address
            address = args[0]; //Set the address
        }
        if (args.length > 1) { //We have a custom port
            try {
                port = Integer.valueOf(args[1]); //Set port
            } catch (Exception e) {}
        }

        /// SOCKET SETUP ///
        announceListener = new AnnounceListener(address, port);
        commandTransmitter = new CommandTransmitter(address, port + 1);

        /// STARTUP ///
        new Thread(commandTransmitter).start(); //Start the command transmitter
        announceListener.run(); //Start the announce listener
    }
}
