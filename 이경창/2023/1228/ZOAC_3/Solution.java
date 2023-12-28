package ZOAC_3;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node{
        public final int x;
        public final int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    private static char L, R;
    private static char[][] cArr;

    private static int compareToLeftRight(int row, int col, char c){
        int curRow = map.get(c)[0];
        int curCol = map.get(c)[1];

        return Math.abs(row - curRow) + Math.abs(col - curCol);
    }
    private static Map<Character, int[]> map, rightMap;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        rightMap = new HashMap<>();
        cArr = new char[][]{{'q','w','e','r','t', 'y','u','i','o', 'p'},{'a', 's', 'd','f','g','h','j','k','l'},{'z','x','c','v','b','n','m'}};

        for(int i = 0; i < cArr.length; i++){

            int mid = cArr[i].length % 2 == 0 ? cArr[i].length / 2 - 1 : cArr[i].length / 2;
            for(int j = 0; j < cArr[i].length; j++){
                if(j <= mid){
                    map.put(cArr[i][j], new int[]{i, j});
                }else{
                    rightMap.put(cArr[i][j], new int[]{i, j});
                }
            }
        }

        String s = reader.readLine();
        String[] lrArr = s.split(" ");
        char[] lrChar = new char[]{lrArr[0].charAt(0), lrArr[1].charAt(0)};
        String input = reader.readLine();
        int answer = 0;

        for(char c : input.toCharArray()){
            int[] d1, d2;
            if(map.containsKey(c)){
                d1 = map.get(lrChar[0]);
                d2 = map.get(c);
                lrChar[0] = c;
            }else{
                d1 = rightMap.get(lrChar[1]);
                d2 = rightMap.get(c);
                lrChar[1] = c;
            }
            answer += Math.abs(d1[0] - d2[0]) + Math.abs(d1[1] - d2[1]);
            answer += 1;
        }

        System.out.println(answer);

        reader.close();
    }
}
