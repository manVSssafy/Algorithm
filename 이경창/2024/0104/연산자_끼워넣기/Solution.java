package 연산자_끼워넣기;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N;
    private static int[] arr;
    private static int[] operatorCount;
    private static int mixResult, maxResult;

    private static int calculate(int index, int result, int operatorIdx){
        // (+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.
        switch (operatorIdx){
            case 0:
                return result + arr[index];
            case 1:
                return result - arr[index];
            case 2:
                return result * arr[index];
            default:
                int cal = (result < 0 ? -1 : 1);
                // 음수인 경우, 양수로 바꾸고 몫 취하고 음수로 바꾼다.
                result *= cal;
                result /= arr[index];
                result *= cal;
                return result;
        }
    }
    private static void backtracking(int index, int result){

//        System.out.println("index : " + index + " result : " + result);
        if(index == N){
            mixResult = Math.min(mixResult, result);
            maxResult = Math.max(maxResult, result);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(operatorCount[i] > 0){
                operatorCount[i]--;
                backtracking(index + 1, calculate(index, result, i));
                operatorCount[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        mixResult = Integer.MAX_VALUE;
        maxResult = Integer.MIN_VALUE;
        N = Integer.parseInt(reader.readLine());
        arr = new int[N];
        operatorCount = new int[4];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < 4; i++){
            operatorCount[i] = Integer.parseInt(tokenizer.nextToken());
        }

        backtracking(1, arr[0]);

        StringBuilder builder = new StringBuilder();
        builder.append(maxResult).append("\n");
        builder.append(mixResult).append("\n");

        System.out.print(builder.toString());

        reader.close();
    }
}
