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

            int left = N / 2;
            int right = N / 2;
            int k = 0;

            if(N % 2 != 0){
                arr[N / 2] = input[N - 1];
                left -= 1;
                right += 1;
                k += 1;
            }else{
                left -= 1;
            }

//            System.out.println("arr size : " + arr.length);

            for(int i = 0; i < (N / 2); i++){

//                System.out.println("left : " + (left - i) + " right : " + (right + i));
//                System.out.println((N - 1 - k) + " " + (N - 1 - (k + 1)));
//                System.out.println(Arrays.toString(arr));
                arr[left - i] = input[N - 1 - k];
                arr[right + i] = input[N - 1 - (k + 1)];
                k += 2;
            }

            int result = 0;
            for(int i = 1; i < N; i++){
                int curResult = Math.abs(arr[i] - arr[i -1]);
//                System.out.println("curResult : " + curResult + " result : " + result);
                if(result < curResult) result = curResult;
            }
            builder.append(result).append("\n");
        }

        System.out.print(builder.toString());


        reader.close();
    }
}
