package DynamicPlanning;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 17:09  2017/9/10
 * @Modified By:
 */
public class Solution1 {
    boolean[][] dp;
    String str1;
    String str2;
    String str3;
    public boolean isInterleave(String s1, String s2, String s3) {

        str1 = s1;
        str2 = s2;
        str3 = s3;
        int i = s1.length();
        int j = s2.length();
        int k = s3.length();
        if(i==0&&j==0&&k==0)
            return true;
        if(i+j!=k)
            return false;
        dp = new boolean[i+1][j+1];
        return judge(i,j);
    }

    public boolean judge(int i, int j) {
       dp[0][0] = true;
        for (int k = 1; k < i+1; k++) {
            dp[k][0] = dp[k-1][0]&&str1.charAt(k-1)==str3.charAt(k-1);
        }
        for (int k = 1; k < j+1; k++) {
            dp[0][k] = dp[0][k-1]&&str1.charAt(k-1)==str3.charAt(k-1);
        }
        for (int k = 1; k < i+1; k++) {
            for (int l = 1; l < j+1; l++) {
                dp[k][l] = (dp[k-1][l]&&str1.charAt(k-1)==str3.charAt(k+l-1))||(dp[k][l-1]&&str2.charAt(l-1)==str3.charAt(k+l-1));
            }
        }

         return dp[i][j];
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        boolean b= solution1.isInterleave("a","b","ab");
        System.out.println(b);
    }

}
