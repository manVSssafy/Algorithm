package 통나무_건너뛰기;

// 다시 풀 문제

import java.io.*;
import java.util.*;

public class Solution {

    private static int T, N;
    private static int[] input, arr;


    private static int swap(int a1, int a2){
        return a1;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();
        T = Integer.parseInt(reader.readLine());

        for(int tk = 1; tk <= T; tk++){
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            input = new int[N];
            arr = new int[N];
            for(int i =0 ; i < N; i++){
                input[i] = Integer.parseInt(tokenizer.nextToken());
            }

            Arrays.sort(input);

            int left = N - 1;
            int right = 0;

            for(int i = 0 ; i < N; i++){
                if(i % 2 == 0){
                    arr[left--] = input[i];
                }else{
                    arr[right++] = input[i];
                }
            }

            int result = 0;
            for(int i = 1; i < N; i++){
                result = Math.max(result, Math.abs(arr[i] - arr[i-1]));
            }
            result = Math.max(result, Math.abs(arr[0] - arr[N - 1]));
            builder.append(result).append("\n");
        }

        System.out.print(builder.toString());


        reader.close();
    }
}
