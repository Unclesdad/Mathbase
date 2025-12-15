public class Logarithm implements Function {
    private final Function base;
    private final Function argument;
    private final boolean isln;

    public Logarithm(Function base, Function argument) {
        this.base = base;
        this.argument = argument;
        isln = false;
    }

    public Logarithm(Function argument) {
        this.base = new Constant(Math.E); // natural log
        this.argument = argument;
        isln = true;
    }

    @Override
    public double evaluate(double value) {
        return Math.log(value) / Math.log(base.evaluate(value)); // change of base formula 
    }

    @Override
    public Function differentate() {
        return divide(subtract(argument.differentate().over(argument), divide(base.differentate().times(new Logarithm(argument)), base.times(new Logarithm(base)))), new Logarithm(base));
    }

    @Override
    public String toString() {
        if (isln) {
            return "ln(" + argument.toString() + ")";
        }
        else {
            return "logbase(" + base.toString() + ", " + argument.toString() + ")";
        }
    }
}
