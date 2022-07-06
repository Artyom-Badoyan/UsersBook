package org.example.excepetions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends Exception{

    private String message;


    public UserNotFoundException(String message){
        super(message);
        this.message = message;
    }


}
