package org.example;

public class AbstractProgram implements Runnable {
    private ProgramStatus status = ProgramStatus.UNKNOWN;
    private final Object monitor = new Object();
    private volatile boolean active = true;

    public ProgramStatus getStatus() {
        synchronized (monitor) {
            return status;
        }
    }

    public void setStatus(ProgramStatus newStatus) {
        synchronized (monitor) {
            if (this.status != newStatus) {
                System.out.printf("[Program]  >>> Status changed: %s -> %s%n", this.status, newStatus);
                this.status = newStatus;
                monitor.notifyAll();
            }
        }
    }

    public void startProgram() {
        synchronized (monitor) {
            if (status == ProgramStatus.UNKNOWN || status == ProgramStatus.STOPPING) {
                System.out.println("[Program]  >>> Starting program...");
                status = ProgramStatus.RUNNING;
                monitor.notifyAll();
            }
        }
    }

    public void stopProgram() {
        synchronized (monitor) {
            active = false;
            System.out.println("[Program]  >>> Program stopped by supervisor");
            monitor.notifyAll();
        }
    }

    public Object getMonitor() {
        return monitor;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        System.out.println("[Program]  Worker thread started");
        while (active) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[Program]  Worker thread finished");
    }
}