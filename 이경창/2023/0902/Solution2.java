import java.io.*;
import java.util.*;

public class Solution2 {

    private static int N;
    private static int[] arr;
    private static long[] dp;
    private static int value;
    private static long valueSum;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        arr = new int[N];
        dp = new long[N];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);
        dp[0] = arr[0];

        for(int i = 1; i < N; i++){
            dp[i] = dp[i - 1] + arr[i];
        }

        value = arr[0];
        valueSum = (dp[N - 1] - dp[0]) - ((long) arr[0] * (N - 1));

        for(int i = 1; i < N; i++){
            long curResult = (long) arr[i] * ((i + i) - (N - 1)) + (dp[N - 1] - dp[i]) - dp[i - 1];
//            System.out.println(arr[i] * ((i + i) - (N - 1)));
//            System.out.println("i : " + i + " arr[i] : " + arr[i] + " valueSum : " + valueSum + " curResult " + curResult);
            if(valueSum > curResult){
                value = arr[i];
                valueSum = curResult;
            }
        }

        System.out.println(value);


    }
}
