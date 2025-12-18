
public class Power implements Function {
    private final double power;
    private final Function argument;

    // an optimized version of exponential for a constant, real power
    public Power(Function argument, double power) {
        this.argument = argument;
        this.power = power;
    }
    
    @Override
    public double evaluate(double value) {
        return Math.pow(this.argument.evaluate(value), power);
    }

    @Override
    public Function differentate(Parameter wrt) {
        Function differentiatedArg = argument.differentate(wrt).simplified(); // check if identically zero (const, or differentiated wrt wrong variable)
        if (differentiatedArg.isConstant() && differentiatedArg.evaluate(0) == 0) {
            return just(0);
        }
        return new Multiplication(new Constant(power), new Power(argument, power - 1)).times(differentiatedArg); // power rule (and a lil product rule)
    }

    @Override
    public String toString() {
        return "(" + argument.toString() + ")^" + Double.toString(power);
    }
    
    @Override
    public boolean isConstant() {
        return argument.isConstant();
    }

    @Override
    public Function simplified() {
        if (power == 0) {
            return just(1);
        }
        else if (power == 1) {
            return argument.simplified();
        }
        else {
            return new Power(argument.simplified(), power);
        }
    }
}
