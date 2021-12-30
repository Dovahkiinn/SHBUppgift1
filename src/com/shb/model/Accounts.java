package com.shb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Accounts implements Runnable{

    private List<Account> allAccounts = Collections.synchronizedList(new ArrayList<>());

    public void addAccounts(final List<Account> accounts){

        for(final Account account : accounts){
            if(!allAccounts.contains(account)){
                allAccounts.add(account);
            }
        }
    }

    public List<Account> getAllAccounts() {

        allAccounts.sort(Comparator.comparing(Account::getAccountName));
        return Collections.unmodifiableList(allAccounts);
    }

    @Override
    public void run() {
        System.out.println("Now the thread is running ...");
    }
}
