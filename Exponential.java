public class Exponential implements Function {
    private final Function base; // base
    private final Function exponent; // exponent

    public Exponential(Function base, Function exponent) {
        this.base = base; 
        this.exponent = exponent;
    }

    public Exponential(Function exponent) {
        this.base = new Constant(1); // ln(e) = 1
        this.exponent = exponent;
    }

    @Override
    public double evaluate(double value) {
        return Math.pow(base.evaluate(value), exponent.evaluate(value));
    }

    @Override
    public Function differentate() {
        return this.times(add(exponent.times(base.differentate().over(base)), exponent.differentate().times(ln(base))));
    }

    @Override
    public String toString() {
        return "(" + base.toString() + ")^(" + exponent.toString() + ")";
    }
}
