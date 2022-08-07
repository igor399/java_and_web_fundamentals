import by.epam.lab.beans.User;
import by.epam.lab.dao.DaoUser;
import by.epam.lab.dao.DaoUserImplList;
import by.epam.lab.dao.DaoUserImplMap;
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

//   Для тестирования Map-имплементации раскомментируйте следующие строки:
//   private final Map<Integer, User> userMap = new ConcurrentHashMap<>();
//   private final DaoUser daoUser = new DaoUserImplList(userMap);

    private final UserService userService = new UserServiceImpl(daoUser);

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUM);

    private final Random rand = new Random();

    private void getAndRegisterTenUserList(List<String> users) throws InterruptedException {
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

    private void getAndRegisterTwoUserList(List<String> users) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(users.size());
        users.forEach(user -> executorService.submit(() -> {
            userService.registerUser(user);
            latch.countDown();
        }));
        TimeUnit.MILLISECONDS.sleep(1000);
        latch.await();
        executorService.shutdown();
    }

    @Test
    public void getAndRegisterTwoVarUserListEmptyTest() throws InterruptedException {
        int expectedSize = 2;
        getAndRegisterTwoUserList(twoVarUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoSameUserListEmptyTest() throws InterruptedException {
        int expectedSize = 1;
        getAndRegisterTwoUserList(twoSameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoVarUserListTest() throws InterruptedException {
        int expectedSize = 12;
        getAndRegisterTenUserList(varUsers);
        TimeUnit.SECONDS.sleep(1);
        getAndRegisterTwoUserList(twoVarUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoSameUserListTest() throws InterruptedException {
        int expectedSize = 11;
        getAndRegisterTenUserList(varUsers);
        TimeUnit.SECONDS.sleep(1);
        getAndRegisterTwoUserList(twoSameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterOneInStorageUserListTest() throws InterruptedException {
        int expectedSize = 11;
        getAndRegisterTenUserList(varUsers);
        TimeUnit.SECONDS.sleep(1);
        getAndRegisterTwoUserList(oneInStorage);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getAndRegisterTwoInStorageUserListTest() throws InterruptedException {
        int expectedSize = 10;
        getAndRegisterTenUserList(varUsers);
        TimeUnit.SECONDS.sleep(1);
        getAndRegisterTwoUserList(twoInStorage);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getTenAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = varUsers.size();
        getAndRegisterTenUserList(varUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getSameTenAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = 1;
        getAndRegisterTenUserList(sameUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }

    @Test
    public void getSameFiveAndRegisterUserListTest() throws InterruptedException {
        int expectedSize = 6;
        getAndRegisterTenUserList(fiveUniqueUsers);
        Assert.assertEquals(userList.size(), expectedSize);
    }
}
