import java.util.Optional;

public class Multiplication implements Function {
    private final Function func1;
    private final Function func2;

    private final Optional<Constant> coefficient;
    private final Optional<Function> singleFunction;

    public Multiplication(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;

        if (func1.isConstant()) {
            coefficient = Optional.of(just(func1.evaluate(0))); // any input value will give the same output (just choose 0)
            singleFunction = Optional.of(func2);
        }
        else if (func2.isConstant()) {
            coefficient = Optional.of(just(func2.evaluate(0)));
            singleFunction = Optional.of(func1);
        }
        else {
            coefficient = Optional.empty();
            singleFunction = Optional.empty();
        }
        // if either is equal to 0, return constant 0
        // if either is equal to 1, return the other
    }

    @Override
    public double evaluate(double value) {
        return func1.evaluate(value) * func2.evaluate(value);
    }

    @Override
    public Function differentiate(Parameter wrt) {
        return new Addition(new Multiplication(func1.simplified().differentiate(wrt), func2), new Multiplication(func1, func2.simplified().differentiate(wrt))).simplified(); // product rule
    }

    @Override
    public boolean isConstant() {
        // TODO kind of wrong. if the changing part of f1 is 1/ the changing part of f2 then the product is const. e.g. f1 = 1+x, f2 = 2/(1+x)
        return func1.isConstant() && func2.isConstant();
    }
    
    @Override
    public String toString() {
        if (coefficient.isPresent()) {
            double value = coefficient.get().evaluate(0);
            if (value == 1) {
                return singleFunction.get().toString();
            }
            else if (value == 0) {
                return "";
            }
            else {
                return coefficient.get().toString() + singleFunction.get().toString();
            }
        }
        return func1.toString() + " * " + func2.toString();
    }

    @Override
    public Function simplified() {
        if (func1.identicalTo(0) || func2.identicalTo(0)) {
            return just(0);
        }
        else if (func1.identicalTo(1)) {
            return func2.simplified();
        }
        else if (func2.identicalTo(1)) {
            return func1.simplified();
        }
        else {
            return new Multiplication(func1.simplified(), func2.simplified());
        }
    }
}
