package mwang.online.multiThread;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2023/3/1 17:12
 * @description: Daemon
 */
public class Daemon {


    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
        try {
            SleepUtils.second(1);
        } finally {
            System.out.println("main finally run.");
        }
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }


}
