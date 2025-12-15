public class Addition implements Function {
    private final Function func1;
    private final Function func2;

    public Addition(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;

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
    public String toString() {
        return "(" + func1.toString() + " + " + func2.toString() + ")";
    }
    
}
