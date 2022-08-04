package by.epam.lab.service;

import by.epam.lab.beans.User;
import by.epam.lab.dal.DalUser;

public class UserServiceImpl implements UserService {
    private final DalUser dalUser;

    public UserServiceImpl(DalUser dalUser) {
        this.dalUser = dalUser;
    }

    @Override
    public User getUserOnId(int id) {
        return dalUser.getUserOnId(id);
    }

    @Override
    public void registerUser(String account) {
        dalUser.registerUser(account);
    }
}
