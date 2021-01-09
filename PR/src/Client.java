

import java.io.*;
        import java.net.*;

public class Client
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;
        int port = 7777;
        String s;

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            sock = new DatagramSocket();

            InetAddress host = InetAddress.getByName("localhost");

            while(true)
            {
                echo("Enter message to send : ");
                s = (String)cin.readLine();
                byte[] b = s.getBytes();

                DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
                sock.send(dp);

                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);

                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());

                echo(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + s);
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