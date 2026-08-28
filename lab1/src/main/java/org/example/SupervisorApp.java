package org.example;

public class SupervisorApp {
    public static void main(String[] args) throws InterruptedException {
        AbstractProgram program = new AbstractProgram();

        Thread programThread = new Thread(program, "ProgramThread");
        programThread.start();

        Supervisor supervisor = new Supervisor(program);
        Thread supervisorThread = new Thread(supervisor, "SupervisorThread");
        supervisorThread.start();

        RandomStateDaemon daemon = new RandomStateDaemon(program, 1500);
        Thread daemonThread = new Thread(daemon, "DaemonThread");
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread.sleep(12_000);

        System.out.println("\n[Main] Shutting down...\n");
        supervisor.stopProgram();

        programThread.join(2000);
        supervisorThread.join(2000);
        daemonThread.interrupt();

        System.out.printf("[Main] Final status: %s%n", program.getStatus());
        System.out.println("[Main] All threads stopped");
    }
}