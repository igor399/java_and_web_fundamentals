package by.epam.lab.services;

import static by.epam.lab.services.GlobalConstants.*;

public enum MarkRepresentation {

    INTEGER {
        @Override
        String markToString(int mark) {
            return String.valueOf(mark / CONVECTION_FACTOR);
        }
    },
    DECIMAL {
        @Override
        String markToString(int mark) {
            return String.format(FORMAT, mark / CONVECTION_FACTOR, mark % CONVECTION_FACTOR);
        }
    },
    DECIMAL_HALF {
        @Override
        String markToString(int mark) {
            String result;
            if (mark % CONVECTION_FACTOR == 0) {
                result = String.valueOf(mark / CONVECTION_FACTOR);
            } else {
                result = String.format(FORMAT, mark / CONVECTION_FACTOR, mark % CONVECTION_FACTOR);
            }
            return result;
        }
    };

    abstract String markToString(int mark);

    public String getStringMark(int mark) {
        return markToString(mark);
    }
}

