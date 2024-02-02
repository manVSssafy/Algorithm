package 무기_공학;

import java.util.*;
import java.io.*;

public class Solution {

    private static class Node{
        public final int x;
        public final int y;
        public final int sum;

        Node(int x, int y, int sum){
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

    }
    private static int N, M;
    private static int[][] arr;

    private static int[][][] dxy = {{{-1, 0}, {1, 0}}, {{-1, 0}, {0, - 1}}, {{1, 0}, {0, -1}}, {{1, 0}, {0, 1}}};
    private static boolean[][] visited;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    private static void backtracking(int row, int col, int sum){
        // 4구간 탐색
        for(int idx = 0; idx < 4; idx++){
            int[][] dArr = dxy[idx];
            int nx = dArr[0][0];
            int ny = dArr[0][1];
            int nx2 = dArr[1][0];
            int ny2 = dArr[1][1];

            if(visited[ny][nx] || visited[ny2][nx2]) continue;
            visited[ny][nx] = true;
            visited[ny2][nx2] = true;
//            backtracking();
//            backtracking();

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                backtracking(i, j ,0);
            }
        }




        reader.close();
    }
}
