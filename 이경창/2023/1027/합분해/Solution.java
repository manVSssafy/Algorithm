package 합분해;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, K;
    private static long[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        dp = new long[201][201];

        for(int i = 0; i <= N; i++){
            dp[i][1] = 1;
        }

        for(int i = 0; i <= N; i++){
            for(int j = 1; j <= K; j++){
                if(i == 0 || j == 1) dp[i][j] = 1;
                else dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_000;
            }
        }

        System.out.println(dp[N][K]);

//        for(int i = 1; i <= N; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }



        reader.close();
    }
}
