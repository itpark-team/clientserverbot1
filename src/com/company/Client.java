package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    static void Log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        System.in.read();

        Scanner scanner = new Scanner(System.in);

        Socket talking = null;
        DataOutputStream out = null;
        DataInputStream in = null;

        boolean isRun;

        try {
            talking = new Socket(InetAddress.getByName("127.0.0.1"), 32467);
            Log("server connect us");

            out = new DataOutputStream(talking.getOutputStream());
            in = new DataInputStream(talking.getInputStream());

            isRun = true;
        } catch (Exception e) {
            Log("server connection error: " + e.getMessage());
            return;
        }

        while (isRun == true) {
            try {
                Log("Input command to server(or /exit): ");
                String request = scanner.nextLine();

                out.writeUTF(request);

                String response = in.readUTF();
                Log("from server: " + response);

                if (request.equals("exit") == true) {
                    isRun = false;
                }
            } catch (Exception e) {
                Log("server error: " + e.getMessage());
                isRun = false;
            }
        }
        talking.close();
        Log("client closed");
    }
}
