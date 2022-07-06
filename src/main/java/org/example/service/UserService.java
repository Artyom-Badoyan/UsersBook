package org.example.service;

import org.example.excepetions.UserByEmailExistException;
import org.example.excepetions.UserNotFoundException;
import org.example.manager.UserManager;
import org.example.model.User;

public class UserService {


    private final UserManager userManager = new UserManager();


    public User register(User newUser) throws UserByEmailExistException {
        if (userManager.existByEmail(newUser.getEmail())) {
            throw new UserByEmailExistException(String.format("User with %s already exists", newUser.getEmail()));
        }
        return userManager.add(newUser);
    }


    public User getByEmailAndPass(String email, String password) throws UserNotFoundException {

       User user =  userManager.getByEmailAndPassword(email, password);
       if(user.getId()==0){
           throw new UserNotFoundException(String.format("User with email = %s and password = %s not found", email, password));
       }
       return user;
    }
}
