package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DaoUserImplList implements DaoUser {
    private final AtomicInteger count;
    private final List<User> userList;

    public DaoUserImplList(List<User> userList) {
        this.userList = userList;
        count = new AtomicInteger(0);
    }

    @Override
    public User getUserOnId(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return new User();
    }

    @Override
    public void registerUser(String account) {
        for (User user : userList) {
            if (user.equals(account)) {
                userList.add(new User());
            }
        }
        userList.add(new User(count.incrementAndGet(), account));
    }
}
