package L11.server;

public class ServerRunner {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        CalcServerA serverA = new CalcServerA(calculator,721);
        CalcServerB serverB = new CalcServerB(calculator,722);
        CalcServerC serverC = new CalcServerC(calculator,723);
        CalcServerD serverD = new CalcServerD(calculator,724);
    }
}
