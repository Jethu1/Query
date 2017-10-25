package DynamicPlanning;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 22:06  2017/9/12
 * @Modified By:
 */
public class BackTracking {
    List<List<Integer>> result=new ArrayList<List<Integer>>();
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> list=new ArrayList<Integer>();
        backtracking(n,k,1,list);
        return result;
    }
    public void backtracking(int n,int k,int start,List<Integer>list){
        if(k<0) return ;
        else if(k==0){
            result.add(new ArrayList(list));
        }else{
            for(int i=start;i<=n;i++){
                list.add(i);
                backtracking(n,k-1,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(list.size()-1);
        System.out.println(list);
    }
}
