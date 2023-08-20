package 숫자_카드2;

import java.io.*;
import java.util.*;

public class Solution2 {
    private static int N, M;
    private static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(reader.readLine());

        Map<Long, Integer> map = new HashMap<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0 ; i < N; i++){
            long number = Long.parseLong(tokenizer.nextToken());
            map.putIfAbsent(number, 0);
            map.put(number, map.get(number) + 1);
        }

        M = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0;  i < M; i++){
            long number = Long.parseLong(tokenizer.nextToken());
            int findCount = map.getOrDefault(number, 0);
            builder.append(findCount).append(" ");
        }
        System.out.println(builder);
        reader.close();
    }
}
