package 타일01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        dp = new int[N + 100];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        // 앞에 1 붙이는 경우의수 + 앞에 00 붙이는 경우의 수
        for(int i = 4; i <= N; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[N]);
        reader.close();
    }
}
