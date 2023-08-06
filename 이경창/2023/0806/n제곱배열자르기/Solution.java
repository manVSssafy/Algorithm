package n제곱배열자르기;

import java.util.*;

public class Solution {
    public int[] solution(int n, long left, long right) {

        List<Integer> list = new ArrayList<>();

        // left
        int leftRow = (int)(left / n);
        int leftCol = (int)(left % n);

        // System.out.println("leftRow : " + leftRow + " leftCol : " + leftCol);

        // right
        int rightRow = (int)(right / n);
        int rightCol = (int)(right % n);

        // System.out.println("rightRow : " + rightRow + " rightCol : " + rightCol);

        // int minX = Math.min(leftCol, rightCol);
        // int maxX = Math.max(leftCol, rightCol);

        for(long i = left; i <= right; i++){
            int col = (int)(i % n) + 1;
            int row = (int)(i / n) + 1;
            list.add(Math.max(col, row));
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}