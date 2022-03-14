package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    Scanner scanner = new Scanner(System.in)) {
                String msg;
                while (true) {
                    System.out.println("Enter Fibonacci number: ");
                    msg = scanner.nextLine();
                    if ("end".equals(msg)) break;
                    out.println(msg);
                    System.out.println("Server Fibonacci answer: " + in.readLine());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}