package 주사위_굴리기;

import java.util.*;
import java.io.*;

public class Solution {

    private static class Dice{
        public final int x;
        public final int y;
//        public final int diceNumber;

        Dice(int y, int x){
            this.x = x;
            this.y = y;
//            this.diceNumber = diceNumber;
        }
    }

    private static int N, M, startY, startX, K;
    private static int[][] travel;
    private static int[][] diceArr;
    private static Dice[] dices;

    // 남북 dxy
    private static int[] dx = { 1, -1, 0, 0};
    private static int[] dy = { 0, 0, -1, 1};

    // 동서 dxy
    private static int[] dx2 = {-1, 1, 1, -1};
    private static int[] dy2 = {1, 1, -1, -1};
    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col && row < N && col < M) return true;
        else return false;
    }

    private static int[][] testCopy(){
        int[][] testCase = new int[4][4];

        for(int i = 0; i < 4; i++) testCase[i] = diceArr[i].clone();

        return testCase;
    }

    private static void rotationEastOrWest(int direction){
        int[][] testCase = testCopy();
        int curX = 1;
        int curY = 0;
        System.out.println("현재 방향 : " + direction);
        for(int i = 0; i < 4; i++){
            int index = i;
            if(direction == 1 && i % 2 == 0) index += 1;
            else if(direction == 1) index -= 1;


            System.out.println(curY + " " + curX + " index : " + index + " " + (curY + dy2[index]) + " " + (curX + dx2[index]));
            diceArr[curY + dy2[index]][curX + dx2[index]] = testCase[curY][curX];

            curX = curX + dx2[index];
            curY = curY + dy2[index];
        }
    }

    private static void rotationSouthOrNorth(int direction){
        int[][] testCase = testCopy();

        for(int i = 0; i < 4; i++){
            int nextIndex = (i + dy[direction] < 0 ? 3 : (i + dy[direction]) % 4);
            diceArr[nextIndex][1] = testCase[i][1];
        }
    }

    private static void printDiceArr(){
        for(int i = 0; i < diceArr.length; i++){
            System.out.println(Arrays.toString(diceArr[0]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder builder = new StringBuilder();

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        startY = Integer.parseInt(tokenizer.nextToken());
        startX = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        travel = new int[N][M];
        diceArr = new int[4][4];
        dices = new Dice[6];

        dices[0] = new Dice(1, 1);
        dices[1] = new Dice(0, 1);
        dices[2] = new Dice(1, 2);
        dices[3] = new Dice(1, 0);
        dices[4] = new Dice(2, 1);
        dices[5] = new Dice(3, 1);

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                int number = Integer.parseInt(tokenizer.nextToken());
                travel[i][j] = number;
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        while(K-- > 0){
            int d = Integer.parseInt(tokenizer.nextToken()) - 1;
            int nextY = startY + dy[d];
            int nextX = startX + dx[d];


            // 범위를 벗어난 경우 pass
            if(!isWithInRange( nextY, nextX)) continue;

            // 2보다 작은 경우, 동서
            // 2보다 크거나 같은 경우, 남북
            if(d < 2) rotationEastOrWest(d);
            else rotationSouthOrNorth(d);

            //            if(travel[nextY][nextX] == 0) travel[nextY][nextX] = diceArr[][1];
            if(travel[nextY][nextX] == 0) travel[nextY][nextX] = diceArr[dices[5].y][dices[5].x];
            else{
                int nextNumber = travel[nextY][nextX];
                diceArr[dices[5].y][dices[5].x] = nextNumber;
                travel[nextY][nextX] = 0;
            }

            startY = nextY;
            startX = nextX;

            builder.append(diceArr[dices[0].y][dices[0].x]).append("\n");
        }

        System.out.print(builder.toString());

        reader.close();
    }
}
