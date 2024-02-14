package L11.client;

import java.io.IOException;

public class CalcClientC implements CalcClient {
    private CalcController controller;

    public CalcClientC(String ip, int port) throws IOException {
        //TODO: Samma som i CalcClientB
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

    }
}