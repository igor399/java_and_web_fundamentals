package by.epam.lab.model;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;

public enum Operation {
    SUM(Double::sum),
    MAX(Double::max),
    MIN(Double::min),
    AVG(Double::sum);

    private final DoubleBinaryOperator binaryOperator;

    Operation(DoubleBinaryOperator binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    public double getResult(double[] num) {
        double res = Arrays.stream(num).reduce(binaryOperator).getAsDouble();
        if (this == AVG) {
            res /= num.length;
        }
        return res;
    }
}
