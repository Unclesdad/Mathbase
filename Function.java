

public interface Function {

    public double evaluate(double value);

    public Function differentate();

    public default Function plus(Function func) {
        return new Addition(this, func);
    }

    public default Function times(Function func) {
        return new Multiplication(this, func);
    }

    public default Function minus(Function func) {
        return new Addition(this, func.times(new Constant(-1)));
    }

    public default Function over(Function func) {
        return new Multiplication(this, new Power(func, -1));
    }
}