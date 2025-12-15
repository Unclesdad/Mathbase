public class Constant implements Function {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(double value) {
        return this.value;
    }

    @Override
    public Function differentate() {
        return new Constant(0);
    }

    @Override
    public boolean isConstant() {
        return true;
    }
    
    @Override
    public String toString() {
        if (value % 1 == 0) {
            return Integer.toString((int) value);
        }
        else {
        return Double.toString(value);
        }
    }

}
