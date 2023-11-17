package 주사위_굴리기2;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Dices{
        public final int x;
        public final int y;

        Dices(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0 , 1};
    private static int[][] travel;
    private static int[][] diceArr;
    private static Dices[] dices;
    private static int N, M, K;
    private static int direction;

    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    private static int[][] testCopy(){
        int[][] testCase = new int[4][3];
        for(int i = 0; i < 4; i++){
            testCase[i] = diceArr[i].clone();
        }
        return testCase;
    }

    // 남, 북
    private static void rotationSouthOrNorth(int d){
        // 남, 북 : 1, 3
        int[][] testCase = testCopy();

        // 0, 1, 2, 3
        for(int i = 0; i < 4; i++){
            int row = (i + dy[d] < 0 ? 3 : i + dy[d]);
            diceArr[row][1] = testCase[i][1];
        }
    }

    // 동, 서
    private static void rotationEastOrWest(int d){
        int[] location = {3, 4, 5, 10};

        // 동쪽인 경우, 반대 방향
        if(d == 0) location = Arrays.stream(location).boxed().sorted((a, b) -> (b - a)).mapToInt(Integer::intValue).toArray();

        int[][] testCase = testCopy();

        for(int i = 0; i < 4; i++){
            int curY = location[i] / diceArr[0].length;
            int curX = location[i] % diceArr[0].length;
            int nextY = (location[(i + 1) % 4] + dy[d]) / diceArr[0].length;
            int nextX = (location[(i + 1) % 4] + dx[d]) % diceArr[0].length;


            diceArr[nextY][nextX] = testCase[curY][curX];
        }
    }

    private static int bfs(int startX, int startY){
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX);
        queue.add(startY);
        boolean[][] visited = new boolean[N][M];
        int startData = travel[startY][startX];
        visited[startY][startX] = true;
        result += startData;

        System.out.println("시작 : " + startY + " " + startX);

        while(queue.size() > 0){
            int curX = queue.poll();
            int curY = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(!isWithInRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(startData != travel[ny][nx]) continue;
                visited[ny][nx] = true;
                result += travel[ny][nx];
                System.out.println("방문 : " + ny + " " + nx);
                queue.add(nx);
                queue.add(ny);
            }
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        travel = new int[N][M];
        diceArr = new int[4][3];
        dices = new Dices[6];
        direction = 2; // 0 : ->, 1 : 아랫방향, 2 : <-, 3 : 윗방향

        int answer = 0;

        dices[0] = new Dices(1, 1);
        dices[1] = new Dices(1, 0);
        dices[2] = new Dices(2, 1);
        dices[3] = new Dices(0, 1);
        dices[4] = new Dices(1, 2);
        dices[5] = new Dices(1, 3);

        // 주사위 위치에 숫자 넣기
        for(int i = 0; i < 6; i++){
            diceArr[dices[i].y][dices[i].x] = (i + 1);
        }

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                travel[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int startY = 0;
        int startX = 0;

        for(int time = 1; time <= K; time++){

            // 범위를 벗어날 경우
            if(!isWithInRange(startY + dy[direction], startX + dx[direction])){
                direction = (direction + 2) % 4;
            }
            startX += dx[direction];
            startY += dy[direction];

            /* 주사위 돌리기
            rotationSouthOrNorth : 2, 0 (남, 북)
            rotationEastOrWest : 3, 1 (동, 서)
            */
            if(direction % 2 == 0){
                rotationSouthOrNorth(direction);
            }else{
                rotationEastOrWest(direction);
            }

            // bfs
            answer += bfs(startX, startY);
            System.out.println("answer : " + answer + " 방향 : " + direction);
            System.out.println("가장 밑 방향 : " + diceArr[dices[5].y][dices[5].x]);
            System.out.println();


            /*
            A : 주사위 아랫면, B : 지도
            A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
            A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
            A = B인 경우 이동 방향에 변화는 없다.
            */


            if(diceArr[dices[5].y][dices[5].x] > travel[startY][startX]){
                direction = (direction + 1) % 4;
            }else if(diceArr[dices[5].y][dices[5].x] < travel[startY][startX]){
                direction = (direction - 1);
                if(direction < 0) direction = 3;
            }
        }


        System.out.println(answer);


        reader.close();
    }
}
