package 이건_꼭_풀어야_해;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {

    private static int N, Q;
    private static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        Q = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N];

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(arr);
        int[] dp = new int[N];

        dp[0] = arr[0];
        for(int i = 1; i < N; i++){
            dp[i] = arr[i] + dp[i - 1];
        }

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < Q; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int L = Integer.parseInt(tokenizer.nextToken()) - 1;
            int R = Integer.parseInt(tokenizer.nextToken()) - 1;

            int result = 0;
            if(L == 0){
                result = dp[R];
            }else{
                result = dp[R] - dp[L - 1];
            }
            builder.append(result).append("\n");
        }

        System.out.print(builder.toString());

        reader.close();
    }
}
