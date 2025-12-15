public class Multiplication implements Function {
    private final Function func1;
    private final Function func2;

    public Multiplication(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;

        // if either is equal to 0, return constant 0
        // if either is equal to 1, return the other
    }

    @Override
    public double evaluate(double value) {
        return func1.evaluate(value) * func2.evaluate(value);
    }

    @Override
    public Function differentate() {
        return new Addition(new Multiplication(func1.differentate(), func2), new Multiplication(func1, func2.differentate())); // product rule
    }
    
}
