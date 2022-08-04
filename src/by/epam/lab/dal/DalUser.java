package by.epam.lab.dal;

import by.epam.lab.beans.User;

public interface DalUser {
    User getUserOnId(int id);

    void registerUser(String account);
}
