import by.epam.lab.beans.User;
import by.epam.lab.dao.DaoUser;
import by.epam.lab.dao.DaoUserImplList;
import by.epam.lab.service.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static by.epam.lab.constants.GlobalConstants.*;


public class TestRunner {
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

    private final List<String> twoVarUsers = Arrays.asList("First_user", "Second_user");
    private final List<String> twoSameUsers = Arrays.asList("First_user", "First_user");
    private final List<String> oneInStorage = Arrays.asList("Alex_777", "Second_user");
    private final List<String> twoInStorage = Arrays.asList("Alex_777", "1992JackWilson");

    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final DaoUser daoUser = new DaoUserImplList(userList);

//   To test the Map implementation, uncomment the following lines:
//   private final Map<Integer, User> userMap = new ConcurrentHashMap<>();
//   private final DaoUser daoUser = new DaoUserImplList(userMap);

    private final UserService userService = new UserServiceImpl(daoUser);

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUM);

    private final Random rand = new Random();

    private void registerTenUserList(List<String> users) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(users.size());
        users.forEach(user -> executorService.submit(() -> {
            userService.registerUser(user);
            int id = rand.nextInt(varUsers.size());
            userService.getUserOnId(id);
            latch.countDown();
        }));
        latch.await();
        executorService.shutdown();
    }

    private void registerTwoUserList(List<String> users) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(users.size());
        users.forEach(user -> executorService.submit(() -> {
            userService.registerUser(user);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException ignored) {
              //The declared exception never thrown
            }
            latch.countDown();
        }));
        latch.await();
        executorService.shutdown();
    }

    private void registerTwoUserListWithFillModel(List<String> users) throws InterruptedException {
        varUsers.forEach(userService::registerUser);
        registerTwoUserList(users);
    }

    @Test
    public void getAndRegisterTwoVarUserListEmptyTest() throws InterruptedException {
        int expectedSize = 2;
        registerTwoUserList(twoVarUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoSameUserListEmptyTest() throws InterruptedException {
        int expectedSize = 1;
        registerTwoUserList(twoSameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoVarUserListTest() throws InterruptedException {
        int expectedSize = 12;
        registerTwoUserListWithFillModel(twoVarUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoSameUserListTest() throws InterruptedException {
        int expectedSize = 11;
        registerTwoUserListWithFillModel(twoSameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterOneInStorageUserListTest() throws InterruptedException {
        int expectedSize = 11;
        registerTwoUserListWithFillModel(oneInStorage);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoInStorageUserListTest() throws InterruptedException {
        int expectedSize = 10;
        registerTwoUserListWithFillModel(twoInStorage);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getTenAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = varUsers.size();
        registerTenUserList(varUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getSameTenAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = 1;
        registerTenUserList(sameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getSameFiveAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = 5;
        registerTenUserList(fiveUniqueUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }
}
