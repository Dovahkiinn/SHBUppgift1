package com.shb.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Account {

    private final String accountName;
    private final Set<Integer> accountNumbers;

    public Account(final AccountBuilder buildAccount) {

        this.accountName =  buildAccount.accountName;
        this.accountNumbers = buildAccount.accountNumbers;
    }

    public String getAccountName() {
        return accountName;
    }

    public static class AccountBuilder {
        private final String accountName;
        private Set<Integer> accountNumbers = new HashSet<>();

        public AccountBuilder(final String accountName) {
            this.accountName = accountName;
        }

        public AccountBuilder addAccountNumber(final int accountNumber){
            accountNumbers.add(accountNumber);
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var account = (Account) o;
        return Objects.equals(accountName, account.accountName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", accountNumbers=" + accountNumbers +
                '}';
    }
}
