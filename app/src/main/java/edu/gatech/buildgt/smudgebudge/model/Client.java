package edu.gatech.buildgt.smudgebudge.model;

import android.util.Log;

import java.io.IOException;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static final String TAG = Client.class.getSimpleName();
    public static final String SERVER_IP = "192.168.43.156"; //server IP address
    public static final int SERVER_PORT = 25565;
    private DataOutputStream out;
    private Socket socket;

    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */
    public Client() {
        new Thread(new ClientThread()).start();
    }

    /**
     * Sends the message entered by client to the server
     *
     * @param message text entered by client
     */
    public void sendMessage(final int message) {
        try {
            Log.d("Socket", socket.toString());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Log.d("Client", socket.toString());
            out.writeInt(message);
        } catch (UnknownHostException e) {
            Log.d("UnknownHost", out.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("IOException", out.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("Exception", out.toString());
            e.printStackTrace();
        }
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {
        try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                Log.d("a", "hello");
                socket = new Socket(serverAddr, SERVER_PORT);
                Log.d("Socket", socket.toString());

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

}