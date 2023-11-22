package Puyo_Puyo;

import java.io.*;
import java.util.*;

public class Solution {

    /*

    규칙
    R : 1
    G : 2
    B : 3
    P : 4
    Y : 5

    * */

    private static class Coordinate {
        public final int x;
        public final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int[][] stages;
    private static boolean[][] visited;
    private static int rowSize;
    private static int colSize;
    private static int answer;

    private static boolean isWithInRange(int row, int col) {
        return 0 <= row && 0 <= col && row < rowSize && col < colSize;
    }

    private static int bfs(int row, int col) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(col, row));
        boolean[][] inVisited = new boolean[rowSize][colSize];
        int curData = stages[row][col];
        inVisited[row][col] = true;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(row * colSize + col);

        while (queue.size() > 0) {
            Coordinate coordinate = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = coordinate.x + dx[i];
                int ny = coordinate.y + dy[i];

                if (!isWithInRange(ny, nx)) continue;
                if (inVisited[ny][nx]) continue;
                if (curData != stages[ny][nx]) continue;
                inVisited[ny][nx] = true;
                list.add(ny * colSize + nx);
                queue.add(new Coordinate(nx, ny));
            }
        }

        for (int curIndex : list) {
            int rowIndex = curIndex / colSize;
            int colIndex = curIndex % colSize;
            visited[rowIndex][colIndex] = true;

            // 4이상일 때는 그 구간은 모두 0처리
            if(list.size() >= 4) stages[rowIndex][colIndex] = 0;
        }

//        System.out.println("list size : " + list.size());

        // 4이상 일 경우에는 갯수 더하기 1
        return list.size() >= 4 ? 1 : 0;

    }

    private static int[][] testCopy(){
        int[][] testCase = new int[rowSize][colSize];

        for(int i = 0; i < rowSize; i++){
            testCase[i] = stages[i].clone();
        }

        return testCase;
    }

    private static void printStation(){
        for(int i =0 ; i < rowSize; i++){
            System.out.println(Arrays.toString(stages[i]));
        }
        System.out.println();
    }
    private static boolean gameStation() {

//        System.out.println("시작");

        int isItPuyoStation = 0;
        // (1) 같은색 뿌요가 4이상인 위치 지점은 모두 0으로 처리한다.
        for (int i = rowSize - 1; i >= 0; i--) {
            for (int j = 0; j < colSize; j++) {
                if (visited[i][j]) continue;
                if(stages[i][j] == 0) continue;

                // 방문하지 않은 경우
                isItPuyoStation += bfs(i, j);
            }
        }

        // 4이상인 뿌요가 있을 때(그룹 일 경우에도 여러 곳일 경우에도) 더하기 1을 해준다.
//        System.out.println("size : "  + isItPuyoStation);
        if(isItPuyoStation > 0) answer += 1;
        else return false;

        // (2) 라인을 정리한다. 4이상인 뿌요로 위에 있던 뿌요가 없어진 자리를 메운다.
//        boolean[] colVisited = new boolean[colSize];

        for(int col = 0; col < colSize; col++){
            Queue<Integer> queue = new LinkedList<>();

            for(int row = rowSize - 1; row >= 0; row--){
                if(stages[row][col] != 0) queue.add(stages[row][col]);
                stages[row][col] = 0;
            }
            int rowIndex = 11;

            while(queue.size() > 0){
                stages[rowIndex--][col] = queue.poll();
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        rowSize = 12;
        colSize = 6;
        stages = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];


        for (int i = 0; i < rowSize; i++) {
            char[] cArr = reader.readLine().toCharArray();

            for (int j = 0; j < colSize; j++) {
                if (cArr[j] == 'R') stages[i][j] = 1;
                else if (cArr[j] == 'G') stages[i][j] = 2;
                else if (cArr[j] == 'B') stages[i][j] = 3;
                else if (cArr[j] == 'P') stages[i][j] = 4;
                else if (cArr[j] == 'Y') stages[i][j] = 5;
                else stages[i][j] = 0; // .일 경우
            }
        }

        while(true){
            // 방문처리 초기화
            for(int i = 0; i < rowSize; i++) Arrays.fill(visited[i], false);
//            printStation();
            if(!gameStation()) break;

        }


        System.out.println(answer);

        reader.close();
    }
}
