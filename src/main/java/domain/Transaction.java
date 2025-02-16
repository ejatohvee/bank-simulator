package domain;

import services.TransactionalService;

public class Transaction implements Runnable {
    private final Account from;
    private final Account to;
    private final int moneyAmount;
    private final TransactionalService transactionalService;

    public Transaction(Account from, Account to, int moneyAmount, TransactionalService transactionalService) {
        this.from = from;
        this.to = to;
        this.moneyAmount = moneyAmount;
        this.transactionalService = transactionalService;
    }

    @Override
    public void run() {
        transactionalService.transfer(from, to, moneyAmount);
    }
}
