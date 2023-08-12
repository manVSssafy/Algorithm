package 돌_게임3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static boolean[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        dp = new boolean[1010];
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;
        dp[4] = true;


        for(int i = 5; i <= N; i++){
            // 이전 (1, 3, 4) 모두 true라면 두 번째 사람 승리
            // 아닐 경우 첫 번째 사람 승리
            if(dp[i - 4] && dp[i - 3] && dp[i - 1]) dp[i] = false;
            else dp[i] = true;
        }

        if(dp[N]) System.out.println("SK");
        else System.out.println("CY");


        reader.close();
    }
}
