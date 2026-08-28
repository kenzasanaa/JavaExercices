package org.example;

public class Supervisor implements Runnable {
    private final AbstractProgram program;
    private volatile boolean shutdown = false;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    public void startProgram() {
        System.out.println("[Supervisor] Command to start program");
        program.startProgram();
    }

    public void stopProgram() {
        System.out.println("[Supervisor] Command to stop");
        shutdown = true;
        program.stopProgram();
    }

    @Override
    public void run() {
        System.out.println("[Supervisor] Supervisor started");

        synchronized (program.getMonitor()) {
            if (program.getStatus() == ProgramStatus.UNKNOWN) {
                program.startProgram();
            }

            while (!shutdown) {
                ProgramStatus current = program.getStatus();
                System.out.printf("[Supervisor] Polling status: %s%n", current);

                switch (current) {
                    case RUNNING:
                        try {
                            System.out.println("[Supervisor] Waiting for state change...");
                            program.getMonitor().wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        break;

                    case STOPPING:
                        System.out.println("[Supervisor] STOPPING detected, restarting...");
                        program.startProgram();
                        break;

                    case FATAL_ERROR:
                        System.out.println("[Supervisor] FATAL_ERROR detected, shutting down...");
                        shutdown = true;
                        program.stopProgram();
                        break;

                    case UNKNOWN:
                        System.out.println("[Supervisor] UNKNOWN detected, starting...");
                        program.startProgram();
                        break;
                }
            }
        }

        System.out.println("[Supervisor] Supervisor finished");
    }
}