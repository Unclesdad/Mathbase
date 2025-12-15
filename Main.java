public class Main {
    public static void main(String[] args) {
        Parameter x = new Parameter("x");
        Function f = new Logarithm(new Power(new Exponential(x).times(new Power(x, 2)), 3));

        System.out.println(f);
    }
}
