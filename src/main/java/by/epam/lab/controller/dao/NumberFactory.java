package by.epam.lab.controller.dao;

import by.epam.lab.controller.dao.impl.*;

import static by.epam.lab.utils.GlobalConstants.*;

public class NumberFactory {
    private enum Source {
        MEMORY {
            @Override
            NumberDAO getNumberDAO(String[] param) {
                return new MemoryImplNumberDAO();
            }
        },
        CSV {
            @Override
            NumberDAO getNumberDAO(String[] param) {
                return new CsvImplNumberDAO(param);
            }
        },
        DB {
            @Override
            NumberDAO getNumberDAO(String[] param) {
                return new DbImplNumberDAO(param);
            }
        };

        abstract NumberDAO getNumberDAO(String[] param);
    }

    private static NumberDAO numberDAO;

    public NumberFactory() {
    }

    public static void init(String initParameter) {
        String[] param = initParameter.split(SEMICOLON);
        Source source = Source.valueOf(param[SRC_IND].toUpperCase());
        numberDAO = source.getNumberDAO(param);
    }

    public static NumberDAO getClassFromFactory() {
        return numberDAO;
    }
}
