package com.example.restTerminal.pinvalidator;

public class AccountIsLockedException extends Exception {
    public AccountIsLockedException(String dateLeft){
        super("Access is blocked " + dateLeft + "second(s)");
    }
}
