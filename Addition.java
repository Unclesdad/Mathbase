public class Addition implements Function {
    private final Function func1;
    private final Function func2;

    public Addition(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;

        // if either is equal to 0 return the other
    }

    @Override
    public Function simplified() {
        boolean func1IsZero = func1.identicalTo(0);
        boolean func2IsZero = func2.identicalTo(0);
        if (isConstant()) {
            return just(evaluate(0));
        }
        else if (func1IsZero && func2IsZero) {
            return just(0);
        }
        else if (func1IsZero) {
            return func2.simplified();
        }
        else if (func2IsZero) {
            return func1.simplified();
        }
        else {
            return new Addition(func1.simplified(), func2.simplified());
        }
    }

    @Override
    public double evaluate(double value) {
        return func1.evaluate(value) + func2.evaluate(value);
    }

    @Override
    public Function differentiate(Parameter wrt) {
        return new Addition(func1.simplified().differentiate(wrt), func2.simplified().differentiate(wrt)).simplified();
    }

    @Override
    public boolean isConstant() {
        // TODO kind of wrong. if the changing part of f1 is opposite the changing part of f2 then the sum is const. e.g. f1 = 1+x, f2 = 2-x
        return func1.isConstant() && func2.isConstant();
    }

    @Override
    public String toString() {
        return "(" + func1.toString() + " + " + func2.toString() + ")";
    }
    
}
