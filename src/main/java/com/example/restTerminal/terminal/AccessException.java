package com.example.restTerminal.terminal;

public class AccessException extends Exception{
    public AccessException(){
        super("Access is denied");
    }
}