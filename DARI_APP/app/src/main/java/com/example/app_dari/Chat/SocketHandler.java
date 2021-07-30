package com.example.app_dari.Chat;

import io.socket.client.Socket;

public class SocketHandler {
    public static synchronized Socket getSocket() {
        return socket;
    }

    public static synchronized void setSocket(Socket socket) {
        SocketHandler.socket = socket;
    }

    private static Socket socket;

}
