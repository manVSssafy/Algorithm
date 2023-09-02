package 우리집엔_도서관이_있어;

// 다시 풀 문제

import java.io.*;
import java.util.*;

public class Solution3 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        N = Integer.parseInt(reader.readLine());
        arr = new int[N];
        int result = N;

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(reader.readLine());
        }


        for(int i = N - 1; i >=0; i--){
            if(arr[i] == N) N -= 1;
        }

        System.out.println(N);

        reader.close();
    }
}
