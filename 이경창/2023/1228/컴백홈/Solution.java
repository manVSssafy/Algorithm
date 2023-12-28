package 컴백홈;

import java.util.*;
import java.io.*;

public class Solution {
    private static int R, C, K;
    private static char[][] cArr;
    private static int startX, startY;
    private static int endX, endY;
    private static int answer;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static boolean[][] visited;

    private static boolean isWithInRange(int row, int col) {
        return 0 <= row && 0 <= col && row < R && col < C;
    }



    private static void dfs(int x, int y, int cnt){
//        System.out.println(y + " " + x);
        if(cnt == K){
//            System.out.println(y + " " + x);
            if(x == endX && y == endY) answer += 1;
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(!isWithInRange(ny, nx)) continue;
            if(cArr[ny][nx] == 'T') continue;
            if(visited[ny][nx]) continue;

            visited[ny][nx] = true;
            dfs(nx, ny, cnt + 1);
            visited[ny][nx] = false;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        startX = 0;
        startY = R - 1;
        endX = C - 1;
        endY = 0;

        cArr = new char[R][C];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            String s = reader.readLine();
            cArr[i] = s.toCharArray();
        }

        visited[startY][startX] = true;
        dfs(startX, startY, 1);

        System.out.println(answer);

    }
}
