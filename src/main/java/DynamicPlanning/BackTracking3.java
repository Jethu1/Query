package DynamicPlanning;

import java.util.*;

/**
 * @Author: Jet Hu
 * @Description:
 * @Date: Created in 19:03  2017/9/14
 * @Modified By:
 */
public class BackTracking3 {
    ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
    public  ArrayList<ArrayList<Integer>> sunSets(int[] nums){
        if(nums==null||nums.length==0) return  lists;
        ArrayList<Integer> list = new ArrayList<>();
        backTrack(nums,list);
        return lists;
    }

    private void backTrack(int[] nums, ArrayList<Integer> list) {
            if(list.size()==nums.length)
            lists.add(new ArrayList<>(list));
        else {
                for (int j = 0; j < nums.length; j++) {
                    if (list.contains(nums[j])) continue;
                    list.add(nums[j]);
                    backTrack(nums, list);
                    list.remove(list.size() - 1);
                }
            }
    }


    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        list.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.size()>o2.size())
                    return 1;
                else
                    return  -1;
            }
        });
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] num = new int[]{3,4,6};
        BackTracking3 tracking3 = new BackTracking3();
        tracking3.sunSets(num);
        System.out.println(tracking3.subsets(num));
        System.out.println(tracking3.lists);
        long begin = System.currentTimeMillis();

    }
}
