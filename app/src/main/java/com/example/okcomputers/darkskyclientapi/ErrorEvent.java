package com.example.okcomputers.darkskyclientapi;

/**
 * Created by OK Computers on 10/11/2018.
 */

public class ErrorEvent {

    private final String ErrorMessage;

    public ErrorEvent(String ErrorMessage)
    {
        this.ErrorMessage = ErrorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

}
