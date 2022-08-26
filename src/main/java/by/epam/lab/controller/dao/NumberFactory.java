package by.epam.lab.controller.dao;

import by.epam.lab.controller.dao.impl.*;
import by.epam.lab.exceptions.InitException;

import javax.servlet.ServletConfig;

import java.util.ResourceBundle;
import java.util.function.BiFunction;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.GlobalConstants.*;

public class NumberFactory {
    private enum Sources {
        MEMORY(NumberImplMemory::new),
        CSV(NumberImplCSV::new),
        DB(NumberImplDB::new);

        private final BiFunction<String, ServletConfig, NumberDAO> biFunction;

        Sources(BiFunction<String, ServletConfig, NumberDAO> biFunction) {
            this.biFunction = biFunction;
        }

        public NumberDAO getImpl(String sourceParams, ServletConfig sc) {
            return biFunction.apply(sourceParams, sc);
        }
    }

    public NumberFactory() {
    }

    private static NumberDAO numberImpl;

    public static void init(ServletConfig sc) throws InitException {
        String param = sc.getInitParameter(FACTORY_NUM);
        String[] params = param.split(SEMICOLON, INT_LIMIT);
        String sourceParams = params.length > 1 ? params[STR_SRC_IND] : EMPTY;
        String sourceType = params[SRC_IND];
        Sources source = Sources.valueOf(sourceType.toUpperCase());
        numberImpl = source.getImpl(sourceParams, sc);
    }

    public static NumberDAO getClassFromFactory() {
        return numberImpl;
    }
}
