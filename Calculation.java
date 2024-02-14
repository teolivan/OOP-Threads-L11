package L11;

import java.io.Serializable;

public class Calculation implements Serializable {
    private static final long serialVersionUID = -7998677798120665743L;
    private double result;
    private Expression expression;

    public Calculation(double result, Expression expression) {
        this.result = result;
        this.expression = expression;
    }

    public double getResult() {
        return result;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return expression + "=" + result;
    }
}
