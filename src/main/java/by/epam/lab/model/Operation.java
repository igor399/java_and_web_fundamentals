package by.epam.lab.model;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public enum Operation {
    SUM(DoubleSummaryStatistics::getSum),
    MAX(DoubleSummaryStatistics::getMax),
    MIN(DoubleSummaryStatistics::getMin),
    AVG(DoubleSummaryStatistics::getAverage);

    private final Function<DoubleSummaryStatistics, Double> terminalOperation;

    Operation(Function<DoubleSummaryStatistics, Double> terminalOperation) {
        this.terminalOperation = terminalOperation;
    }

    public double getResult(double[] num) {
        return terminalOperation.apply(Arrays.stream(num).summaryStatistics());
    }
}
