package by.epam.lab.dal;

import by.epam.lab.beans.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DalUserImplList implements DalUser {
    private final AtomicInteger count;
    private final List<User> userList;

    public DalUserImplList(List<User> userList) {
        this.userList = userList;
        count = new AtomicInteger(0);
    }

    @Override
    public User getUserOnId(int id) {
        return null;
    }

    @Override
    public void registerUser(String account) {
        userList.add(new User(count.incrementAndGet(), account));
    }
}
