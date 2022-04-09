package com.example.restTerminal.repository;

import com.example.restTerminal.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class RepositoryOfClient {
    private ConcurrentMap<Integer, Client> concurrentMap;
    private int generateNumCard;

    private RepositoryOfClient() {
        this.concurrentMap = new ConcurrentHashMap<>();
        this.generateNumCard=1000;
        createClient(new Client(generateNumCard, (short) 1234, 1000));
        createClient(new Client(generateNumCard, (short) 1234, 2000));
        createClient(new Client(generateNumCard, (short) 1234, 3000));
    }

    public int createClient(Client client){
        concurrentMap.put(generateNumCard++, client);
        return generateNumCard;
    }

    public void updateClient(int numCard, short pin, int balance){
        Client client=concurrentMap.get(numCard);
        client.setPin(pin);
        client.setBalance(balance);
    }

    public Client readClient(int numCard){
        return concurrentMap.get(numCard);
    }

    public List<Client> readAllClients(){
        return new ArrayList<>(concurrentMap.values());
    }

    public void deleteClient(int numCard){
        concurrentMap.remove(numCard);
    }
}