package 기능개발;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++){
            int res = (100 - progresses[i]) / speeds[i];
            res += ((100 - progresses[i]) % speeds[i] == 0 ? 0 : 1);

            queue.add(res);
        }

        ArrayList<Integer> result = new ArrayList();

        int beforeData = queue.peek();

        int count = 0;
        while(queue.size() > 0){
            int res = queue.poll();
            // System.out.println(res);
            if(beforeData < res){
                beforeData = res;
                result.add(count);
                count = 1;
            }else{
                count++;
            }
        }

        if(count > 0) result.add(count);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}