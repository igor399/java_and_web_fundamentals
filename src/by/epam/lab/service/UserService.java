package by.epam.lab.service;

import by.epam.lab.beans.User;

public interface UserService {
   User getUserOnId(int id);

   void registerUser(String account);


}
