package 컨베이어_벨트_위의_로봇;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, K;
    private static int[] arr;
    private static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N * 2];
        robot = new boolean[N];

        tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0; i < N * 2; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int level = 0;
        while(true){
            level++;

            // (1) 벨트 회전
            int consume = arr[N * 2 - 1];
            for(int i = N * 2 - 1; i > 0; i--){
                arr[i] = arr[i - 1];
            }
            arr[0] = consume;

            for(int i = N - 1; i > 0; i--){
                robot[i] = robot[i - 1];
            }

            robot[0] = false;
            robot[N - 1] = false;

            // (2) 로봇 이동
            for(int i = N - 1 ; i > 0; i--){
                if(robot[i - 1] && !robot[i] && arr[i] > 0){
                    robot[i - 1] = false;
                    robot[i] = true;
                    arr[i]--;
                    robot[N - 1] = false;
                }
            }

            // (3) 로봇 넣기
            if(arr[0] > 0) {
                robot[0] = true;
                arr[0]--;
            }

            int count = 0;
            for(int i = 0; i < N * 2; i++){
                if(arr[i] == 0) count++;
            }

            if(count >= K) break;
        }

        System.out.println(level);

        reader.close();
    }
}
