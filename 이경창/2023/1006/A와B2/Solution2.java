package A와B2;

import java.io.*;
import java.util.*;

public class Solution2 {

    private static String S, T;
    private static int answer;
    private static void dfs(StringBuilder builder){

        if(builder.length() <= 0) return;
        if(builder.length() == S.length()){
            if(builder.toString().equals(S)) answer = 1;
            return;
        }

        if(builder.charAt(0) == 'B'){
            StringBuilder nextBuilder = new StringBuilder(builder);
            dfs(new StringBuilder(nextBuilder.reverse().deleteCharAt(nextBuilder.length() - 1)));
        }
        if(builder.charAt(builder.length() - 1) == 'A') dfs(new StringBuilder(builder.deleteCharAt(builder.length() - 1)));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        S = reader.readLine();
        T = reader.readLine();

        dfs(new StringBuilder(T));

        System.out.println(answer);


        reader.close();
    }
}
