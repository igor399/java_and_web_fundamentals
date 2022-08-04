package by.epam.lab.dal;

import by.epam.lab.beans.User;

import java.util.List;

public class DalUserImplList implements DalUser {
    private final List<User> userList;

    public DalUserImplList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public User getUserOnId(int id) {
        return null;
    }

    @Override
    public void registerUser(String account) {

    }
}
