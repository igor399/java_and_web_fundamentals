package by.epam.lab.services;

import static by.epam.lab.services.GlobalConstants.*;

public enum RoundMethod {
    INTEGER {
        @Override
        String markToString(int mark) {
            return String.valueOf(mark / CONVECTION_FACTOR);
        }
    },
    DECIMAL {
        @Override
        String markToString(int mark) {
            return String.format(MARK_FORMAT, mark /
                    CONVECTION_FACTOR, mark % CONVECTION_FACTOR);
        }
    },
    DECIMAL_HALF {
        @Override
        String markToString(int mark) {
            String result;
            if (mark % CONVECTION_FACTOR == 0) {
                result = String.valueOf(mark / CONVECTION_FACTOR);
            } else {
                result = String.format(MARK_FORMAT, mark /
                        CONVECTION_FACTOR, mark % CONVECTION_FACTOR);
            }
            return result;
        }
    };

    abstract String markToString(int mark);

    public String getMarkToString(int mark) {
        return markToString(mark);
    }
}
