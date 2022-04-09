package com.example.restTerminal.pinvalidator;

public class ErrorPinException extends Exception{
    public ErrorPinException(){
        super("Uncorrected pin code");
    }
}
