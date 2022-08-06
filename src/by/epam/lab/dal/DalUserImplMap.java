package by.epam.lab.dal;

import by.epam.lab.beans.User;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DalUserImplMap implements DalUser {
    private final AtomicInteger count;
    private final Map<Integer, User> userMap;

    public DalUserImplMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
        count = new AtomicInteger(0);
    }

    @Override
    public User getUserOnId(int id) {
        return userMap.get(id);
    }

    @Override
    public void registerUser(String account) {
        int key = count.incrementAndGet();
        userMap.put(key, new User(key, account));
    }
}
