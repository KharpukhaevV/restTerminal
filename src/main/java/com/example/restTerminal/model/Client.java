package com.example.restTerminal.model;

public class Client {
    private int numCard;
    private short pin;
    private int balance;

    private int countEnterPin;
    private boolean blockAccount;
    private long startTime=0;
    private long endTime = startTime + 5000;

    public Client(int numCard, short pin, int balance) {
        this.numCard = numCard;
        this.pin = pin;
        this.balance = balance;
    }

    public int getNumCard() {
        return numCard;
    }

    public void setNumCard(int numCard) {
        this.numCard = numCard;
    }

    public short getPin() {
        return pin;
    }

    public void setPin(short pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCountEnterPin() {
        return countEnterPin;
    }

    public void setCountEnterPin(int countEnterPin) {
        this.countEnterPin = countEnterPin;
    }

    public boolean isBlockAccount() {
        return blockAccount;
    }

    public void setBlockAccount(boolean blockAccount) {
        this.blockAccount = blockAccount;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numCard=" + numCard +
                ", balance=" + balance +
                '}';
    }
}