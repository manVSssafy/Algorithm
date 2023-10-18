package 스토쿠;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node {
        public final int x;
        public final int y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int[][] arr;
    private static int zeroCnt;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static ArrayList<Node> list;

    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col &&  row < 9 && col < 9) return true;
        else return false;
    }

    private static boolean findNumberList(Node n, int findNumber){

        // 가로
        for(int i = 1; i <= 9; i++){
            if(arr[n.y][i - 1] == findNumber) return false;
        }

        // 세로
        for(int i = 1; i <= 9; i++){
            if(arr[i - 1][n.x] == findNumber) return false;
        }

        // 0, 3, 6
        int curRow = n.y / 3 * 3;
        int curCol = n.x / 3 * 3;

//        System.out.println("시작 위치 : " + curRow + " " + curCol);
        for(int i = curRow; i < curRow + 3; i++){
            for(int j = curCol; j < curCol + 3; j++){
//                System.out.println(i + " " + j);
                if(arr[i][j] == findNumber) return false;
            }
        }

        return true;
    }
//    private static boolean check = false;
    private static void printTracking(){
        for(int i  =0 ; i< 9; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }
    private static void backtracking(int cnt){

        if(list.size() == cnt){
            // 종료

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        for(int i = 1; i <= 9 ;i++){
            Node n = list.get(cnt);

            if(findNumberList(n, i)){
                arr[n.y][n.x] = i;
                backtracking(cnt + 1);
                arr[n.y][n.x] = 0;
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9][9];
        list = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for(int j = 0; j < 9; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
                if(arr[i][j] == 0){
                    list.add(new Node(j, i));
                }
            }
        }

        backtracking(0);

        reader.close();
    }
}
