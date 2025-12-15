public class Exponential implements Function {
    private final Function baseln; // natural log of the base
    private final Function argument; // argument of the function

    public Exponential(Function argument, Function base) {
        this.baseln = new Logarithm(base); // ln of base
        this.argument = argument;
    }

    public Exponential(Function argument) {
        this.baseln = new Constant(1); // ln(e) = 1
        this.argument = argument;
    }

    @Override
    public double evaluate(double value) {
        return Math.exp(argument.evaluate(value) * baseln.evaluate(value)); // e^(lnb * arg)
    }

    @Override
    public Function differentate() {
        return 
    }
}
