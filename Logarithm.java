public class Logarithm implements Function {
    private final Function base;

    public Logarithm(Function base, Function argument) {
        this.base = base;
    }

    public Logarithm(Function argument) {
        this.base = new Constant(Math.E); // natural log
    }

    @Override
    public double evaluate(double value) {
        return Math.log(value) / Math.log(base.evaluate(value)); // change of base formula
    }

    @Override
    public Function differentate() {
        return 
    }
}
