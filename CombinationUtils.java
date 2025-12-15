public interface CombinationUtils {
    public default Function add(Function func1, Function func2) {
        return new Addition(func1, func2);
    }
    
    public default Function subtract(Function func1, Function func2) {
        return new Multiplication(func1, func2.times(just(-1)));
    }

    public default Function multiply(Function func1, Function func2) {
        return new Multiplication(func1, func2);
    }

    public default Function divide(Function func1, Function func2) {
        return new Multiplication(func1, new Power(func2, -1));
    }

    public default Function exponentiate(Function base, Function exponent) {
        return new Exponential(base, exponent);
    }

    public default Constant just(double value) {
        return new Constant(value);
    }

    public default Function ln(Function argument) {
        return new Logarithm(argument);
    }
}
