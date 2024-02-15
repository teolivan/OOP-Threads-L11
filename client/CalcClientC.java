package L11.client;

import java.io.*;
import java.net.Socket;

public class CalcClientC implements CalcClient {
    private CalcController controller;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;


    public CalcClientC(String ip, int port) throws IOException {
        //TODO: Samma som i CalcClientB
        socket = new Socket(ip, port);
        dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        new Listener().start();
    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    public void newCalculation(String expression) throws IOException {
        //TODO
        // Räkneuppgiften ska inte överföras som en string (expression) utan som tre enskilda objekt
        // Först ska två double-objekt skickas som innehåller båda talen och sedan ett Char-objekt som innehåller operatorn.
        // T.ex. ska ”2 + 2” skickas som writeDouble(2), writeDouble(2), writeChar(“+”)
        // Ordningen är viktig eftersom servern förväntar sig först två double och sedan en char
        // För att dela upp expression-string kan expression.split(",") användas
        // https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)

        double n1 = Double.NaN, n2 = Double.NaN;
        char operation = ' ';
        String[] parts = expression.split(",");
        try {
            n1 = Double.parseDouble(parts[0]);
            n2 = Double.parseDouble(parts[1]);
            operation = parts[2].charAt(0);
        } catch (Exception e) {
            controller.newResponse("Bad arguments: " + parts[0] + parts[2] + parts[1]);
            return;
        }
        dos.writeDouble(n1);
        dos.writeDouble(n2);
        dos.writeChar(operation);
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
            } catch (IOException e) {}
            try {
                socket.close();
            } catch (IOException e) {}
            controller.newResponse("Kopplar ner");
        }
    }
}