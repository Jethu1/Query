package DynamicPlanning;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 22:14  2017/9/12
 * @Modified By:
 */
public class BackTracking1 {
    List<List<Integer>> result=new ArrayList<List<Integer>>();
    public List<List<Integer>> combine(int[] num, int target) {
        List<Integer> list=new ArrayList<Integer>();
        backtracking(num,target,0,0,list);
        return result;
    }
    public void backtracking(int[] num,int target,int start,int sum,List<Integer>list){
        if(sum>target) return ;
        else if(sum==target){
            result.add(new ArrayList(list));
        }else{
            for(int i=start;i<num.length;i++){
                list.add(num[i]);
                sum+=num[i];
                backtracking(num,target,i,sum,list);
                list.remove(list.size()-1);
                sum-=num[i];
            }
        }
    }

    public static void main(String[] args) {
        BackTracking1 tracking1 = new BackTracking1();
        int[] num = new int[]{2,3,6,7};
        tracking1.combine(num,7);
        System.out.println(tracking1.result);


    }

}
