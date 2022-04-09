package com.example.restTerminal.terminal;

import com.example.restTerminal.pinvalidator.AccountIsLockedException;
import com.example.restTerminal.pinvalidator.CountPinException;
import com.example.restTerminal.pinvalidator.ErrorPinException;
import com.example.restTerminal.pinvalidator.PinValidator;
import com.example.restTerminal.repository.RepositoryOfClient;
import com.example.restTerminal.terminalserver.NotEnoughMoneyException;
import com.example.restTerminal.terminalserver.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final RepositoryOfClient repositoryOfClient;
    private boolean flag;

    @Autowired
    public TerminalImpl(TerminalServer server, PinValidator pinValidator, RepositoryOfClient repositoryOfClient) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.repositoryOfClient = repositoryOfClient;
    }

    public boolean getAccess(int numCard, short pin) throws ErrorPinException, CountPinException, AccountIsLockedException {
        flag = pinValidator.checkPin(repositoryOfClient.readClient(numCard), pin);
        return flag;
    }

    @Override
    public int checkSum(int numCard) throws AccessException {
        if (!flag) {
            throw new AccessException();
        }
        return server.checkSum(numCard);
    }

    @Override
    public void putMoney(int numCard, int money) throws AccessException {
        if (!flag) {
            throw new AccessException();
        }
        server.putMoney(numCard, money);
    }

    @Override
    public int getMoney(int numCard, int sumMoney) throws OperationException, AccessException {
        if (!flag) {
            throw new AccessException();
        }
        try {
            return server.getMoney(numCard, sumMoney);
        } catch (NotEnoughMoneyException e) {
            throw new OperationException("Sorry, but you don't have enough money");
        }
    }
}