package DynamicPlanning;

public class GoldMine {
    int max_n = 10;
    int max_people = 10000;
    int n;
    int people;
    int[] peopleNeed= new int[max_n];
    int[] gold = new int[max_n];
    int[][] maxGold = new int[max_people][max_n];

    public  void init(){
        for (int i = 0; i < max_n ; i++) {
            peopleNeed[i] = 1500;
            gold[i] = (7000-500*i+50*i*i);
        }
        for (int i = 0; i < max_people; i++) {
            for (int j = 0; j < max_n; j++) {
                  maxGold[i][j] = -1;
            }
        }

    }

    public int caculate(int people, int mineNum){
        if(mineNum<=0){
            if(people>=peopleNeed[0])
                return gold[0];
            else
                return  0;
        }else if(people< peopleNeed[mineNum]){
            return  0;
        }else if(maxGold[people][mineNum]!=-1){
            return  maxGold[people][mineNum];
        } else {
           int left = caculate(people,mineNum-1);
           int right = caculate(people-peopleNeed[mineNum],mineNum-1)+gold[mineNum];
           maxGold[people][mineNum] = Math.max(left,right);
           return  maxGold[people][mineNum];
        }
    }

    public static void main(String[] args) {
        GoldMine goldMine = new GoldMine();
        goldMine.init();
        int value = goldMine.caculate(9999,9);
        System.out.println(value);
    }

}