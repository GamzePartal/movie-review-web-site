package com.webproject.web.excepiton;

public class BaseException extends RuntimeException{


    public  BaseException(){

    }

    public  BaseException(ErrorMessage errorMessage){
        super(errorMessage.preparedErrorMessage());
    }

}
