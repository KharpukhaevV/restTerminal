package com.example.restTerminal.pinvalidator;

import com.example.restTerminal.model.Client;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PinValidator {

    public boolean checkPin(Client client, short pinCode) throws ErrorPinException, CountPinException, AccountIsLockedException {

        long currentTime = new Date().getTime();
        if (currentTime - client.getStartTime() >= 5000) {
            client.setBlockAccount(false);
        }
        if (client.isBlockAccount()) {
            String dateLeft = Long.toString((client.getEndTime() - currentTime) / 1000);
            throw new AccountIsLockedException(dateLeft);
        }
        if (pinCode < 0) {
            client.setCountEnterPin(client.getCountEnterPin() + 1);
            throw new ErrorPinException();
        }
        if (client.getPin() == pinCode) {
            client.setCountEnterPin(0);
            return true;
        }
        client.setCountEnterPin(client.getCountEnterPin() + 1);
        if (client.getCountEnterPin() >= 3) {
            client.setBlockAccount(true);
            client.setStartTime(currentTime);
            client.setEndTime(client.getStartTime()+5000);
            throw new CountPinException();
        }

        return false;
    }
}
