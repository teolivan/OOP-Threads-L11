package L11.client;

import java.io.*;
import java.net.Socket;

public class CalcClientA implements CalcClient {
    private CalcController controller;
    private String ip;
    private int port;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public CalcClientA(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    private void connect() throws IOException {
        //TODO
        // Koppla upp mot servern (ip och port)
        // Använd Timeout 5000 ms
        // Använd en kombination av DataInputStream och BufferedInputStream för att läsa från sockets InputStream
        // Använd en kombination av DataOutputStream och BufferedOutputStream för att skriva i sockets OutputStream

        socket = new Socket(ip, port);
        socket.setSoTimeout(5000);
        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

    }

    private void disconnect() throws IOException {
        //TODO: Koppla ner förbindelsen
        socket.close();
    }

    public void newCalculation(String expression) throws IOException {
        //TODO
        // 1. Koppla upp mot servern via socket (connect-metod)
        // 2. Överför en sträng (expression) med writeUTF()
        // 3. Läs serverns svar med readUTF() och visa resultatet i fönstret ( controller.newResponse() )
        // 4. Koppla ner förbindelsen (disconnect-metod)

        connect();
        dos.writeUTF(expression);
        dos.flush();
        String response = dis.readUTF();
        controller.newResponse(response);
        disconnect();
    }
}