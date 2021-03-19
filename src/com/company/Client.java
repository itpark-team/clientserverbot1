package com.company;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    static void Log(String msg)
    {
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException
    {
        System.in.read();

        Scanner scanner = new Scanner(System.in);

        Socket talking = new Socket(InetAddress.getByName("127.0.0.1"),32467);
        Log("server connect us");

        DataOutputStream out = new DataOutputStream(talking.getOutputStream());
        DataInputStream in = new DataInputStream(talking.getInputStream());

        while(true)
        {
            try
            {
                Log("Input command to server: ");
                String request = scanner.nextLine();

                out.writeUTF(request);
                //out.flush();

                String response = in.readUTF();
                Log("from server: "+response);
            }
            catch (Exception e)
            {
                Log(e.getMessage());
            }
        }
    }
}
