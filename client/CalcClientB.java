package L11.client;

import java.io.*;
import java.net.Socket;
import java.net.http.WebSocket.Listener;

public class CalcClientB implements CalcClient {
    private CalcController controller;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public CalcClientB(String ip, int port) throws IOException {
        //TODO
        // Koppla upp mot servern (ip och port)
        // Använd Timeout 5000 ms
        // Använd en kombination av DataInputStream och BufferedInputStream för att läsa från sockets InputStream
        // Använd en kombination av DataOutputStream och BufferedOutputStream för att skriva i sockets OutputStream
        // Starta en tråd (t.ex. inre klass) som lyssnar på svar från servern (readUTF())
        socket = new Socket(ip, port);
        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        new Listener().start();

    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    public void newCalculation(String expression) throws IOException {
        //TODO: Överför en sträng (expression) med writeUTF()
        dos.writeUTF(expression);
        dos.flush();
    }

    private class Listener extends Thread {
        public void run() {
            String response;
            try {
                while(!Thread.interrupted()) {
                    response = dis.readUTF();
                    controller.newResponse(response);
                }
            } catch(IOException e) {}
            try {
                socket.close();
            } catch (IOException e) {}
            controller.newResponse("Kopplar ner");
        }

    }
}