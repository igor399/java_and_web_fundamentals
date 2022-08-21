package by.epam.lab.controller.dao;

public class NumberFactory {

    private static NumberDAO numberDAO;

    public static void init(String initParameter) {

    }

    public static NumberDAO getClassFromFactory() {

        return numberDAO;
    }
}
