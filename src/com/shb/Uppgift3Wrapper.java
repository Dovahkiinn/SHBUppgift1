package com.shb;

import com.shb.model.Account;

import java.util.List;
import java.util.Random;

/**
 * Thread safe way to add account in a list.
 */
class AddAccount implements Runnable {

    private final List<Account> accounts;
    private final Object host;
    private final int maxAccounts;

    AddAccount(final List<Account> list, final int maxAccounts, final Object object) {
        this.accounts = list;
        this.host = object;
        this.maxAccounts = maxAccounts;
    }

    private void addAccounts(final int i) {
        //lock on the host to avoid concurrency issue
        synchronized (this.host) {
            final Account account = new Account
                    .AccountBuilder("Account-" + i)
                    .addAccountNumber(new Random().nextInt())
                    .build();

            accounts.add(account);
            System.out.println("Added: " + account);
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= maxAccounts; i++) {
            addAccounts(i);
        }
    }

}

/**
 * Thread safe way to print all accounts in the accounts list.
 */
class PrintAccount implements Runnable {

    private final List<Account> accounts;
    private final Object host;

    public PrintAccount(List<Account> list, Object object) {
        this.accounts = list;
        this.host = object;
    }

    private void printAccounts() {
        synchronized (this.host) {
            accounts.forEach(System.out::println);
        }
    }

    @Override
    public void run() {
        printAccounts();
    }
}

public class Uppgift3Wrapper {
}