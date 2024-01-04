package 에너지_모으기;

import java.util.*;
import java.io.*;

// arrayList로 처리해보기
public class Solution {

    private static int N;
    private static int[] arr;
    private static boolean[] visited;
    private static int result;
    private static ArrayList<Integer> arrayList;

    private static void backtracking(int nSize, int sum, StringBuilder builder){
        if(nSize <= 2){
//            System.out.println(builder.toString() + " " + " 합 : " + sum);
            result = Math.max(result, sum);
            return;
        }

        for(int i = 1; i < N - 1; i++){
            if(!visited[i]){
                int left = 0;
                int right = 0;
                visited[i] = true;

                for(int j = i - 1; j >= 0; j--){
                    if(!visited[j]){
                        left = arr[j];
                        break;
                    }
                }
                for(int j = i + 1; j < N; j++){
                    if(!visited[j]){
                        right = arr[j];
                        break;
                    }
                }
                backtracking(nSize - 1, sum + (left * right), new StringBuilder(builder.toString() + " " + arr[i]));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
//        arrayList = new ArrayList<>();
        arr = new int[N];
        visited = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
//            arrayList.add(arr[i]);
        }

        backtracking(N, 0, new StringBuilder());

        System.out.println(result);


        reader.close();
    }
}
