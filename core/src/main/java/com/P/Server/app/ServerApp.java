package com.P.Server.app;

import com.P.common.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerApp {
    public static final int TIMEOUT_MILLIS = 10000;
    private static final ArrayList<ClientConnectionThread> connections = new ArrayList<>();
    private static boolean exitFlag = false;
    private static ListenerThread listenerThread;
    private static final HashMap<String, Object> diff = new HashMap<>();
    private static UpdateThread updateThread;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void setListenerThread(ListenerThread listenerThread) {
        ServerApp.listenerThread = listenerThread;
    }

    public static void startListening() {
        if (listenerThread != null && !listenerThread.isAlive()) {
            listenerThread.start();
        } else {
            throw new IllegalStateException("Listener thread is already running or not set.");
        }
    }

    public static void setUpdateThread(UpdateThread updateThread) {
        ServerApp.updateThread = updateThread;
    }

    public static void letsPlay() {
        if(updateThread != null && !updateThread.isAlive()) {
            updateThread.start();
        } else {
            throw new IllegalStateException("Update thread is already running or not set.");
        }
    }

    public static void endGame() {
        updateThread.end();
    }

    public static void endAll() {
        exitFlag = true;
        for (ClientConnectionThread connection : connections)
            connection.end();
        connections.clear();
        listenerThread.end();
    }

    public static ArrayList<ClientConnectionThread> getConnections() {
        return connections;
    }

    public static void removeClientConnection(ClientConnectionThread connection) {
        if (connection != null) {
            connections.remove(connection);
            connection.end();
        }
    }

    public static void addClientConnection(ClientConnectionThread connection) {
        if (connection != null && !connections.contains(connection))
            connections.add(connection);
    }

    public static HashMap<String, Object> getDiff() {
        return diff;
    }
}
