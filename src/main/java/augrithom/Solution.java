package augrithom;

import java.util.*;
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if(s==null||s.length()==0||dict==null||dict.size()==0)
            return false;
        Boolean[] result = new Boolean[s.length()+1];
        for (int i = 0; i <s.length()+1 ; i++) {
            result[i] = false;
        }
        result[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(result[j]&&dict.contains(s.substring(j,i))){
                    result[i] = true;
                    break;
                }
            }

        }
        return result[s.length()];
    }
}