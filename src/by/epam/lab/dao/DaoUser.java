package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.Optional;

public interface DaoUser {
    User getUserOnId(int id);

    Optional<User> registerUser(String account);
}
