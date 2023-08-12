package 피보나치_수의_확장;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        // 1,000,000,000

        int n = Integer.parseInt(reader.readLine());

        if(n < 0 && n % 2 == 0) builder.append(-1).append("\n");
        else if(n == 0) builder.append(0).append("\n");
        else{
            builder.append(1).append("\n");
        }

        n = Math.abs(n);
        dp = new long[n + 100];

        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000000;
        }

        builder.append(dp[n]);

        System.out.println(builder);

        reader.close();
    }
}
