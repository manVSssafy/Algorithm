package 상어_초등학교;

import javax.net.ssl.SSLContextSpi;
import java.io.*;
import java.util.*;

public class Solution {

    private static class Node{
        public final int x;
        public final int y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int N;
    private static int[][] school;
    private static Node[] userIndexNode;
    private static boolean[] visited;
    private static int answer;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};


    private static boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < N && col < N;
    }

    private static void addCnt(int[][] loveArr, int row, int col){
        for(int i = 0; i < 4; i++){
            int ny = dy[i] + row;
            int nx = dx[i] + col;

            if(!isWithInRange(ny, nx)) continue;
            loveArr[ny][nx] += 1;
        }
    }
    private static List<Node> oneCondition(List<Integer> connectUser){

        List<Node> loveList = new ArrayList<>();
        int[][] loveArr = new int[N][N];
        for(int userIdx : connectUser){
            int col = userIndexNode[userIdx].x;
            int row = userIndexNode[userIdx].y;

            addCnt(loveArr, row, col);
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(school[i][j] != -1) continue;
                if(cnt < loveArr[i][j]){
                    loveList = new ArrayList<>();
                    loveList.add(new Node(j, i));
                    cnt = loveArr[i][j];
                }else if(cnt == loveArr[i][j]){
                    loveList.add(new Node(j, i));
                }
            }
        }

        return loveList;
    }

    private static List<Node> twoCondition(List<Node> loveList){

        List<Node> emptyList = new ArrayList<>();
        int emptyCnt = 0;
        for(Node node : loveList){
            int row = node.y;
            int col = node.x;

            int curCount = 0;
            for(int i = 0; i < 4; i++){
                int ny = dy[i] + row;
                int nx = dx[i] + col;

                if(!isWithInRange(ny, nx)) continue;
                if(school[ny][nx] == -1) curCount += 1;
            }

            if(emptyCnt < curCount){
                emptyList = new ArrayList<>();
                emptyList.add(new Node(col, row));
                emptyCnt = curCount;
            }else if(emptyCnt == curCount){
                emptyList.add(new Node(col, row));
            }
        }

        return emptyList;
    }

    private static List<Node> twoSecondCondition(){

        List<Node> emptyList = new ArrayList<>();
        int emptyCnt = 0;
        // 모든 부분 탐색
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                // 방문한 곳은 제외
//                if(visited[col * N + col]) continue;
                if(school[row][col] != -1) continue;
                int curCount = 0;
                for(int i = 0; i < 4; i++){
                    int ny = dy[i] + row;
                    int nx = dx[i] + col;

                    if(!isWithInRange(ny, nx)) continue;
                    if(school[ny][nx] == -1) curCount += 1;
                }

                if(emptyCnt < curCount){
                    emptyList = new ArrayList<>();
                    emptyList.add(new Node(col, row));
                    emptyCnt = curCount;
                }else if(emptyCnt == curCount){
                    emptyList.add(new Node(col, row));
                }
            }
        }

        return emptyList;
    }

    private static int[] threeCondition(List<Node> emptyList){
        int curRow = Integer.MAX_VALUE;
        int curCol = Integer.MAX_VALUE;

        for(Node node : emptyList){
            int row = node.y;
            int col = node.x;

            if(curRow > row){
                curRow = row;
                curCol = col;
            }else if(curRow == row && curCol > col){
                curRow = row;
                curCol = col;
            }
        }

        return new int[]{curRow, curCol};
    }
    private static void whoGameIsRunning(List<Integer> connectUser, int userNumber){
        // (1) 좋아하는 학생 수가 있다면
        List<Node> loveList = oneCondition(connectUser);

        if(loveList.size() == 1){
            int x = loveList.get(0).x;
            int y = loveList.get(0).y;

            school[y][x] = userNumber;
            visited[userNumber] = true;
            userIndexNode[userNumber] = new Node(x, y);
            return;
        }

        // (2) 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        List<Node> emptyList = twoCondition(loveList);
        if(emptyList.size() == 1){
            int x = emptyList.get(0).x;
            int y = emptyList.get(0).y;

            school[y][x] = userNumber;
            visited[userNumber] = true;
            userIndexNode[userNumber] = new Node(x, y);
            return;
        }

        // (3) 행 번호 작은 것, 열 번호 작은것
        int[] rowCol = threeCondition(emptyList);
        int y = rowCol[0];
        int x = rowCol[1];

        school[y][x] = userNumber;
        visited[userNumber] = true;
        userIndexNode[userNumber] = new Node(x, y);
    }

    private static void neverNotGameIsRunning(int userNumber){
        // (1) 좋아하는 학생 모두가 게임을 하지 않았다면
        // (2) 에서 완탐
        List<Node> emptyList = twoSecondCondition();
        if(emptyList.size() == 1){
            int x = emptyList.get(0).x;
            int y = emptyList.get(0).y;

            school[y][x] = userNumber;
            visited[userNumber] = true;
            userIndexNode[userNumber] = new Node(x, y);
            return;
        }

        // (3) 행 번호 작은 것, 열 번호 작은것
        int[] rowCol = threeCondition(emptyList);
        int y = rowCol[0];
        int x = rowCol[1];

        school[y][x] = userNumber;
        visited[userNumber] = true;
        userIndexNode[userNumber] = new Node(x, y);
    }

    private static void printSchool(){
        for(int i = 0; i < N; i++){
            System.out.println(Arrays.toString(school[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        school = new int[N][N];
        visited = new boolean[N * N];
        userIndexNode = new Node[N * N];

        ArrayList<Integer>[] result = new ArrayList[N * N];



        // 사용자 위치 저장
        for(int i = 0; i < N * N; i++){
            userIndexNode[i] = new Node( -1, -1);
            school[i / N][i % N] = -1;
        }


        for(int i = 1; i <= N * N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int userNumber = Integer.parseInt(tokenizer.nextToken()) - 1;
//            System.out.println(userNumber);
//            int[] arr = new int[4];
            List<Integer> connectUserList = new ArrayList<>();
            ArrayList<Integer> people = new ArrayList<>();

            for(int j = 0; j < 4; j++){
                // 사용자 번호 - 1
                int connectUser = Integer.parseInt(tokenizer.nextToken()) - 1;
                people.add(connectUser);
                if(visited[connectUser]) connectUserList.add(connectUser);
            }
            result[userNumber] = people;


            if(connectUserList.size() > 0){
                whoGameIsRunning(connectUserList, userNumber);
            }else{
                neverNotGameIsRunning(userNumber);
            }

        }

        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                boolean[] resultVisited = new boolean[N * N];
//                System.out.print("시작 값 : " + school[row][col] + " ");
//                int[] resultArr = new int[4];
                for(int k = 0; k < 4; k++){
//                    System.out.print(result[school[row][col]].get(k) + " ");
                    resultVisited[result[school[row][col]].get(k)] = true;
                }
//                System.out.println();

                int count = 0;
                for(int k = 0; k < 4; k++) {
                    int ny = dy[k] + row;
                    int nx = dx[k] + col;

                    if (!isWithInRange(ny, nx)) continue;
                    if (!resultVisited[school[ny][nx]]) continue;
                    count++;
                }

                if(count >= 1) answer += Math.pow(10, count - 1);
//                System.out.println(answer + " " + count);

            }
        }
        System.out.println(answer);

        reader.close();
    }
}
