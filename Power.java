
public class Power implements Function {
    private final double power;
    private final Function argument;

    // an optimized version of 
    public Power(Function argument, double power) {
        this.argument = argument;
        this.power = power;
    }
    
    @Override
    public double evaluate(double value) {
        return Math.pow(this.argument.evaluate(value), power);
    }

    @Override
    public Function differentate() {
        return new Multiplication(new Constant(power), new Power(argument, power - 1)); // power rule
    }

    @Override
    public String toString() {
        return "(" + argument.toString() + ")^" + Double.toString(power);
    }
    
    @Override
    public boolean isConstant() {
        return argument.isConstant();
    }
}
