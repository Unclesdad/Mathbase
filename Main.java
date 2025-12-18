public class Main {
    public static void main(String[] args) {
        Parameter x = new Parameter("x");
        Function f = new Logarithm(new Power(new Exponential(x).times(new Power(x, 2)).times(new Constant(1.0)), 3).plus(new Constant(0.0)));
        Parameter y = new Parameter("y");

        System.out.println(f.differentate(x).differentate(x).differentate(x).differentate(x).differentate(x));
    }
}
