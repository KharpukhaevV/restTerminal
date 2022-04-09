package com.example.restTerminal.controller;

import com.example.restTerminal.pinvalidator.AccountIsLockedException;
import com.example.restTerminal.pinvalidator.CountPinException;
import com.example.restTerminal.pinvalidator.ErrorPinException;
import com.example.restTerminal.repository.RepositoryOfClient;
import com.example.restTerminal.terminal.AccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.restTerminal.terminal.OperationException;
import com.example.restTerminal.terminal.TerminalImpl;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    private final TerminalImpl terminal;
    private final RepositoryOfClient repositoryOfClient;

    @Autowired
    public TerminalController(TerminalImpl terminal, RepositoryOfClient repositoryOfClient) {
        this.terminal = terminal;
        this.repositoryOfClient = repositoryOfClient;
    }

    @GetMapping(value = "/enter-pin")
    public ResponseEntity checkPin(@RequestParam int numCard, @RequestParam short pinCode) {
        try {
            if (!terminal.getAccess(numCard, pinCode)) {
                return ResponseEntity.ok("Pin is not good " + false);
            }
        } catch (ErrorPinException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CountPinException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AccountIsLockedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Pin is good " + true);
    }

    @GetMapping(value = "/check-money")
    public ResponseEntity getSumInAccount(@RequestParam int numCard) {
        try {
            return ResponseEntity.ok("Sum in " + numCard + "" + terminal.checkSum(numCard));
        } catch (AccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/add-money")
    public ResponseEntity addMoney(@RequestParam int numCard, @RequestParam int money) {
        try {
            terminal.putMoney(numCard, money);
            return ResponseEntity.ok("Money is added");
        } catch (AccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/take-money")
    public ResponseEntity takeMoney(@RequestParam int numCard, @RequestParam int money) {
        try {
            terminal.getMoney(numCard, money);
            return ResponseEntity.ok("Money withdrawn");
        } catch (OperationException | AccessException e) {
            return ResponseEntity.ok("Money could't be withdrawn");
        }
    }

    @PostMapping(value = "/getAll")
    @ResponseBody
    public String getAll() {
        return repositoryOfClient.readAllClients().toString();
    }
}