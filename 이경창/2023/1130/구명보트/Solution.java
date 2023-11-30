package 구명보트;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int leftIdx = 0;
        int rightIdx = people.length - 1;

        int answer = 0;
        // 7 / 2 = 3.xxx
        Arrays.sort(people);

        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < people.length; i++){
            deque.add(people[i]);
        }

        while(deque.size() > 0){
            if(deque.size() == 1){
                answer += 1;
                break;
            }
            int leftData = deque.getFirst();
            int rightData = deque.getLast();

            if(leftData + rightData <= limit){
                deque.pollFirst();
                deque.pollLast();
            }else{
                deque.pollLast();
            }

            answer += 1;
        }

        return answer;
    }
}
