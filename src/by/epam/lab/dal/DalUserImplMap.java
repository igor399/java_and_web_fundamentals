package by.epam.lab.dal;

import by.epam.lab.beans.User;

import java.util.Map;

public class DalUserImplMap implements DalUser {
    private final Map<Integer, User> userMap;

    public DalUserImplMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    @Override
    public User getUserOnId(int id) {
        return null;
    }

    @Override
    public void registerUser(String account) {

    }
}
