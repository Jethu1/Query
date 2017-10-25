package DynamicPlanning;

import java.math.BigDecimal;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 11:18  2017/9/14
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {

        double kk = 3.14d;
        kk += 0.37d;
        System.out.println(kk);
        BigDecimal b1 = new BigDecimal("3.14");
        BigDecimal b2 = new BigDecimal("0.37");
        BigDecimal b4 = b1.add(b2);


        float d = b4.floatValue();
        System.out.println(d);
        System.out.println(b4);

        float x1 = 3.5f;
        float x2 = 3.6f;
        float x3 = 3.0f;
        float x4 = 3.8f;
        System.out.println(x1/5+" "+x2/4+" "+x3/1.5f+" "+x4/2);

        String  s4 = "1239";
        int m = 1+Integer.valueOf(s4);
        int in = 123;
        String s5 = new String(String.valueOf(m));
        System.out.println(s5);
        float n1 = 1.55f;
        System.out.println((double)n1);


    }
}
