package 행렬_테두리_회전하기;

import java.util.*;

class Solution {

    private int rowLen, colLen;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int[][] arr;

    private boolean isWithInRange(int row, int col, int startRow, int startCol, int endRow, int endCol){
        return startRow <= row && startCol <= col && row <= endRow && col <= endCol;
    }

    private int[][] testCopy(){
        int[][] testCase = new int[rowLen][colLen];

        for(int i = 0; i < rowLen; i++) testCase[i] = arr[i].clone();

        return testCase;
    }

    public int[] solution(int rows, int columns, int[][] queries) {

        rowLen = rows;
        colLen = columns;
        boolean[][] visited = new boolean[rowLen][colLen];
        arr = new int[rowLen][colLen];

        int count = 1;
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                arr[i][j] = count++;
            }
        }

        for(int[] inQuery : queries){
            int row1 = inQuery[0] - 1;
            int col1 = inQuery[1] - 1;
            int row2 = inQuery[2] - 1;
            int col2 = inQuery[3] - 1;

            int y = row1;
            int x = col1;
            int d = 0;

            for(int i = 0; i < rowLen; i++) Arrays.fill(visited[i], false);

            int[][] testCase = testCopy();
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // 시작
            while(true){
                int nx = x + dx[d];
                int ny = y + dy[d];
                // System.out.println(ny + " " + nx + " " + isWithInRange(ny, nx));

                if(!isWithInRange(ny, nx, row1, col1, row2, col2) || visited[ny][nx]){
                    d = (d + 1) % 4;
                    nx = x + dx[d];
                    ny = y + dy[d];

                    if(!isWithInRange(ny, nx, row1, col1, row2, col2) || visited[ny][nx]){
                        break;
                    }
                }

                visited[ny][nx] = true;
                arr[ny][nx] = testCase[y][x];
                y = ny;
                x = nx;
                // System.out.println(y + " " + x + " 값 : " + arr[ny][nx]);
                pq.add(arr[ny][nx]);
            }

            answer.add(pq.peek());

        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
