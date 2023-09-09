package 미세먼지_안녕;

import java.io.*;
import java.util.*;

public class Solution2 {

    private static int R, C, T;
    private static int[][] arr;
    private static int upX, upY;
    private static int downX, downY;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};


    private static int[] dx2 = {0, 1, 0, -1};
    private static int[] dy2 = {1, 0, -1, 0};



    private static boolean isItAirCleaner(int row, int col){
        if((row == upY && col == upX) || (row == downY && col == downX)) return true;
        return false;
    }

    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col && row < R && col < C) return true;
        return false;
    }

    private static boolean isWithInRange2(int row, int col){
        if(0 <= row && 0 <= col && row <= upY && col < C) return true;
        return false;
    }
    private static boolean isWithInRange3(int row, int col){
        if(downY <= row && 0 <= col && row < R && col < C) return true;
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

//        System.out.println("upX "+ upX + " " + upY);
//        System.out.println("downX " + downX + " " + downY);
//        for(int i = 0 ; i < clearRoomArr.length; i++){
//            System.out.println(Arrays.toString(clearRoomArr[i]));
//        }
//
//        System.out.println();

        // 반시계 방향
        // 시작점 : upX, upY
        int startX = upX;
        int startY = upY;
        int d = 0;

        while(true){
            int nx = startX + dx[d];
            int ny = startY + dy[d];

            if(!isWithInRange2(ny, nx)){
                d = (d + 1) % 4;
                nx = startX + dx[d];
                ny = startY + dy[d];
            }

            if(nx == upX && ny == upY){
                clearRoomArr[startY][startX] = 0;
                break;
            }else{
                clearRoomArr[startY][startX] = clearRoomArr[ny][nx];
                startX = nx;
                startY = ny;
            }
        }

//        for(int i = 0 ; i < clearRoomArr.length; i++){
//            System.out.println(Arrays.toString(clearRoomArr[i]));
//        }
//
//        System.out.println();

        // 시계 방향
        startX = downX;
        startY = downY;
        d = 0;

        while(true){
            int nx = startX + dx2[d];
            int ny = startY + dy2[d];

            if(!isWithInRange3(ny, nx)){
                d = (d + 1) % 4;
                nx = startX + dx2[d];
                ny = startY + dy2[d];
            }


            if(nx == downX && ny == downY){
                clearRoomArr[startY][startX] = 0;
                break;
            }else{
                clearRoomArr[startY][startX] = clearRoomArr[ny][nx];
                startX = nx;
                startY = ny;
            }
        }


        for(int i = 0 ; i < clearRoomArr.length; i++){
            System.out.println(Arrays.toString(clearRoomArr[i]));
        }

        System.out.println();
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
