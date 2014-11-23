package com.pvl.newparser;

public class ValidationException extends Exception {

    public ValidationException() {
    }

    public ValidationException(String msg) {
        super(msg);
    }

    public String getMessage() {
        return "You have to put correct input Path";
    }

}
