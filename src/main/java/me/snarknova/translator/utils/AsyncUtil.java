package me.snarknova.translator.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncUtil {
    public static ExecutorService executor = Executors.newCachedThreadPool();

    public static void run(Runnable runnable, long delay) {
        executor.execute(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        });
    }

    public static void run(Runnable r) {
        executor.execute(r);
    }
}
