package by.epam.lab;

public enum RoundMethod {
    FLOOR {
        double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    ROUND {
        double roundFunction(double d) {
            return Math.round(d);
        }
    },
    CEIL {
        double roundFunction(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFunction(double value);

    private static int pow10(int d) {
        int result = 1;
        for (int i = 1; i <= d; i++) {
            result = result * 10;
        }
        return result;
    }

    public int round(double roundedValue, int d) {
        int tenPow = pow10(d);
        return (int) roundFunction(roundedValue / tenPow) * tenPow;
    }
}
