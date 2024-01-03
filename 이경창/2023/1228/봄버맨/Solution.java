package 봄버맨;

import java.util.*;
import java.io.*;

public class Solution {

    private static int R, C, N;
    private static int[][] graph;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < R && col < C;
    }
    private static void updateEvenGraph(int time){
        for(int i = 0; i < R; i++){
            for(int j = 0 ; j < C; j++){
                if(graph[i][j] == -1) graph[i][j] = time;
            }
        }
    }
    private static void updateOddGraph(int time){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(graph[i][j] == time - 3) {
                    graph[i][j] = -1;
                    for(int k = 0; k < 4; k++){
                        int nx = dx[k] + j;
                        int ny = dy[k] + i;

                        if(!isWithInRange(ny, nx)) continue;
                        if(graph[ny][nx] == time - 3) continue;
                        graph[ny][nx] = -1;
                    }
                }
            }
        }
    }
    private static void gameStation(){
        for(int time = 2; time <= N; time++){
            if(time % 2 == 0){
                // 짝수인 경우
                updateEvenGraph(time);
            }else{
                // 홀수인 경우
                updateOddGraph(time);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder builder = new StringBuilder();

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());

        graph = new int[R][C];

        for(int i = 0; i < R; i++){
            Arrays.fill(graph[i], -1);
        }

        for(int i = 0; i < R; i++){
            char[] c = reader.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                // -1은 빈칸, 0은 초
                graph[i][j] = (c[j] == '.' ? -1 : 0);
            }
        }

        // 짝수인경우 모두 칸 0
        if(N % 2 == 0){
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    builder.append('O');
                }
                builder.append('\n');
            }
        }else{
            gameStation();

            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    builder.append(graph[i][j] == -1 ? '.' : 'O');
                }
                builder.append('\n');
            }
        }

        System.out.print(builder.toString());
        reader.close();
    }
}
