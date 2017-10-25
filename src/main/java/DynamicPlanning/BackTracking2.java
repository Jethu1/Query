package DynamicPlanning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 22:36  2017/9/12
 * @Modified By:
 */
public class BackTracking2 {

        List<List<Integer>> result=new ArrayList<List<Integer>>();
        public List<List<Integer>>combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);//所给数组可能无序，排序保证解按照非递减组合
            List<Integer> list=new ArrayList<Integer>();
            backtracking(candidates,target,0,list);//给定target，start=0表示从数组第一个开始
            return result;//返回解的组合链表
        }
    public void backtracking(int[]candidates,int target,int start,List<Integer> list){

        if(target<0)    return;//凑过头了
        else if(target==0){

            result.add(new ArrayList<>(list));//正好凑出答案，开心地加入解的链表

        }else{
            for(int i=start;i<candidates.length;i++){//循环试探每个数
                list.add(candidates[i]);//尝试加入
                //下一次凑target-candidates[i]，允许重复，还是从i开始
                backtracking(candidates,target-candidates[i],i,list);
                list.remove(list.size()-1);//回退
            }
        }

    }
}

