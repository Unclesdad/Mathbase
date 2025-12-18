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
        return Math.log(argument.evaluate(value)) / Math.log(base.evaluate(value)); // change of base formula 
    }

    @Override
    public Function differentiate(Parameter wrt) {
        if (isln) {
            return argument.differentiate(wrt).over(argument);
        }
        else {
            return divide(subtract(argument.simplified().differentiate(wrt).over(argument), divide(base.simplified().differentiate(wrt).times(new Logarithm(argument)), base.times(new Logarithm(base)))), new Logarithm(base)).simplified();
        }
    }

    @Override
    public boolean isConstant() {
        // TODO kinda wrong but its ok for now
        return base.isConstant() && argument.isConstant();
    }

    @Override
    public String toString() {
        if (isln) {
            return "ln(" + argument.toString() + ")";
        }
        else if (isConstant()) {
            return Double.toString(evaluate(0));
        }
        else {
            return "logbase(" + base.toString() + ", " + argument.toString() + ")";
        }
    }

    @Override
    public Function simplified() {
        if (isConstant()) {
            return just(evaluate(0));
        }
        else if (argument.identicalTo(just(1))) {
            return just(0);
        }
        else {
            return new Logarithm(base.simplified(), argument.simplified());
        }
    }

    @Override
    public boolean identicalTo(Function other) {
        if (!(other instanceof Logarithm)) return false;
        Logarithm otherLog = (Logarithm) other;
        return base.identicalTo(otherLog.base) && argument.identicalTo(otherLog.argument);
    }
}
