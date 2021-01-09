

import java.io.*;
        import java.net.*;

public class Server
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;

        try
        {
            sock = new DatagramSocket(7777);
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            echo("Server socket created. Waiting for incoming data...");
            while(true)
            {
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                echo(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);

                s = "OK : " + s;
                DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
                sock.send(dp);
            }
        }

        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }

    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}