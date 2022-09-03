package Baekjoon.study;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ17616_경창 {
    static int N, M, X;
    static int cnt, reverseCnt;
    static ArrayList<Integer>[] arrayList;
    static ArrayList<Integer>[] backArrayList;

    static boolean[] visited;
    static void dfs(int idx){

        if(!visited[idx]){
            visited[idx] = true;
            //
//            System.out.println("idx : " + idx);
            cnt += 1;
            for (int in_data : arrayList[idx]) {
                dfs(in_data);
            }
        }
    }

    static void backDfs(int idx){
        if(!visited[idx]){
            visited[idx] = true;
//            System.out.println("idx : " + idx);
            // 횟수
            reverseCnt += 1;
            for (int in_data : backArrayList[idx]) {
                backDfs(in_data);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arrayList = new ArrayList[N+1];
        visited = new boolean[N+1];
        backArrayList = new ArrayList[N+1];

        for(int i = 0 ; i < N+1;i++){
            arrayList[i] = new ArrayList<>();
            backArrayList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrayList[a].add(b);
            backArrayList[b].add(a);
        }


        // 순방향
        dfs(X);

        visited = new boolean[N+1];

        // 역방향
        backDfs(X);

        System.out.println(reverseCnt + " " + (N - cnt + 1));
        br.close();
    }
}
