package com.shb;

import com.shb.model.Account;
import com.shb.model.Accounts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Uppgift {

    public static void main(String... args) {

        uppgift1();

        uppgift2("02/02/2000");
        uppgift2("12/13/2000");
        uppgift2("2/2/2000");

        uppgift3();
    }

    private static void uppgift1() {
        // I could have done this assignment using java.util.Set to ensure uniqueness and then converting to list and
        // the simply sorting the list. Assignment told me to NOT use java.util.Map , so I am assuming Collections.sort
        // is not forbidden. Once could also use the Bubble sort algorithm but i chose to not invent the wheel.

        //The Accounts class to ensure safe way to doing additions and ensure immutability.
        final Accounts accounts = new Accounts();

        final Account account1 = new Account.AccountBuilder("Vijay")
                .addAccountNumber(100)
                .addAccountNumber(200)
                .build();

        final Account account2 = new Account.AccountBuilder("Vijay1")
                .addAccountNumber(300).addAccountNumber(400)
                .build();

        final Account account3 = new Account.AccountBuilder("Vijay2")
                .addAccountNumber(500)
                .addAccountNumber(500) //duplicate account number.. will be ignored... by design.
                .build();

        //Duplicate account (name exists).. this will be ignored. In real world, logging and proper message to user
        // would be conveyed or simply the account number will be added, depending on the requirement.
        final Account account4 = new Account.AccountBuilder("Vijay1")
                .addAccountNumber(111)
                .addAccountNumber(222)
                .build();

        // add 4 accounts , 1 is duplicate
        accounts.addAccounts(Arrays.asList(account1, account2, account3, account4));

        //And therefore only 3 would be printed.
        accounts.getAllAccounts().forEach(System.out::println);
    }


    private static void uppgift2(final String date) {

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate.parse(date, dateTimeFormatter); // we only accept date in format as described in assignment.
            System.out.println(date.replace("/", "-"));

        } catch (DateTimeParseException e) {
            System.out.println("You entered Invalid date, you naughty developer");
        }
    }

    private static void uppgift3(){

        final List<Account> accounts = new ArrayList<>();
        final Object lockObject = new Object();

        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new AddAccount(accounts, 10, lockObject));
        executorService.execute(new PrintAccount(accounts, lockObject));

        executorService.shutdown();
    }

}
