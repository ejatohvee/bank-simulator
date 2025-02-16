package services;

import domain.Account;
import exceptions.WithdrawMoneyException;
import utils.Logger;
import utils.StatsCollector;


public class TransactionalService {
    private final StatsCollector statsCollector;
    private final Logger logger;

    public TransactionalService(StatsCollector statsCollector, Logger logger) {
        this.statsCollector = statsCollector;
        this.logger = logger;
    }

    public void transfer(Account from, Account to, int moneyAmount) {
        long startTime = System.currentTimeMillis();
        Account first = from.getId().compareTo(to.getId()) < 0 ? from : to;
        Account second = first == from ? to : from;

        try {
            synchronized (first) {
                synchronized (second) {
                    from.withdraw(moneyAmount);
                    to.deposit(moneyAmount);
                }
            }
            statsCollector.writeSuccess(System.currentTimeMillis() - startTime);
            logger.log("Transfer successful: " + moneyAmount + " to: " + to.getId() + " from: " + from.getId());
        } catch (WithdrawMoneyException e) {
            statsCollector.writeFailure();
            logger.log("Transfer failed: " + e.getMessage());
        }
    }
}
