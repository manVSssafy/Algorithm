package 수_묶기;

import java.io.*;
import java.util.*;

public class Solution {
    private static int N;
    private static long answer;
    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        arr = new int[N];

        int minusCnt = 0;
        int plusCnt = 0;
        int zeroCnt = 0;
        visited = new boolean[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(arr);
        for(int i = 0; i < N; i++){
            if(arr[i] < 0) minusCnt += 1;
            else if(arr[i] > 0) plusCnt += 1;
            else zeroCnt += 1;
        }

        for(int i = 0 ; i < minusCnt - 1; i += 2){
            answer += (long)arr[i] * arr[i + 1];
            visited[i] = true;
            visited[i + 1] = true;
        }

        // zeroCnt == 1, minusCnt % 2 == 1인 경우 0
        // zeroCnt == 0, minusCnt % 2 == 1인 경우 minuCnt 더해줌
        if(zeroCnt > 0 && minusCnt % 2 != 0){
            visited[minusCnt] = true;
            visited[minusCnt - 1] = true;
        }

        for(int i = arr.length - 1; i > arr.length - plusCnt; i -= 2){
            long multiply = (long)arr[i] * arr[i - 1];
            long addition = (long)arr[i] + arr[i - 1];

            if(multiply < addition) answer += addition;
            else answer += multiply;
            visited[i] = true;
            visited[i - 1] = true;
        }

        for(int i = 0; i < arr.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
//            System.out.println("방문하지못한 값 " + arr[i]);
            answer += (long)arr[i];
        }

        System.out.println(answer);

        reader.close();
    }
}
