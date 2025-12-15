public class Logarithm implements Function {
    private final Function base;
    private final Function argument;

    public Logarithm(Function base, Function argument) {
        this.base = base;
        this.argument = argument;
    }

    public Logarithm(Function argument) {
        this.base = new Constant(Math.E); // natural log
        this.argument = argument;
    }

    @Override
    public double evaluate(double value) {
        return Math.log(value) / Math.log(base.evaluate(value)); // change of base formula 
    }

    @Override
    public Function differentate() {
        return divide(subtract(argument.differentate().over(argument), divide(base.differentate().times(ln(argument)), base.times(ln(base)))), ln(base));
    }
}
