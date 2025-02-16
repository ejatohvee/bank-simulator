import domain.Account;
import domain.Transaction;
import services.TransactionalService;
import utils.Logger;
import utils.StatsCollector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        StatsCollector statsCollector = new StatsCollector();
        Logger logger = new Logger();
        TransactionalService transactionalService = new TransactionalService(statsCollector, logger);

        Account account1 = new Account(100_000);
        Account account2 = new Account(50_000);

        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executor.submit(new Transaction(account1, account2, 50, transactionalService));
            executor.submit(new Transaction(account2, account1, 10, transactionalService));
        }

        executor.close();

        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());
        statsCollector.printStats();
    }
}
