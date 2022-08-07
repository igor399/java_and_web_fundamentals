package by.epam.lab.dao;

import by.epam.lab.beans.User;

public interface DaoUser {
    User getUserOnId(int id);

    void registerUser(String account);
}
