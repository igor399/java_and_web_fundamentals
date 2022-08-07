import by.epam.lab.beans.User;
import by.epam.lab.dao.DaoUserImplList;
import by.epam.lab.dao.DaoUserImplMap;
import by.epam.lab.service.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static by.epam.lab.constants.GlobalConstants.*;


public class TestRunner {
    private final List<String> users = Arrays.asList(
            "Alex_777", "1992JackWilson", "CrazyAndrew", "Funny_John", "Jessic@_18",
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer__Science",
            "Easy_acc", "Simple_acc", "Hard_acc", "Normal_acc", "Password1111",
            "StrAcc", "UserFromMoon", "_123", "FridayChill", "MyPass", "Hello_World"
    );

    private final List<String> varUsers = Arrays.asList(
            "Alex_777", "1992JackWilson", "CrazyAndrew", "Funny_John", "Jessic@_18",
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer_Science",
            "Easy_acc"
    );

    private final List<String> sameUsers = Arrays.asList(
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc",
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc"
    );

    private final List<String> fiveUniqueUsers = Arrays.asList(
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer__Science",
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc"
    );

    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private final UserService userServiceList = new UserServiceImpl
            (new DaoUserImplList(userList));
    private final UserService userServiceMap = new UserServiceImpl
            (new DaoUserImplMap(userMap));

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(THREADS_NUM);

    private final Random rand = new Random();

    @Test
    public void getAndRegisterUserListTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(users.size());
        users.forEach(user ->
                executorService.submit(() -> {
                    userServiceList.registerUser(user);
                    int id = rand.nextInt(users.size());
                    userServiceList.getUserOnId(id);
                    latch.countDown();
                    try {
                        Thread.sleep(rand.nextInt(1000));
                    } catch (InterruptedException ignored) {
                        //The declared exception is never thrown
                    }
                }));
        Thread.sleep(1000);
        latch.await();
        executorService.shutdown();
        Assert.assertEquals(users.size(), userList.size());
        userList.forEach(user -> Assert.assertNotEquals(0, user.getId()));
    }

    private void getAndRegisterTenUserList(List<String> users) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(varUsers.size());
        varUsers.forEach(user -> executorService.submit(() -> {
            userServiceList.registerUser(user);
            int id = rand.nextInt(varUsers.size());
            userServiceList.getUserOnId(id);
            latch.countDown();
        }));
        latch.await();
        executorService.shutdown();
    }

    @Test
    public void getTenAndRegisterUserListTest() throws InterruptedException {
        getAndRegisterTenUserList(varUsers);
        Assert.assertEquals(varUsers.size(), userList.size());
        userList.forEach(user -> Assert.assertNotEquals(0, user.getId()));
    }

    @Test
    public void getSameTenAndRegisterUserListTest() throws InterruptedException {
        getAndRegisterTenUserList(sameUsers);
        Assert.assertEquals(sameUsers.size(), userList.size());
        userList.forEach(user -> Assert.assertNotEquals(0, user.getId()));
    }

    @Test
    public void getSameFiveAndRegisterUserListTest() throws InterruptedException {
        getAndRegisterTenUserList(fiveUniqueUsers);
        Assert.assertEquals(fiveUniqueUsers.size(), userList.size());
        userList.forEach(user -> Assert.assertNotEquals(0, user.getId()));
    }


    @Test
    public void getAndRegisterUserMapTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(users.size());
        users.forEach(user -> executorService.submit(() -> {
            userServiceMap.registerUser(user);
            int id = rand.nextInt(users.size());
            userServiceMap.getUserOnId(id);
            latch.countDown();
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException ignored) {
                //The declared exception is never thrown
            }
        }));
        Thread.sleep(1000);
        latch.await();
        executorService.shutdown();
        Assert.assertEquals(users.size(), userMap.size());
        userMap.forEach((k, v) -> Assert.assertEquals(k.intValue(), v.getId()));
    }

    private void getAndRegisterTenUserMap(List<String> users) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(varUsers.size());
        varUsers.forEach(user -> executorService.submit(() -> {
            userServiceMap.registerUser(user);
            int id = rand.nextInt(varUsers.size());
            userServiceMap.getUserOnId(id);
            latch.countDown();
        }));
        latch.await();
        executorService.shutdown();
    }

    @Test
    public void getTenAndRegisterUserMapTest() throws InterruptedException {
        getAndRegisterTenUserMap(varUsers);
        Assert.assertEquals(varUsers.size(), userMap.size());
        userMap.forEach((k, v) -> Assert.assertEquals(k.intValue(), v.getId()));
    }

    @Test
    public void getSameTenAndRegisterUserMapTest() throws InterruptedException {
        getAndRegisterTenUserMap(sameUsers);
        Assert.assertEquals(sameUsers.size(), userMap.size());
        userMap.forEach((k, v) -> Assert.assertEquals(k.intValue(), v.getId()));
    }

    @Test
    public void getSameFiveAndRegisterUserMapTest() throws InterruptedException {
        getAndRegisterTenUserMap(fiveUniqueUsers);
        Assert.assertEquals(fiveUniqueUsers.size(), userMap.size());
        userMap.forEach((k, v) -> Assert.assertEquals(k.intValue(), v.getId()));
    }
}
