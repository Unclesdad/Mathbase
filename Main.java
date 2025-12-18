public class Main {
    public static void main(String[] args) {
        Parameter x = new Parameter("x");
        Parameter y = new Parameter("y");
        // Function f = new Logarithm(new Power(new Exponential(x).times(new Power(x, 2)).times(new Constant(1.0)), 3).plus(new Constant(0.0)));
        Function f = new Exponential(x, x.toThe(0)).plus(new Constant(0)).times(new Constant(1));

        System.out.println(f.differentate(x).differentate(x).differentate(y).simplified());
    }
}
