package com.example.restTerminal.terminalserver;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(){
        super("Not enough money");
    }
}
