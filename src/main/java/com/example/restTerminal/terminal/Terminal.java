package com.example.restTerminal.terminal;

public interface Terminal {
    int checkSum(int numCard) throws AccessException, OperationException;

    void putMoney(int numCard, int money) throws AccessException, OperationException;

    int getMoney(int numCard, int sumMoney) throws AccessException, OperationException;
}
