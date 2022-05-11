package by.epam.lab.services;

public class GlobalConstants {
    public final static String SEMICOLON = ";";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/coordinates";
    public final static String USER = "root";
    public final static String PASS = "password";
    public final static String FIRST_SELECT_QUERY = "SELECT ROUND(ABS(x1 - x2)) AS len, COUNT(*) " +
            "AS num FROM coordinates GROUP BY 1 ORDER BY 1";
    public final static String INSERT_QUERY = "INSERT INTO frequencies (len, num)" + "VALUES (?, ?)";
    public final static String DELETE_TABLE_QUERY = "DELETE FROM frequencies";
    public final static String SECOND_SELECT_QUERY = "SELECT * FROM frequencies WHERE len > num";
    public final static int LENGTH = 1;
    public final static int NUMBER = 2;
}
