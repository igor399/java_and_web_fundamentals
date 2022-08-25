package by.epam.lab.controller.dao;

import by.epam.lab.controller.dao.impl.*;
import by.epam.lab.exceptions.InitException;

import javax.servlet.ServletConfig;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.GlobalConstants.*;

public class NumberFactory {
    private enum Sources {
        MEMORY(NumberImplMemory::new),
        CSV(NumberImplCSV::new),
        DB(NumberImplDB::new);

//field function interface

 //constr of function
        Sources(Object aNew) {

        }


        public NumberDAO getImpl(String sourceParams, ServletConfig sc) {

        }
    }

    public NumberFactory() {
    }

    private static NumberDAO numberImpl;

    public static void init(ServletConfig sc) throws InitException {
        String param = sc.getInitParameter(FACTORY_NUM);
        String[] params = param.split(SEMICOLON, INT_LIMIT);

        String sourceType = params[0];
        String sourceParams = params.length > 1 ? params[1] : "";
        Sources source = Sources.valueOf(sourceType.toUpperCase());
        numberImpl = source.getImpl(sourceParams, sc);

    }

    public static NumberDAO getClassFromFactory() {
        return numberImpl;
    }
}
