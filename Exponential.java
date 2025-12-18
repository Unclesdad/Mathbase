public class Exponential implements Function {
    private final Function base; // base
    private final Function exponent; // exponent

    private final boolean isExp;

    public Exponential(Function base, Function exponent) {
        this.base = base;
        this.exponent = exponent;

        isExp = base.identicalTo(just(Math.E));

    }

    public Exponential(Function exponent) {
        this.base = just(Math.E);
        this.exponent = exponent;

        isExp = true;
    }

    @Override
    public double evaluate(double value) {
        return Math.pow(base.evaluate(value), exponent.evaluate(value));
    }

    @Override
    public Function differentiate(Parameter wrt) {
        return this.times(add(exponent.times(base.simplified().differentiate(wrt).over(base)), exponent.simplified().differentiate(wrt).times(ln(base)))).simplified();
    }

    @Override
    public boolean isConstant() {
        // TODO kinda wrong but its ok for now
        return base.isConstant() && exponent.isConstant();
    }

    @Override
    public String toString() {
        return (isExp ? "e" : "(" + base.toString() + ")") + "^(" + exponent.toString() + ")";
    }

    @Override
    public Function simplified() {
        if (isConstant()) {
            return just(evaluate(0));
        }
        else if (base.identicalTo(just(0))) {
            return just(0);
        }
        else if (base.identicalTo(just(1))) {
            return just(1);
        }
        else if (exponent.isConstant()) {
            return new Power(base, exponent.evaluate(0)).simplified();
        }
        else {
            return new Exponential(base.simplified(),exponent.simplified());
        }
    }

    @Override
    public boolean identicalTo(Function other) {
        if (!(other instanceof Exponential)) return false;
        Exponential otherExp = (Exponential) other;
        return base.identicalTo(otherExp.base) && exponent.identicalTo(otherExp.exponent);
    }
}
