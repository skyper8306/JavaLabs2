package Robot;

import java.util.concurrent.Exchanger;

public class Robot extends Thread {
    private boolean leftLegAhead;
    private boolean rightLegAhead;
    Exchanger<Boolean> exchanger = new Exchanger<>();

    Thread leftLeg = new Thread(() -> {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
                exchanger.exchange(rightLegAhead);
                changeLegPosition(leftLegAhead, "left");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Thread rightLeg = new Thread(() -> {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
                changeLegPosition(rightLegAhead, "right");
                exchanger.exchange(leftLegAhead);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    void changeLegPosition(boolean leg, String name) {
        leg = !leg;
        System.out.println(name);
    }

    @Override
    public void run() {
        rightLeg.start();
        leftLeg.start();
    }
}
