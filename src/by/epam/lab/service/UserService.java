package by.epam.lab.service;

import by.epam.lab.beans.User;

import java.util.Optional;

public interface UserService {
   Optional<User> getUserOnId(int id);

   void registerUser(String account);
}
