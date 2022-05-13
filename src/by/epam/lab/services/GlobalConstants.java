package by.epam.lab.services;

public class GlobalConstants {
    public final static String SEMICOLON = ";";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/segments";
    public final static String USER = "root";
    public final static String PASS = "root";
    public final static String SELECT_COORDINATES = "SELECT ROUND(ABS(x2 - x1)) " +
            "AS len, COUNT(*) " + "AS num FROM coordinates GROUP BY 1 ORDER BY 1";
    public final static String INSERT_FREQUENCIES = "INSERT INTO " +
            "frequencies (len, num) VALUES (?, ?)";
    public final static String DELETE_FREQUENCIES = "DELETE FROM frequencies";
    public final static String SELECT_FREQUENCIES = "SELECT * FROM " +
            "frequencies WHERE len > num";
    public final static int LEN_IND = 1;
    public final static int NUM_IND = 2;
}
