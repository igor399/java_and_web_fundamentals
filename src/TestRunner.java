import by.epam.lab.beans.User;
import by.epam.lab.dal.DalUserImplList;
import by.epam.lab.dal.DalUserImplMap;
import by.epam.lab.service.UserService;
import by.epam.lab.service.UserServiceImpl;
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

    private final ExecutorService addUserExecutor = Executors.newFixedThreadPool(5);
    private final ExecutorService getUserExecutor = Executors.newFixedThreadPool(5);

    Random rand = new Random();

    @Test
    public void getUserAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(users.size());
        for (String user : users) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
                try {
                    Thread.sleep(rand.nextInt(1000));
                } catch (InterruptedException ignored) {
                    //The declared exception is never thrown
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
            //The declared exception is never thrown
        }
        for (int i = 0; i < users.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(users.size()) + ID_INCR;
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
                System.out.println(id + COLON + user);
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ignored) {
            //The declared exception is never thrown
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        System.out.println(userList);
        Assert.assertEquals(users.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
    }

    @Test
    public void getTenUsersAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(varUsers.size());
        for (String user : varUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < varUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(varUsers.size()) + ID_INCR;
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ignored) {
            //The declared exception is never thrown
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(varUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
    }

    @Test
    public void getSameTenUsersAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(sameUsers.size());
        for (String user : sameUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < sameUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(varUsers.size()) + ID_INCR;
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ignored) {
            //The declared exception is never thrown
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(sameUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
    }

    @Test
    public void getSameFiveUsersAndRegisterUserListTest() {
        CountDownLatch latch = new CountDownLatch(fiveUniqueUsers.size());
        for (String user : fiveUniqueUsers) {
            addUserExecutor.submit(() -> {
                userServiceList.registerUser(user);
                latch.countDown();
            });
        }
        for (int i = 0; i < fiveUniqueUsers.size(); i++) {
            getUserExecutor.submit(() -> {
                int id = rand.nextInt(varUsers.size()) + ID_INCR;
                User user = userServiceList.getUserOnId(id);
                if (user != null) {
                    Assert.assertEquals(id, user.getId());
                    Assert.assertNotNull(user.getAccount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ignored) {
            //The declared exception is never thrown
        }
        addUserExecutor.shutdown();
        getUserExecutor.shutdown();
        Assert.assertEquals(fiveUniqueUsers.size(), userList.size());
        userList.forEach(user -> {
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getAccount());
            Assert.assertNotEquals(0, user.getId());
        });
    }


}
