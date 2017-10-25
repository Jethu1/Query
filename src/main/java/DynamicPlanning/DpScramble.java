package DynamicPlanning;

import java.util.Arrays;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 14:43  2017/9/30
 * @Modified By:
 */
public class DpScramble {

    public boolean isScramble(String s1, String s2) {
        if(s1.length()!=s2.length()) return  false;
        if(s1.length()==1) return  s1.equals(s2);
        char[] chars1 = new char[s1.length()];
        char[] chars2 = new char[s2.length()];
        s1.getChars(0,s1.length(),chars1,0);
        s2.getChars(0,s2.length(),chars2,0);
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        if(!new String(chars1).equals(new String(chars2))) return  false;

        for (int i = 1; i < s1.length() ; i++) {
            String left1 = s1.substring(0,i);
            String right1 = s1.substring(i,s1.length());
            String left2 = s2.substring(0,i);
            String right2 = s2.substring(i,s2.length());
            if(isScramble(left1,left2)&&isScramble(right1,right2))
                return  true;
            left2 = s2.substring(0,s2.length()-i);
            right2 = s2.substring(s2.length()-i,s2.length());
            if(isScramble(left1,right2)&&isScramble(right1,left2))
                return  true;
        }
        return  false;

    }

}
