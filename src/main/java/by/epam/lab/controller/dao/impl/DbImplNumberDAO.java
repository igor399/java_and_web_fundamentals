package by.epam.lab.controller.dao.impl;

import by.epam.lab.controller.dao.NumberDAO;
import by.epam.lab.exceptions.InitException;
import com.mysql.cj.jdbc.NonRegisteringDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.utils.GlobalConstants.*;
import static by.epam.lab.utils.SqlRequestConstants.*;

public class DbImplNumberDAO implements NumberDAO {
    private final String dbUrl;
    private final String user;
    private final String password;


    public DbImplNumberDAO(String[] param) {
        dbUrl = DB_URL + param[URL_IND];
        user = param[LOGIN_IND];
        password = param[PASS_IND];
    }

    @Override
    public List<Double> getNumbers() throws InitException {
        List<Double> numbers = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            try (Connection cn = DriverManager.getConnection(dbUrl, user, password);
                 Statement st = cn.createStatement();
                 ResultSet rs = st.executeQuery(SELECT_NUM)) {
                while (rs.next()) {
                    numbers.add(rs.getDouble(NUM_IND));
                }
            }
        } catch (SQLException  e) {
            throw new InitException(e);
        }
        return numbers;
    }
}
