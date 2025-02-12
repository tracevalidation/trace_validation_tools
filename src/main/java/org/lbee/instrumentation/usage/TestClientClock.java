package org.lbee.instrumentation.usage;

import java.io.IOException;
import java.util.Random;

import org.lbee.instrumentation.clock.ClientClock;

/**
 * A test client clock that requests the next time from the server clock.
 */
public class TestClientClock {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: TestClientClock <id> <host> <port>");
            return;
        }
        String id = args[0];
        String host = args[1];
        int port = Integer.parseInt(args[2]);
        System.out.println("Starting client clock for " + id + " on " + host + ":" + port);
        ClientClock clientClock = null;
        try {
            clientClock = ClientClock.getInstance(host, port);
        } catch (IOException e) {
            System.out.println("Error while creating client clock: " + e.getMessage());
        }
        if (clientClock == null) return;
        long clock = 0;
        while (true) {
            clock = clientClock.getNextTime(clock);
            System.out.println("Next time for " + id + " : " + clock);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Error while sleeping: " + e.getMessage());
            }
        }
    }
}
