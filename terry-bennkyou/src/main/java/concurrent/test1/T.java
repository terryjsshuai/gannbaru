package concurrent.test1;

import java.util.concurrent.Phaser;

public class T {

    public static void main(String args[]) {

        final int count = 3;
        final Phaser phaser = new Phaser(count); // 总共有3个registered parties

        for (int i = 0; i < count; i++) {

            final Thread thread = new Thread(new Task(phaser));

            thread.start();
        }
    }

    public static class Task implements Runnable {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            // 每执行到这里，都会有一个party arrive，如果arrived parties等于registered parties，就往下继续执行，否则等待
            phaser.arriveAndAwaitAdvance();
        }
    }
}
