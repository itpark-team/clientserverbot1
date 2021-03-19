package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Server {
    static void Log(String msg)
    {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {

        System.in.read();

        ServerSocket listener = new ServerSocket(37152,12,InetAddress.getByName("127.0.0.1"));
        Log("server is started");

        Socket talking = listener.accept();
        Log("client is connected");

        DataInputStream in = new DataInputStream(talking.getInputStream());
        DataOutputStream out = new DataOutputStream(talking.getOutputStream());

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        Random rand = new Random();

        while (true){
            String request = in.readUTF();
            Log("from client: "+request);

            String response = "";

            switch (request){
                case "hello":
                    response = "hi";
                    break;
                case "/date":
                    response = dateFormatter.format(date);
                    break;
                case "/time":
                    response = timeFormatter.format(date);
                    break;
                case "/s_name":
                    response = "127.0.0.1";
                    break;
                case "/help":
                    response = "/date = show current date \n /time = show current time \n /s_name = show server name \n /help = show command list \n /rnd = show random number from 0 to 10";
                    break;
                case "/rnd":
                    response = String.valueOf((int)(Math.random() * (10 - 0 + 1) + 0));
                    break;
                case "/gamemode 1":
                    response = "set own gamemode to creative";
                    break;
                case "/gamemode 0":
                    response = "set own gamemode to survival";
                    break;
                default:
                    response = "unknown message";
                    break;
            }

            out.writeUTF(response);
            Log("to client: "+response);
            out.flush();
        }

    }
}
