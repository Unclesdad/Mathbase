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
    
    

}
