package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractDaoUser implements DaoUser{
    protected final AtomicInteger counter;
    protected final Lock lock;

    protected AbstractDaoUser() {
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
    }

    public Optional<User> registerUser(String account) {
        try {
            lock.lock();
            if (getUsers().stream().anyMatch(u -> u.getAccount().equals(account))) {
                return Optional.empty();
            } else {
                User user = new User(counter.incrementAndGet(), account);
                addUser(user);
                return Optional.of(user);
            }
        } finally {
            lock.unlock();
        }
    }

    protected abstract void addUser(User user);

    protected abstract Collection<User> getUsers();
}
