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
    public Function differentiate(Parameter wrt) {
        if (wrt.equals(this)) {
            return just(1);
        }
        else {
            return just(0);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public Function simplified() {
        return this;
    }
    
}
