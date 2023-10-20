package 인구_이동;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, L, R;
    private static int[][] arr;

    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int result;


    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col && row < N && col < N ) return true;
        else return false;
    }

    private static boolean isWithInRange2(int peopleCount){
        if(L <= peopleCount && peopleCount <= R) return true;
        else return false;
    }

    private static boolean isItNotEqualArr(int[][] copyArr){
        for(int i = 0; i < copyArr.length; i++){
            for(int j = 0; j < copyArr[0].length; j++) if(copyArr[i][j] != arr[i][j]) return true;
        }
        return false;
    }
    private static void bfs(int row, int col){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(row);
        queue.add(col);
        visited[row][col] = true;
        ArrayList<Integer> list = new ArrayList<>();
        // 이차원 -> 일차원
        list.add(row * N + col);

        // 총합
        int allSum = arr[row][col];

        while(!queue.isEmpty()){
            int curRow = queue.poll();
            int curCol = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = dx[i] + curCol;
                int ny = dy[i] + curRow;

                // 범위 이내라면, 방문한 곳이라면 pass
                if(!isWithInRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(!isWithInRange2(Math.abs(arr[curRow][curCol] - arr[ny][nx]))) continue;

                allSum += arr[ny][nx];
                visited[ny][nx] = true;
                list.add(ny * N + nx);
                queue.add(ny);
                queue.add(nx);
            }
        }

        for(int inList : list){
            int curRow = inList / N;
            int curCol = inList % N;

            arr[curRow][curCol] = allSum / list.size();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][N];
        visited = new boolean[N][N];

        int time = 0;
        int[][] copyArr = new int[N][N];

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while(true){
            for(int i = 0; i < N; i++) Arrays.fill(visited[i], false);

            for(int i = 0 ; i < N; i++) copyArr[i] = arr[i].clone();

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(visited[i][j]) continue;
                    bfs(i, j);
                }
            }

            if(!isItNotEqualArr(copyArr)) break;
            time++;
        }

        System.out.println(time);
//        for(int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(arr[i]));
//        }

        reader.close();
    }
}
