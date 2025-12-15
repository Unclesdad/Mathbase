public class Main {
    public static void main(String[] args) {
        Parameter x = new Parameter("x");
        Function f = new Logarithm(new Power(new Exponential(x).times(new Power(x, 2)).times(new Constant(0.0)), 3).plus(new Constant(0.0)));

        System.out.println(f);
    }
}
