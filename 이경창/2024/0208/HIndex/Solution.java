package HIndex;

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int maxData = citations[citations.length - 1];
        for(int i = maxData; i >= 0; i--){

            // (1) h번 이상 인용된 논문

            // (2) h편 이상
            int hCnt = 0;
            // System.out.println(i);
            for(int j = citations.length - 1; j >= 0; j--){

                if(citations[j] >= i) hCnt++;

                if(hCnt >= i && j <= i) return i;
            }

        }

        return 0;
    }
}