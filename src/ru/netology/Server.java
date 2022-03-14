package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(int port) {
        try {
            ServerSocket servSocket = new ServerSocket(port);
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    try {
                        out.println(getFibonacci(Integer.parseInt(line)));
                    } catch (NumberFormatException ex) {
                        out.println("Input error");
                    }
                }
            }
        } catch (InterruptedException e) {
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private long getFibonacci(int number) {
        long fibonacci = -1;
        if (number == 0) {
            fibonacci = 0;
        } else if (number == 1) {
            fibonacci = 1;
        } else {
            long f1 = 0, f2 = 1;
            for (int i = 1; i < number; i++) {
                fibonacci = f1 + f2;
                f1 = f2;
                f2 = fibonacci;
            }
        }
        return fibonacci;
    }
}