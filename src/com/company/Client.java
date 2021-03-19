package com.company;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static void Log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {

        System.in.read();

        Scanner scanner = new Scanner(System.in);

        Socket talking = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        boolean isRun;

        try {
            talking = new Socket(InetAddress.getByName("127.0.0.1"), 37152);
            Log("server connect us");

            in = new DataInputStream(talking.getInputStream());
            out = new DataOutputStream(talking.getOutputStream());

            isRun = true;
        } catch (Exception e) {
            Log("server connection failed: " + e.getMessage());
            isRun = false;
            return;
        }

        while (isRun) {
            try {
                Log("input command to server (to exit write /exit)");
                String request = scanner.nextLine();

                out.writeUTF(request);
                out.flush();

                String response = in.readUTF();
                Log("from server: " + response);

                if (request.equals("/exit")) {
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