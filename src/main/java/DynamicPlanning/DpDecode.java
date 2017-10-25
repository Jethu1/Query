package DynamicPlanning;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 15:49  2017/9/16
 * @Modified By:
 */
public class DpDecode {
    int[] dp;
    public int numDecodings(String s) {
        if(s.length()==0||s.equals("0")) return 0;
        dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        char[] ch = s.toCharArray();
        return  dps(ch,s.length());
    }

    private int dps(char[] ch, int i) {
        if(i==0||i==1) return 1;
        else if(dp[i]!=0){
            return  dp[i];
        }else{
            if(ch[i-1]=='0'&&ch[i-2]=='0'){
                return 0;
            }
            else if(ch[i-1]=='0'){
                if(i>2){
                    dp[i] = dps(ch,i-3);
                    return dp[i];
                }else{
                    return 1;
                }
            }
            else if((ch[i-2]=='1'&&ch[i-1]>'0')||(ch[i-2]=='2'&&ch[i-1]<'7'&&ch[i-1]>'0')){
                dp[i] = dps(ch,i-1)+dps(ch,i-2);
                return dp[i];
            }else{
                dp[i] =dps(ch,i-1);
                return dp[i];
            }
        }
    }

    public static void main(String[] args) {

        String s ="10";
        DpDecode dpDecode = new DpDecode();
        System.out.println(dpDecode.numDecodings(s));

    }

}
