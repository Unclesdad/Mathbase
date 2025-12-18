

public interface Function extends CombinationUtils {

    public double evaluate(double value);

    public Function differentiate(Parameter wrt);

    public boolean isConstant();

    public Function simplified();

    public default boolean identicalTo(double value) {
        return this.isConstant() && this.evaluate(0) == value;
    }

    public default Function plus(Function func) {
        return new Addition(this, func);
    }

    public default Function times(Function func) {
        return new Multiplication(this, func);
    }

    public default Function minus(Function func) {
        return new Addition(this, func.times(just(-1)));
    }

    public default Function over(Function func) {
        return new Multiplication(this, new Power(func, -1));
    }

    public default Function toThe(Function func) {
        return new Exponential(this, func);
    }

    public default Function toThe(double power) {
        return new Power(this, power);
    }
}