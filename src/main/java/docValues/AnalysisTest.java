package docValues;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 22:20  2017/9/22
 * @Modified By:
 */
public class AnalysisTest {
    public static void main(String[] args) {
        int n =1000000000;
        int sum =0;
        long b1 = System.currentTimeMillis();
        sum =0;
        for (int i = 0; i < n; i++) {
            sum++;
        }
        long b2 = System.currentTimeMillis();
        sum =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                sum++;
            }
        }
        long b3 = System.currentTimeMillis();
        sum =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n*n ; j++) {
                sum++;
            }
        }
        long b4 = System.currentTimeMillis();
        sum =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i ; j++) {
                sum++;
            }
        }
        long b5 = System.currentTimeMillis();
        sum =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i*i ; j++) {
                for (int k= 0; k < j ; k++) {
                    sum++;
                }
            }
        }
        long b6 = System.currentTimeMillis();
        sum =0;
/*        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i*i ; j++) {
                if(j%i==0)
                for (int k= 0; k < j ; k++) {
                    sum++;
                }
            }
        }*/
        long b7 = System.currentTimeMillis();
        System.out.println((b2-b1)+" "+(b3-b2)+" "+(b4-b3)+" "+(b5-b4)+" "+(b6-b5)+" "+(b7-b6) );


    }


}
