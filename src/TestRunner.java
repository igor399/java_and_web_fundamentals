import by.epam.lab.beans.User;
import by.epam.lab.dal.DalUserImplList;
import by.epam.lab.dal.DalUserImplMap;
import by.epam.lab.exceptions.CountDownException;
import by.epam.lab.service.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static by.epam.lab.constants.GlobalConstants.*;


public class TestRunner {
    private final List<String> users = new ArrayList<>(Arrays.asList(
            "Alex_777", "1992JackWilson", "CrazyAndrew", "Funny_John", "Jessic@_18",
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer__Science",
            "Easy_acc", "Simple_acc", "Hard_acc", "Normal_acc", "Password1111",
            "StrAcc", "UserFromMoon", "_123", "FridayChill", "MyPass", "Hello_World"
    ));

    private final List<String> varUsers = new ArrayList<>(Arrays.asList(
            "Alex_777", "1992JackWilson", "CrazyAndrew", "Funny_John", "Jessic@_18",
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer_Science",
            "Easy_acc"
    ));

    private final List<String> sameUsers = new ArrayList<>(Arrays.asList(
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc",
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc"
    ));

    private final List<String> fiveUniqueUsers = new ArrayList<>(Arrays.asList(
            "Stack_Overflow_", "TechnoWorld", "Java_Is_Best", "Computer__Science",
            "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc", "Easy_acc"
    ));

    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private final UserService userServiceList = new UserServiceImpl
            (new DalUserImplList(userList));
    private final UserService userServiceMap = new UserServiceImpl
            (new DalUserImplMap(userMap));

    private final ExecutorService addUserExecutor =
            Executors.newFixedThreadPool(NUM_ADD_EXECUTOR);
    private final ExecutorService getUserExecutor =
            Executors.newFixedThreadPool(NUM_GET_EXECUTOR);

    private final Random rand = new Random();

    @Test
    public void getAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(users.size());
        for (String user : users) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
                try {
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    //The declared exception is never thrown
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //The declared exception is never thrown
        }
        for (int i = 0; i < users.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(users.size());
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertNotNull(user.getAccount());
                    Assert.assertEquals(id, user.getId());
                }
                System.out.println(id + COLON + user);
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(users.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
        System.out.println(userList);
    }

    @Test
    public void getTenAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(varUsers.size());
        for (String user : varUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < varUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(varUsers.size());
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertNotNull(user.getAccount());
                    Assert.assertEquals(id, user.getId());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(varUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
        System.out.println(varUsers);
    }

    @Test
    public void getSameTenAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(sameUsers.size());
        for (String user : sameUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < sameUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(sameUsers.size());
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertNotNull(user.getAccount());
                    Assert.assertEquals(id, user.getId());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(sameUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
        System.out.println(sameUsers);
    }

    @Test
    public void getSameFiveAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(fiveUniqueUsers.size());
        for (String user : fiveUniqueUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < fiveUniqueUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(fiveUniqueUsers.size());
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertNotNull(user.getAccount());
                    Assert.assertEquals(id, user.getId());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(fiveUniqueUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
        System.out.println(fiveUniqueUsers);
    }

    @Test
    public void getAndRegisterUserMapTest() {
        CountDownLatch latch = new CountDownLatch(users.size());
        for (String user : users) {
            addUserExecutor.submit(() -> {
                userServiceMap.registerUser(user);
                latch.countDown();
                try {
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    //The declared exception is never thrown
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //The declared exception is never thrown
        }
        for (int i = 0; i < users.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(users.size());
                User user = userServiceMap.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
                System.out.println(id + COLON + user);
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(users.size(), userMap.size());
        userMap.forEach((k, v) -> {
            Assert.assertNotNull(k);
            Assert.assertNotNull(v);
            Assert.assertNotNull(v.getAccount());
            Assert.assertEquals(k.intValue(), v.getId());
        });
        System.out.println(userMap);
    }

    @Test
    public void getTenAndRegisterUserMapTest() {
        CountDownLatch latch = new CountDownLatch(varUsers.size());
        for (String user : varUsers) {
            addUserExecutor.submit(() -> {
                userServiceMap.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < varUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(varUsers.size());
                User user = userServiceMap.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(varUsers.size(), userMap.size());
        userMap.forEach((k, v) -> {
            Assert.assertNotNull(k);
            Assert.assertNotNull(v);
            Assert.assertNotNull(v.getAccount());
            Assert.assertEquals(k.intValue(), v.getId());
        });
        System.out.println(varUsers);
    }

    @Test
    public void getSameTenAndRegisterUserMapTest() {
        CountDownLatch latch = new CountDownLatch(sameUsers.size());
        for (String user : sameUsers) {
            addUserExecutor.submit(() -> {
                userServiceMap.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < sameUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(sameUsers.size());
                User user = userServiceMap.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(sameUsers.size(), userMap.size());
        userMap.forEach((k, v) -> {
            Assert.assertNotNull(k);
            Assert.assertNotNull(v);
            Assert.assertNotNull(v.getAccount());
            Assert.assertEquals(k.intValue(), v.getId());
        });
        System.out.println(sameUsers);
    }

    @Test
    public void getSameFiveAndRegisterUserMapTest() {
        CountDownLatch latch = new CountDownLatch(fiveUniqueUsers.size());
        for (String user : fiveUniqueUsers) {
            addUserExecutor.submit(() -> {
                userServiceMap.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < fiveUniqueUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(fiveUniqueUsers.size());
                User user = userServiceMap.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new CountDownException(THREAD_ERROR + e.getMessage());
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(fiveUniqueUsers.size(), userMap.size());
        userMap.forEach((k, v) -> {
            Assert.assertNotNull(k);
            Assert.assertNotNull(v);
            Assert.assertNotNull(v.getAccount());
            Assert.assertEquals(k.intValue(), v.getId());
        });
        System.out.println(fiveUniqueUsers);
    }
}
