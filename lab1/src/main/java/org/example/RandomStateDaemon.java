package org.example;

import java.util.Random;

public class RandomStateDaemon implements Runnable {
    private final AbstractProgram program;
    private final long intervalMs;
    private final Random random = new Random();

    public RandomStateDaemon(AbstractProgram program, long intervalMs) {
        this.program = program;
        this.intervalMs = intervalMs;
    }

    @Override
    public void run() {
        System.out.println("[Daemon]   Random state daemon started");
        while (program.isActive()) {
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            synchronized (program.getMonitor()) {
                if (!program.isActive()) break;
                ProgramStatus[] statuses = ProgramStatus.values();
                ProgramStatus next = statuses[random.nextInt(statuses.length)];
                program.setStatus(next);
            }
        }
        System.out.println("[Daemon]   Random state daemon finished");
    }
}