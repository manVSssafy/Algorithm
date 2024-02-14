package 숫자고르기;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N;
    private static int[] arr;
    private static int result;
    private static ArrayList<Integer> resultStartIdx;

    private static void dfs(int index, boolean[] visited, int count, int startIdx){
        if(startIdx == index){
//            System.out.println("도착 : " + index);
//            System.out.println(count);
            resultStartIdx.add(startIdx);
            return;
        }

        if(visited[index]) return;
        visited[index] = true;

        // dfs 관련 반환처리하는 쉽게 하는 방법 학습하기
       dfs(arr[index], visited, count + 1, startIdx);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        arr = new int[N];

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            // i -> 입력값으로 간다.
            arr[i] = Integer.parseInt(reader.readLine()) - 1;
            if(arr[i] == i) list.add(i + 1);
        }

        System.out.println("size 크기 : " + list.size());
        resultStartIdx = new ArrayList<>();
        for(int i = 0; i < N; i++){
//            System.out.println("시작 : " + i + " " + arr[i]);
            dfs(arr[i], new boolean[N], 1, i);
//            System.out.println();
        }


        boolean[] visited = new boolean[N];
        for(int i = 0; i < resultStartIdx.size(); i++){
            if(visited[i]) continue;
            System.out.println(i + " 시작");
            visited[i] = true;
            int number = resultStartIdx.get(i);
            visited[number] = true;
            list.add(number + 1);
            while(true){

                number = arr[number];
                if(visited[number]) break;
                visited[number] = true;
                System.out.println(number + " 다음");

                if(resultStartIdx.get(i) == number) break;
                list.add(number + 1);
            }
            System.out.println();
        }

        Collections.sort(list);

        System.out.println(list.size());
        for(int number : list){
            System.out.println(number);
        }

        reader.close();
    }
}
