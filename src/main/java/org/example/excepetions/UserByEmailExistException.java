package org.example.excepetions;

import lombok.Getter;

@Getter
public class UserByEmailExistException extends Exception{

    private String message;


    public UserByEmailExistException(String message){
        super(message);
        this.message = message;
    }


}
