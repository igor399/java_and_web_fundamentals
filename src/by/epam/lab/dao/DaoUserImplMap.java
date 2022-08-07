package by.epam.lab.dao;

import by.epam.lab.beans.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DaoUserImplMap implements DaoUser {
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicInteger count;
    private final Map<Integer, User> userMap;

    public DaoUserImplMap(Map<Integer, User> userMap) {
        this.userMap = userMap;
        count = new AtomicInteger(0);
    }

    @Override
    public User getUserOnId(int id) {
        return userMap.get(id);
    }

    @Override
    public Optional<User> registerUser(String account) {
        try {
            lock.lock();
            if(userMap.values().stream().anyMatch(u -> u.getAccount().equals(account))){
                return Optional.empty();
            }else {
                int id = count.incrementAndGet();
                User user = new User(id, account);
                userMap.put(id, user);
                return Optional.of(user);
            }
        }
        finally {
            lock.unlock();
        }
    }
}
