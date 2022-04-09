package com.example.restTerminal.terminalserver;

import com.example.restTerminal.repository.RepositoryOfClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerminalServer {

    @Autowired
    private RepositoryOfClient repositoryOfClient;

    public int checkSum(int numCard) {
        return repositoryOfClient.readClient(numCard).getBalance();
    }

    public void putMoney(int numCard, int money) {
        if (money % 100 == 0) {
            repositoryOfClient.readClient(numCard).setBalance(money);
        }
    }

    public int getMoney(int numCard, int sumMoney) throws NotEnoughMoneyException {
        if (sumMoney % 100 == 0 && sumMoney <= repositoryOfClient.readClient(numCard).getBalance()) {
            int sum = repositoryOfClient.readClient(numCard).getBalance() - sumMoney;
            repositoryOfClient.readClient(numCard).setBalance(sum);
        } else throw new NotEnoughMoneyException();
        return repositoryOfClient.readClient(numCard).getBalance();
    }
}
