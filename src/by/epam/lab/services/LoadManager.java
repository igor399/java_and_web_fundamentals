package by.epam.lab.services;

import by.epam.lab.beans.Result;
import by.epam.lab.dao.ResultDao;
import by.epam.lab.exceptions.ConnectionDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.lab.services.GlobalConstants.*;
import static by.epam.lab.services.SqlRequestConstants.*;

public class LoadManager {
    public static void insertResult(ResultDao resultDao)
            throws ConnectionDbException {
        Connection cn = ConnectionDbManager.CONNECTION_MANAGER.getConnection();
        try (PreparedStatement selectLogin = cn.prepareStatement(SELECT_ID_LOGIN);
             PreparedStatement selectTest = cn.prepareStatement(SELECT_ID_TEST);
             PreparedStatement insertLogin = cn.prepareStatement(INSERT_LOGIN);
             PreparedStatement insertTest = cn.prepareStatement(INSERT_TEST);
             PreparedStatement insertResult = cn.prepareStatement(INSERT_RESULT)) {
            while (resultDao.hasResult()) {
                Result result = resultDao.nextResult();
                int loginId = getInsertedId(result.getLogin(), selectLogin, insertLogin);
                int testId = getInsertedId(result.getTest(), selectTest, insertTest);
                insertResult.setInt(LOGIN_INDEX, loginId);
                insertResult.setInt(TEST_INDEX, testId);
                insertResult.setDate(DATE_INDEX, result.getDate());
                insertResult.setInt(MARK_INDEX, result.getMark());
                insertResult.addBatch();
            }
            insertResult.executeBatch();
        } catch (SQLException e) {
            throw new ConnectionDbException(e.getMessage());
        }
    }

    private static int getInsertedId(String name, PreparedStatement selectName,
                                     PreparedStatement insertName)
            throws SQLException {
        int id;
        selectName.setString(COLUMN_NAME_IND, name);
        try (ResultSet rs = selectName.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt(START_ID);
            } else {
                insertName.setString(COLUMN_NAME_IND, name);
                insertName.executeUpdate();
                try (ResultSet idResult = selectName.executeQuery()) {
                    idResult.next();
                    id = idResult.getInt(START_ID);
                }
            }
        }
        return id;
    }
}
