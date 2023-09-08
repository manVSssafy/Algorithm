package 미세먼지_안녕;

import java.io.*;
import java.util.*;

public class Solution2 {

    private static int R, C, T;
    private static int[][] arr;
    private static int upX, upY;
    private static int downX, downY;

    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};


    private static boolean isItAirCleaner(int row, int col){
        if((row == upY && col == upX) || (row == downY && col == downX)) return true;
        return false;
    }

    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col && row < R && col < C) return true;
        return false;
    }
    // 미세먼지 일 때 계산식
    private static void calculate(int row, int col, int[][] clearRoomArr) {
        int spread = 0;
        for(int i = 0; i < 4; i++){
            int nx = col + dx[i];
            int ny = row + dy[i];

            if(!isItAirCleaner(ny, nx) && isWithInRange(ny, nx)){
                spread++;
                clearRoomArr[ny][nx] += (arr[row][col] / 5);
            }
        }

        clearRoomArr[row][col] += (arr[row][col] - (arr[row][col] / 5) * spread);

    }

    private static void airCleaner(int[][] clearRoomArr){
        for(int i = 0; i < R; i++) System.out.println(Arrays.toString(clearRoomArr[i]));
        System.out.println();
        int startX = upX;
        int startY = upY;
        int index = 0;
        // 반시계 방향
        while(true){
            if(isWithInRange(startY + dy[index], startX + dx[index])){
                clearRoomArr[startY][startX] = clearRoomArr[startY + dy[index]][startX + dx[index]];
                startX += dx[index];
                startY += dy[index];
            }else{
                index += 1;
            }
            if(isItAirCleaner(startY, startX)) break;
            System.out.println("테스트");
        }

        // 시계 방향
        startX = downX;
        startY = downY;
        index = 2;
        while(true){
            if(isWithInRange(startY + dy[index], startX + dx[index])){
                clearRoomArr[startY][startX] = clearRoomArr[startY + dy[index]][startX + dx[index]];
                startX += dx[index];
                startY += dy[index];
            }else{
                index -= 1;
                if(index < 0) index = 3;
            }
            if(isItAirCleaner(startY, startX)) break;
        }
    }
    private static void clearRoom(){
        int[][] clearRoomArr = new int[R][C];
        // (1) 미세먼지
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(arr[i][j] != 0 && !isItAirCleaner(i, j)){
                    // clearRoomArr에 결과 저장
                    calculate(i, j, clearRoomArr);
                }
            }
        }

        // (2) 공기청정기
        airCleaner(clearRoomArr);
        for(int i = 0; i < R; i++) System.out.println(Arrays.toString(clearRoomArr[i]));
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        T = Integer.parseInt(tokenizer.nextToken());

        arr = new int[R][C];
        upX = -1;
        upY = -1;
        for(int i = 0; i < R; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < C; j++){
                int num = Integer.parseInt(tokenizer.nextToken());

                // -1이라면 공기청정기 위치
                if(num == -1 && upX == -1 && upY == -1){
                    upY = i;
                    upX = j;
                }else if(num == -1){
                    downY = i;
                    downX = j;
                }else{
                    // 공기청정기 이외 위치
                    arr[i][j] = num;
                }
            }
        }

        for(int i = 0 ; i < T; i++){
            clearRoom();
        }

        reader.close();
    }
}
