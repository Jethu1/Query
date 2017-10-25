package DynamicPlanning;

public class BuyBook {
    int i = 20;
    int j = 20;
    int k=20;


    int[] pay = new int[7];
    int[][][] minMoney = new int[i][j][k];

    public  void init(){
        pay[0] = 57;
        pay[1] = 57;
        pay[2] = 57;
        pay[3] = 108;
        pay[4] = 108;
        pay[5] = 108;
        pay[6] = 153;
        for (int l = 0; l < i ; l++) {
            for (int m = 0; m < j; m++) {
                for (int n = 0; n < k; n++) {
                    minMoney[l][m][n] = -1;
                }
            }
        }


    }

    public int caculate(int i, int j, int k){
        if(i<=1&&j<=1&&k<=1){
            if((i==1&&j<1&&k<1)||(i<1&&j==1&&k<1)||(i<1&&j<1&&k==1)){
                return  57;
            }else if((i==1&&j==1&&k<1)||(i==1&&j<1&&k==1)||(i<1&&j==1&&k==1)){
                return 108;
            }else if((i==1&&j==1&&k==1)){
                return 153;
            }
        }else if(minMoney[i][j][k]!=-1){
            System.out.println(i+" "+j+" "+k );
            return minMoney[i][j][k];
        }else if(i==0&&j>0&&k>0){
            int[] s = new int[3];
            s[0] = pay[0]+caculate(i,j-1,k);
            s[1] = pay[0]+caculate(i,j,k-1);
            s[2] = pay[3]+caculate(i,j-1,k-1);

            minMoney[i][j][k] =totalMax(s);
            return  minMoney[i][j][k];

        }else if(i>0&&j==0&&k>0){
            int[] s = new int[3];
            s[0] = pay[0]+caculate(i-1,j,k);
            s[1] = pay[0]+caculate(i,j,k-1);
            s[2] = pay[3]+caculate(i-1,j,k-1);

            minMoney[i][j][k] =totalMax(s);
            return  minMoney[i][j][k];

        }else if(i>0&&j>0&&k==0){
            int[] s = new int[3];
            s[0] = pay[0]+caculate(i,j-1,k);
            s[1] = pay[0]+caculate(i-1,j,k);
            s[2] = pay[3]+caculate(i-1,j-1,k);

            minMoney[i][j][k] =totalMax(s);
            return  minMoney[i][j][k];

        }else if(i==0&&j>0&&k==0){
            int[] s = new int[1];
            s[0] = pay[0]+caculate(i,j-1,k);
            minMoney[i][j][k] =s[0];
            return  minMoney[i][j][k];

        } else if(i>0&&j==0&&k==0){
            int[] s = new int[1];
            s[0] = pay[0]+caculate(i-1,j,k);
            minMoney[i][j][k] =s[0];
            return  minMoney[i][j][k];

        } else if(i==0&&j==0&&k>0){
            int[] s = new int[1];
            s[0] = pay[0]+caculate(i,j,k-1);
            minMoney[i][j][k] =s[0];
            return  minMoney[i][j][k];

        } else if(i>0&&j>0&&k>0){
            int[] s = new int[7];
            s[0] = pay[0]+ caculate(i-1,j,k);
            s[1] = pay[0]+caculate(i,j-1,k);
            s[2] = pay[0]+caculate(i,j,k-1);
            s[3] = pay[0]+caculate(i-1,j,k-1);
            s[4] = pay[0]+caculate(i,j-1,k-1);
            s[5] = pay[0]+caculate(i-1,j-1,k);
            s[6] = pay[0]+caculate(i-1,j-1,k-1);
            minMoney[i][j][k] =totalMax(s);
           return  minMoney[i][j][k];
        }
        System.out.println("this is not ture!"+i+ " "+ j+"  "+k);
        return  -1;

    }

    private int totalMax(int[] s) {
        int max = s[0];
        for (int l = 1; l < s.length; l++) {
            if(max<s[l]){
                max = s[l];
            }
        }
        return  max;
    }

    public static void main(String[] args) {
        BuyBook goldMine = new BuyBook();
        goldMine.init();
        int value = goldMine.caculate(11,13,15);
        System.out.println(value);

    }

}