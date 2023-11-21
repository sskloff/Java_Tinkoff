package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortScanner {

    private PortScanner() {
    }

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    @SuppressWarnings("MultipleStringLiterals")
    private static final Map<Port, String> PORTS = Map.ofEntries(
        Map.entry(new Port(135, Port.ServerProtocol.TCP), "EPMAP"),
        Map.entry(new Port(137, Port.ServerProtocol.UDP), "Служба имен NetBIOS"),
        Map.entry(new Port(17500, Port.ServerProtocol.TCP), "Dropbox"),
        Map.entry(new Port(17500, Port.ServerProtocol.UDP), "Dropbox"));
    private static final Logger LOGGER = LogManager.getLogger();

    private static boolean isTcpOccupied(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    private static boolean isUdpOccupied(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void checkPorts() {
        for (int port = MIN_PORT; port <= MAX_PORT; port++) {
            boolean flag = false;
            if (isTcpOccupied(port)) {
                LOGGER.info("TCP Port " + port + " is occupied");
                flag = true;
            }
            if (isUdpOccupied(port)) {
                LOGGER.info("UDP Port " + port + " is occupied");
                flag = true;
            }
            if (!flag) {
                LOGGER.info("Port " + port + " is free");
            }

            Port tcp = new Port(port, Port.ServerProtocol.TCP);
            Port udp = new Port(port, Port.ServerProtocol.UDP);
            if (PORTS.containsKey(tcp)) {
                LOGGER.info("TCP Port " + port + " is potentially used by " + PORTS.get(tcp));
            }
            if (PORTS.containsKey(udp)) {
                LOGGER.info("UDP Port " + port + " is potentially used by " + PORTS.get(udp));
            }
        }
    }
}
