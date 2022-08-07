package by.epam.lab.service;

import by.epam.lab.beans.User;
import by.epam.lab.dao.DaoUser;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final DaoUser daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public Optional<User> getUserOnId(int id) {
        return daoUser.getUserOnId(id);
    }

    @Override
    public void registerUser(String account) {
        daoUser.registerUser(account);
    }
}
