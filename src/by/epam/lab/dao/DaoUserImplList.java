package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DaoUserImplList extends AbstractDaoUser {
    private final List<User> userList;

    public DaoUserImplList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public Optional<User> getUserOnId(int id) {
        return userList.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public Optional<User> registerUser(String account) {
        try {
            lock.lock();
            if (userList.stream().anyMatch(u -> u.getAccount().equals(account))) {
                return Optional.empty();
            } else {
                User user = new User(counter.incrementAndGet(), account);
                userList.add(user);
                return Optional.of(user);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    protected void addUser(User user) {
        userList.add(user);
    }

    @Override
    protected Collection<User> getUsers() {
        return userList;
    }
}
