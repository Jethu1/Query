package TryCatch;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 14:30  2017/10/21
 * @Modified By:
 */
public class TryCatch1 {

    public static void main(String[] args) {
        int a =0,b=2,c=3;
//        c=  b/a;
        try{
            c=b/a;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("I want to know the program is keeping running!");

    }
}
