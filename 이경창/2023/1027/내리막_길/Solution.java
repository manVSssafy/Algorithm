package 내리막_길;

// 다시 풀기

import java.io.*;
import java.util.*;

public class Solution {

    public static class Point{
        public final int x;
        public final int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Node{
        public final int x;
        public final int y;
        public final int directionSize;

        Node(int x, int y, int directionSize){
            this.x = x;
            this.y = y;
            this.directionSize = directionSize;
        }
    }

    private static int M, N;
    private static int[][] arr;
    private static int[][] dp;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int result;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < M && col < N;
    }


    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        dp[0][0] = arr[0][0];

        while (queue.size() > 0) {
            Point p = queue.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                result += 1;
                continue;
            };

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (!isWithInRange(ny, nx)) continue;

                if (arr[ny][nx] < arr[p.y][p.x] && dp[ny][nx] >= dp[p.y][p.x] + arr[ny][nx]) {

                    dp[ny][nx] = dp[p.y][p.x] + arr[ny][nx];
                    System.out.println(ny + " " + nx + " : " + dp[ny][nx] + " , " + dp[p.y][p.x]);
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        M = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());

        arr = new int[M][N];
        dp = new int[M][N];

        for(int i = 0 ; i < M; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                dp[i][j] = 10_100;
            }
        }

        bfs();



        for(int i  =0 ; i < M; i++){
            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(result);

        reader.close();
    }
}
