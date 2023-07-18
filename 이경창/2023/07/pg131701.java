package PACKAGE_NAME;


import java.util.Set;
import java.util.HashSet;

class Solution {

    private int sum(int index, int[] elements, int len){

        int elementsLen = elements.length;
        int number = 0;
        for(int i = 0; i < len; i++){
            number += elements[(index + i) % elementsLen];
        }

        return number;
    }

    public int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        for(int len = 1; len <= elements.length; len++){
            for(int startIndex = 0; startIndex < elements.length; startIndex++){
                answer.add(sum(startIndex, elements, len));
            }
            // System.out.println("길이" + len);
            // for(int num : answer) System.out.println(num);
        }

        return answer.size();
    }
}
