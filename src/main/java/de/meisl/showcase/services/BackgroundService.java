package de.meisl.showcase.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class BackgroundService {

    private final Log log = LogFactory.getLog(BackgroundService.class);

    @Async
    public CompletableFuture<String> longRunningTask(String taskName) {
//        return CompletableFuture.supplyAsync(() -> {
//            log.info("Running " + taskName);
//            try {
//                Thread.sleep(3000); // simuliert 3 Sekunden Arbeit
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            return "Successfully executed " + taskName;
//        });
        log.info("Running " + taskName);
        try {
            Thread.sleep(new Random().nextLong(1000,6000));
        } catch (InterruptedException e) {
            log.error(e.getStackTrace());
        }
        log.info("Completed " + taskName);
        return CompletableFuture.completedFuture("Successfully executed " + taskName);
    }
}
