package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DaoUserImplMap extends AbstractDaoUser {
    private final Map<Integer, User> userMap;

    public DaoUserImplMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    @Override
    public Optional<User> getUserOnId(int id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    protected void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    protected Collection<User> getUsers() {
        return userMap.values();
    }
}
