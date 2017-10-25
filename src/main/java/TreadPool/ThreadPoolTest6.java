package TreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 11:48  2017/10/11
 * @Modified By:
 */
public class ThreadPoolTest6 {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        while(true) {
                            System.out.println(index);
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
