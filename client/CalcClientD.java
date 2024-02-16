package L11.client;

import L11.Calculation;
import L11.Expression;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CalcClientD implements CalcClient {
    private CalcController controller;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public CalcClientD(String ip, int port) throws IOException {
        socket = new Socket(ip,port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        new Listener().start();
    }
    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }
    public void newCalculation(String expression) throws IOException {
        double nbr1=Double.NaN, nbr2=Double.NaN;
        char operation=' ';
        String[] parts = expression.split(",");
        try {
            nbr1 = Double.parseDouble(parts[0]);
            nbr2 = Double.parseDouble(parts[1]);
            operation = parts[2].charAt(0);
        } catch(Exception e) {
            controller.newResponse("Bad arguments: " + parts[0] + parts[2] +
                    parts[1]);
            return;
        }
        oos.writeObject(new Expression(nbr1,nbr2,operation));
        oos.flush();
    }
    private class Listener extends Thread {
        public void run() {
            Calculation response;
            try {
                while(true) {
                    response = (Calculation)ois.readObject();
                    controller.newResponse(response.getResult()+"\n"+response);
                }
            } catch(Exception e) {}
            try {
                socket.close();
            } catch(Exception e) {}
            controller.newResponse("Klient kopplar ner");
        }
    }
}
