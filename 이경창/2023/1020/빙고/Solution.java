package 빙고;

import java.util.*;
import java.io.*;

public class Solution {

    private static int[] arr;
    private static int[][] graph;
    private static boolean[][] visited;

    private static boolean bingo(){

        int count = 0;
        for(int i = 0; i < 5; i++){
            int curCount = 0;
            int curCount2 = 0;
            for(int j = 0; j < 5; j++){
                if(visited[i][j]) curCount++;
                if(visited[j][i]) curCount2++;
            }

            if(curCount == 5) count++;
            if(curCount2 == 5) count++;
        }


        int curCount = 0;
        int curCount2 = 0;
        for(int i = 0; i < 5; i++){
            if(visited[i][i]) curCount++;
            if(visited[i][4 - i]) curCount2++;
        }

        if(curCount == 5) count++;
        if(curCount2 == 5) count++;

        return (count >= 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[25];
        visited = new boolean[5][5];
        graph = new int[5][5];
        int answer = 0;

        for(int i = 0; i < 5; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 5; j++){
                int number = Integer.parseInt(tokenizer.nextToken()) - 1;
                arr[number] = i * 5 + j;
            }
        }


        for(int i = 0; i < 5; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 5; j++){
                int number = Integer.parseInt(tokenizer.nextToken()) - 1;
                graph[i][j] = number;
            }
        }

        Loop1:
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                int number = graph[i][j];
                int curIndex = arr[number];

                visited[curIndex / 5][curIndex % 5] = true;

                if(i >= 2 && bingo()){
                    answer = (i * 5 + j) + 1;
                    break Loop1;
                }
            }
        }

        System.out.println(answer);
        reader.close();
    }
}
