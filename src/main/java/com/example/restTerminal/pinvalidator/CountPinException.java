package com.example.restTerminal.pinvalidator;

public class CountPinException extends Exception {
    private int countEnterPin;
    public CountPinException(){
        super("Entered your PIN more than three times");
    }
}