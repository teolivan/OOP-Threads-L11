package L11.client;

import java.io.IOException;

public class CalcClientB implements CalcClient {
    private CalcController controller;

    public CalcClientB(String ip, int port) throws IOException {
        //TODO
        // Koppla upp mot servern (ip och port)
        // Använd Timeout 5000 ms
        // Använd en kombination av DataInputStream och BufferedInputStream för att läsa från sockets InputStream
        // Använd en kombination av DataOutputStream och BufferedOutputStream för att skriva i sockets OutputStream
        // Starta en tråd (t.ex. inre klass) som lyssnar på svar från servern (readUTF())
    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    public void newCalculation(String expression) throws IOException {
        //TODO: Överför en sträng (expression) med writeUTF()
    }
}