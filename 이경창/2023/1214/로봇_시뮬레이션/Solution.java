package 로봇_시뮬레이션;

import java.util.*;
import java.io.*;


public class Solution {

    private static class Robot{
        public final int x;
        public final int y;
        public final int d;

        Robot(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }

        private Robot update(int x, int y, int d){
            return new Robot(x, y, d);
        }
    }

    private static int rowLen, colLen;
    private static int N, M;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static ArrayList<Robot> robotList;
    private static int[][] graph;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < rowLen && col < colLen;
    }

    private static void print(){
        for(int i = 0; i < rowLen; i++){
            System.out.println(Arrays.toString(graph[i]));
        }
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        colLen = Integer.parseInt(tokenizer.nextToken());
        rowLen = Integer.parseInt(tokenizer.nextToken());

        graph = new int[rowLen][colLen];
        robotList = new ArrayList<>();
        tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        for(int i = 0; i < rowLen; i++){
            Arrays.fill(graph[i], -1);
        }

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            int row = rowLen - Integer.parseInt(tokenizer.nextToken());
            // W : 0, N : 1, E : 2, S : 3
            String s = tokenizer.nextToken();
            int data;
            if(s.equals("W")) data = 0;
            else if(s.equals("N")) data = 1;
            else if(s.equals("E")) data = 2;
            else data = 3;

            robotList.add(new Robot(col, row, data));

            graph[row][col] = i;
        }

        int result = 0;
        int resultAIndex = -1;
        int resultBIndex = -1;
//        print();
        for(int i = 0; i < M; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int robotIdx = Integer.parseInt(tokenizer.nextToken()) - 1;
            char c = tokenizer.nextToken().charAt(0);
            int rangeCount = Integer.parseInt(tokenizer.nextToken());
            Robot curRobot = robotList.get(robotIdx);
            int row = curRobot.y;
            int col = curRobot.x;
            int d = curRobot.d;

//            System.out.println(i + " 번째 " + " 현재 위치 : " + row + " " + col + " " + d);

            if(result >= 1) continue;

            for(int j = 0; j < rangeCount; j++){
                // L 왼쪽 90도 회전, R 오른쪽 90도 회전
                // F 앞으로 한칸 움직임
                if(c == 'L'){
                    d = (d - 1 < 0 ? 3 : d - 1);
                }else if(c == 'R'){
                    d = (d + 1) % 4;
                }else{
                    row += dy[d];
                    col += dx[d];

                    // 범위를 벗어난 경우
                    if(!isWithInRange(row, col)){
//                        System.out.println(row + " " + col);
                        result = 1;
                        resultAIndex = robotIdx + 1;
                        break;
                    }else if(graph[row][col] >= 0){
                        result = 2;
                        resultAIndex = robotIdx + 1;
                        resultBIndex = graph[row][col] + 1;
                        break;
                    }else {
//                        System.out.println(row + " " + col + " <= " + (row - dy[d]) + " " + (col - dx[d]));
                        graph[row][col] = graph[row - dy[d]][col - dx[d]];
                        graph[row - dy[d]][col - dx[d]] = -1;
                    }
                }
                robotList.set(robotIdx, robotList.get(robotIdx).update(col, row, d));
            }
//            print();
        }


        if(result == 1) System.out.println("Robot " + resultAIndex + " crashes into the wall");
        else if(result == 2) System.out.println("Robot " + resultAIndex + " crashes into robot " + resultBIndex);
        else System.out.println("OK");


        reader.close();
    }
}
