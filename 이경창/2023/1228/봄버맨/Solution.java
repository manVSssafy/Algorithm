package 봄버맨;

import java.util.*;
import java.io.*;

public class Solution {

    private static int R, C, N;
    private static int[][] arr;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < R && col <C;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        arr = new int[R][C];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < R; i++){
            char[] c = reader.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                if(c[j] == 'O') arr[i][j] = 0;
                else arr[i][j] = -1;
            }
        }

        queue.add(3);

        if(N == 1){
            // 전부 O
        }else if(N == 2){
            // 전부 O에서 첫부분만 X처리
        }

        queue.add(2 + 3);

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(arr[i][j] == -1) arr[i][j] = 5;
            }
        }

        for(int time = 3; time <= N; time++){
            if(queue.peek() == time){
                // 규칙 부분 삭제
                for(int i = 0; i < R; i++){
                    for(int j = 0; j < C; j++){
                        if(arr[i][j] == time){
                            for(int k = 0; k < 4; k++){
                                int nx = dx[k] + j;
                                int ny = dy[k] + i;

                                if(!isWithInRange(ny, nx)) continue;
                                arr[ny][nx] = time + 3;
                            }
                        }
                    }
                }
                queue.add(time + 2);
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        reader.close();
    }
}
