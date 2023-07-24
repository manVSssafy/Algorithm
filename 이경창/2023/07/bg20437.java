import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bg20437 {

    // 2
    //superaquatornado
    //2
    //abcdefghijklmnopqrstuvwxyz
    //5

    private static int T, K;
    private static String W;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        T = Integer.parseInt(reader.readLine());

        for(int tk = 1; tk <= T; tk++){
            W = reader.readLine();
            K = Integer.parseInt(reader.readLine());

            if(K == 1){
                builder.append(1).append(" ").append(1).append("\n");
                continue;
            }

            int[] alpha = new int[26];
            for(int j = 0; j < W.length(); j++) alpha[W.charAt(j) - 'a']++;

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int j = 0; j < W.length();j++){

                // 만약 구간내에 K 개수보다 적다면 pass
                if(alpha[W.charAt(j) - 'a'] < K) continue;

                int count = 1;
                for(int l = j + 1; l < W.length(); l++){
                    if(W.charAt(l) == W.charAt(j)) count++;
                    if(count == K){
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) builder.append(-1).append("\n");
            else builder.append(min).append(" ").append(max).append("\n");
        }

        System.out.print(builder);

        reader.close();
    }
}
