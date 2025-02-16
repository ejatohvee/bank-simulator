package utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class StatsCollector {
    private final AtomicInteger successfulTransactionsAmount = new AtomicInteger(0);
    private final AtomicInteger unsuccessfulTransactionsAmount = new AtomicInteger(0);
    private final LongAdder totalTime = new LongAdder();

    public void writeSuccess(Long duration) {
        successfulTransactionsAmount.incrementAndGet();
        totalTime.add(duration);
    }

    public void writeFailure() {
        unsuccessfulTransactionsAmount.incrementAndGet();
    }

    public void printStats() {
        System.out.printf("Successful transactions amount: %d, Errors: %d, Average time: %.3f ms",
                successfulTransactionsAmount.get(), unsuccessfulTransactionsAmount.get(),
                totalTime.doubleValue() / successfulTransactionsAmount.get());
    }
}
