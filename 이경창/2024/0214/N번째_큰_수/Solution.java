package N번째_큰_수;


import java.util.*;
import java.io.*;

public class Solution {
    private static int N;
    private static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        arr = new int[N * N];

        int index = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++){
                arr[index++] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        arr = Arrays.stream(arr).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();

        System.out.println(arr[N - 1]);

        reader.close();
    }
}
