import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String,Integer> HM = new HashMap<>();     
        
        for(int i=0 ;i< clothes.length; i++){            
            if(HM.get(clothes[i][1]) == null) {
                HM.put(clothes[i][1],1);
            }
            else{
                HM.put(clothes[i][1],HM.get(clothes[i][1])+1);
            }            
        }
        
        for(String s : HM.keySet()){
            answer=answer*(HM.get(s)+1);
        }
        
        return answer-1;
    }
}