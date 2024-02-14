package L11.server;

public class Calculator {
    public double calculate(double nbr1, double nbr2, char operation) {
        double res = Double.NaN;
        switch (operation) {
            case '+':
                res = nbr1 + nbr2;
                break;
            case '-':
                res = nbr1 - nbr2;
                break;
            case '*':
                res = nbr1 * nbr2;
                break;
            case '/':
                res = nbr1 / nbr2;
                break;
        }
        return res;
    }
}
