package L11.client;

import L11.Calculation;
import L11.Expression;

import java.io.IOException;

public class CalcClientD implements CalcClient {
    private CalcController controller;

    public CalcClientD(String ip, int port) throws IOException {
        //TODO
        // Samma som i CalcClientB och CalcClientC
        // Starta en tråd (t.ex. inre klass) som lyssnar på svar från servern (readObject())
        // Obs! Typkonvertering krävs!
    }

    public void setCalcController(CalcController controller) {
        this.controller = controller;
    }

    public void newCalculation(String expression) throws IOException {
        //TODO
        // Räkneuppgiften ska inte överföras som en string (expression) utan som ett Expression-objekt (L11.Expression).
        // För att dela upp expression-string kan expression.split(",") användas precis som i CalcClientC
        // Expression-objektet ska sedan skickas med writeObject()
    }

}