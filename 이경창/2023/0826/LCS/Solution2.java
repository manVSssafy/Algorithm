package LCS;

import java.util.*;
import java.io.*;

public class Solution2 {

    private static String str1, str2;
    private static char[] cArr1, cArr2;

    private static int longestCommonSubsequence(){
        int[][] dp = new int[cArr1.length + 1][cArr2.length + 1];

        for(int i = 1; i <= cArr1.length; i++){
            for(int j = 1; j <= cArr2.length; j++){
//                System.out.println(cArr1[i - 1]  + " " +  cArr2[j - 1]);
                if(cArr1[i - 1] == cArr2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    int res = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    res = Math.max(res, dp[i - 1][j - 1]);
                    dp[i][j] = res;
                }
            }
        }

//        System.out.println(Arrays.deepToString(dp));

        return dp[cArr1.length][cArr2.length];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        str1 = reader.readLine();
        str2 = reader.readLine();

        cArr1 = str1.toCharArray();
        cArr2 = str2.toCharArray();

        System.out.println(longestCommonSubsequence());

        reader.close();
    }
}
