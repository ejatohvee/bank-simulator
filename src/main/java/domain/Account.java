package domain;

import exceptions.WithdrawMoneyException;

import java.util.UUID;

public class Account {
    private final UUID id;
    private int balance;

    public Account(int balance) {
        this.id = UUID.randomUUID();
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Deposit negative money amount.");
        balance += amount;
    }

    public synchronized void withdraw(int amount) throws WithdrawMoneyException {
        if (amount < 0) throw new IllegalArgumentException("Withdraw negative money amount.");
        if (amount > balance) throw new WithdrawMoneyException("Insufficient funds.");

        balance -= amount;
    }

    public UUID getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }
}
