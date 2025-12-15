public class Parameter implements Function {
    private final String name;

    public Parameter(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(double value) {
        return value;
    }

    @Override
    public Function differentate() {
        return just(1);
    }

    @Override
    public String toString() {
        return name;
    }
    
}
