package by.epam.lab.service;

import by.epam.lab.beans.User;
import by.epam.lab.dao.DaoUser;

public class UserServiceImpl implements UserService {
    private final DaoUser daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public User getUserOnId(int id) {
        return daoUser.getUserOnId(id);
    }

    @Override
    public void registerUser(String account) {
        daoUser.registerUser(account);
    }
}
