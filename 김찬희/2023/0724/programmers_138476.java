import java.util.*;

class Solution {
    Map<Integer,Integer> TestMap = new HashMap<Integer,Integer>();
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        
        for(int i=0;i<tangerine.length;i++){            
            TestMap.put(tangerine[i],TestMap.getOrDefault(tangerine[i],0)+1);
        }
        
        List<Integer> TestKey=new ArrayList<>(TestMap.keySet());
        Collections.sort(TestKey, new customComparator());
        
        for (Integer e : TestKey) {
            if (k <= 0) 
                break;
            answer++;
            k -= TestMap.get(e);
        }
        return answer;
    }
    
    public class customComparator implements Comparator<Integer> {
        
        @Override
        public int compare(Integer o1, Integer o2) {
            return TestMap.get(o2).compareTo(TestMap.get(o1));
        }
    }
}