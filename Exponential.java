import java.util.Optional;

public class Exponential implements Function {
    private final Function base; // base
    private final Function exponent; // exponent

    private final Optional<Function> simplified;

    private final boolean isExp;

    public Exponential(Function base, Function exponent) {
        this.base = base; 
        this.exponent = exponent;

        if (base.isConstant()) {
            double value = base.evaluate(0);
            if (Math.abs(Math.E - value) < 10e-6) {
                isExp = true; // close enough to exp
            }
            else {
                isExp = false;
            }

            if (value == 0) {
                simplified = Optional.of(just(0));
            }
            else if (value == 1) {
                simplified = Optional.of(just(1));
            }
            else {
                simplified = Optional.empty();
            }
        }
        else if (exponent.isConstant()) {
            isExp = false;
            double value = exponent.evaluate(0);
            if (value == 0) {
                simplified = Optional.of(just(1));
            }
            else if (value == 1) {
                simplified = Optional.of(base);
            }
            else {
                simplified = Optional.empty();
            }
        }
        else {
            simplified = Optional.empty();
            isExp = false;
        }
        
    }

    public Exponential(Function exponent) {
        this.base = just(Math.E);
        this.exponent = exponent;

        isExp = true;

        if (exponent.isConstant()) {
            double value = exponent.evaluate(0);
            if (value == 0) {
                simplified = Optional.of(just(1));
            }
            else if (value == 1) {
                simplified = Optional.of(base);
            }
            else {
                simplified = Optional.empty();
            }
        }
        else {
            simplified = Optional.empty();
        }
    }

    @Override
    public double evaluate(double value) {
        return Math.pow(base.evaluate(value), exponent.evaluate(value));
    }

    @Override
    public Function differentate(Parameter wrt) {
        return this.times(add(exponent.times(base.differentate(wrt).over(base)), exponent.differentate(wrt).times(ln(base))));
    }

    @Override
    public boolean isConstant() {
        // TODO kinda wrong but its ok for now
        return base.isConstant() && exponent.isConstant();
    }

    @Override
    public String toString() {
        return simplified.isPresent() ? simplified.get().toString() : (isExp ? "e" : "(" + base.toString() + ")") + "^(" + exponent.toString() + ")";
    }
}
