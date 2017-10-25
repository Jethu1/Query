package TreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 11:39  2017/10/11
 * @Modified By:
 */
public class ThreadPoolTest4 {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
