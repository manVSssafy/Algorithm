package 테트로미노;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N, M;
    private static int[][] arr;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int[] dx2 = {-1, 1, 1, -1};
    private static int[] dy2 = {-1, -1, 1, 1};
    private static int game1(int y, int x){
        int sum = arr[y][x];
        for(int i = 0; i < 3; i++){
            int nx = dx[2] + x;
            int ny = dy[2] + y;
            if(!isWithInRange(ny, nx)) break;
            sum += arr[ny][nx];
        }

        int sum2 = arr[y][x];
        for(int i = 0; i < 3; i++){
            int nx = dx[0] + x;
            int ny = dy[0] + y;
            if(!isWithInRange(ny, nx)) break;
            sum2 += arr[ny][nx];
        }

        return Math.max(sum, sum2);
    }

    private static int game2(int y, int x){
        int sum = arr[y][x];

        if(!(y + 1 < N && x + 1 < M)) return 0;

        sum += arr[y + dy[2]][x + dx[2]];
        sum += arr[y + dy[3]][x + dx[3]];
        sum += arr[y + dy2[2]][x + dx2[2]];

        return sum;
    }

    private static int game3(int y, int x){
        int result = 0;

        for(int i = 0; i < 4; i++){
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            int sum = arr[y][x];
            if(!isWithInRange(ny, nx)) continue;

            sum += arr[ny][nx];

            // 경우의 1번의 경우 인덱스 + 1 움직임
            int nextIdx = (i - 1 < 0 ? 3 : i - 1);

            int firstNy = dy[nextIdx] + ny;
            int firstNx = dx[nextIdx] + nx;
            if(!isWithInRange(firstNy, firstNx)) continue;

            int sum2 = sum + arr[firstNy][firstNx];
            firstNy = dy[i] + firstNy;
            firstNx = dx[i] + firstNx;

            if(!isWithInRange(firstNy, firstNx)) continue;
            sum2 = sum2 + arr[firstNy][firstNx];
            result = Math.max(sum2, result);

            // 경우의 2번의 경우 인덱스 - 1 움직임
            int secondNy = dy[nextIdx] + ny;
            int secondNx = dx[nextIdx] + nx;
            if(!isWithInRange(secondNy, secondNx)) continue;

            int sum3 = sum + arr[secondNy][secondNx];
            secondNy = dy[i] + secondNy;
            secondNx = dx[i] + secondNx;

            if(!isWithInRange(secondNy, secondNx)) continue;
            sum3 = sum3 + arr[secondNy][secondNx];

            result = Math.max(sum3, result);

        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }


        for(int i =0; i< N; i++){
            for(int j = 0; j < M; j++){
                //(1) 1번 모양
                int curY = i;
                int curX = j;
                game1(curY, curX);
            }
        }

        reader.close();
    }
}
