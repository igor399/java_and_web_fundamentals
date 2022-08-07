package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DaoUserImplList implements DaoUser {
    private final ReentrantLock lock = new ReentrantLock();
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
    public Optional<User> registerUser(String account) {
        try {
            lock.lock();
            if (userList.stream().anyMatch(u -> u.getAccount().equals(account))) {
                return Optional.empty();
            } else {
                User user = new User(count.incrementAndGet(), account);
                userList.add(user);
                return Optional.of(user);
            }
        } finally {
            lock.unlock();
        }
    }
}
