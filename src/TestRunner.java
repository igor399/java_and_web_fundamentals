import by.epam.lab.beans.User;
import by.epam.lab.dal.DalUserImplList;
import by.epam.lab.dal.DalUserImplMap;
import by.epam.lab.service.UserServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestRunner {
    private final List<User> userList = new CopyOnWriteArrayList<>();
    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private final UserServiceImpl userServiceList = new UserServiceImpl(new DalUserImplList(userList));
    private final UserServiceImpl userServiceMap = new UserServiceImpl(new DalUserImplMap(userMap));







}
