package com.P.Client.app;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class ClientApp {
    public static final int TIMEOUT_MILLIS = 10000;
    private static String clientIp;
    private static int clientPort;
    private static int turnNumber;
    private static ServerConnectionThread serverConnection;
    private static AtomicBoolean startGame = new AtomicBoolean(false);
//    private static UpdateThread updateThread;
    private static boolean exitFlag = false;

    public static void initFromArgs(String[] args) throws Exception {
        String[] clientIp_Port = args[0].split(":");
        String[] serverIp_Port = args[1].split(":");

        clientIp = clientIp_Port[0];
        clientPort = parseInt(clientIp_Port[1]);
        String trackerIp = serverIp_Port[0];
        int trackerPort = parseInt(serverIp_Port[1]);
        serverConnection = new ServerConnectionThread(new Socket(trackerIp, trackerPort));
    }

    public static void connectServer() {
        if (serverConnection != null && !serverConnection.isAlive()) {
            serverConnection.start();
        } else {
            throw new IllegalStateException("server connection thread is already running or not set.");
        }
    }

    public static int getTurnNumber() {
        return turnNumber;
    }

    public static void setTurnNumber(int turnNumber) {
        ClientApp.turnNumber = turnNumber;
    }

//    public static void setUpdateThread(UpdateThread updateThread) {
//        ClientApp.updateThread = updateThread;
//    }
//
//    public static void startUpdate() {
//        if(updateThread != null && !updateThread.isAlive()) {
//            updateThread.start();
//        } else {
//            throw new IllegalStateException("Update thread is already running or not set.");
//        }
//    }

    public static String getClientIP() {
        return clientIp;
    }

    public static int getClientPort() {
        return clientPort;
    }

    public static ServerConnectionThread getServerConnection() {
        return serverConnection;
    }

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void setStartGame(boolean flag) {
        ClientApp.startGame.set(flag);
    }

    public static void endAll() {
        exitFlag = true;
        if(serverConnection != null)
            serverConnection.end();
    }

    public static String getClientIp() {
        return clientIp;
    }

    public static AtomicBoolean getStartGame() {
        return startGame;
    }

    public static boolean isExitFlag() {
        return exitFlag;
    }
}
