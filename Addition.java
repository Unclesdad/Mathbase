import java.util.Optional;

public class Addition implements Function {
    private final Function func1;
    private final Function func2;

    private final Optional<Function> simplified;

    public Addition(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;

        if (func1.isConstant() && func1.evaluate(0) == 0) {
            simplified = Optional.of(func2);
        }
        else if (func2.isConstant() && func2.evaluate(0) == 0) {
            simplified = Optional.of(func1);
        }
        else {
            simplified = Optional.empty();
        }

        // if either is equal to 0 return the other
    }

    @Override
    public double evaluate(double value) {
        return func1.evaluate(value) + func2.evaluate(value);
    }

    @Override
    public Function differentate() {
        return new Addition(func1.differentate(), func2.differentate());
    }

    @Override
    public boolean isConstant() {
        // TODO kind of wrong. if the changing part of f1 is opposite the changing part of f2 then the sum is const. e.g. f1 = 1+x, f2 = 2-x
        return func1.isConstant() && func2.isConstant();
    }

    @Override
    public String toString() {
        return simplified.isPresent() ? simplified.get().toString() : "(" + func1.toString() + " + " + func2.toString() + ")";
    }
    
}
