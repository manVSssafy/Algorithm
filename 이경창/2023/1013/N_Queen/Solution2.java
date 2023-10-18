package N_Queen;

import java.io.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

public class Solution2 {
    private static class Pair{
        public final int x;
        public final int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int hashCode(){
            return Objects.hash(this.x , this.y);
        }


        public boolean equals(Object o){
            Pair p = (Pair)o;
            if(p.x == this.x && p.y == this.y) return true;
            else return false;
        }
    }
    private static int N;
    private static Set<Set<Pair>> result;
    private static int[][] dxy = {
            {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };


    private static boolean isWithInRange(int row, int col){
        if(0 <= row && 0 <= col &&  row < N && col < N) return true;
        else return false;
     }
     private static void printList(ArrayList<Pair> list){
        for(Pair p : list){
            System.out.println(p.y + " " + p.x);
        }
     }
    private static void backtracking(int cnt, int row, int col, boolean[] visited, Set<Pair> set){
//        System.out.println("횟수 : " + cnt);
        if(cnt == N){
            result.add(new HashSet<>(set));
            return;
        }

        for(int d = 0 ; d < 8; d++){
            int nx = col;
            int ny = row;

            // 최대 N번 전진할 수 있다.
            for(int index = 0; index < N; index++){
                nx = dxy[d][0] + nx;
                ny = dxy[d][1] + ny;

                if(isWithInRange(ny, nx)){
                    // 방문한 곳이면 out
                    if(visited[ny * N + nx]) return;
                }else break;
            }

        }

        // 8방면 다 확인했을 때 방문한 곳이 없다면 현재 위치 검토
        for(int i = 0 ; i < N * N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            Pair pair = new Pair(i /N, i % N);
            set.add(pair);
            backtracking(cnt + 1, i / N, i % N, visited, set);
            set.remove(pair);
            visited[i] = false;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        boolean[] visited = new boolean[N * N];
        result = new HashSet<>();


        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(visited, false);

                backtracking(1, i, j , visited, new HashSet<>());
            }
        }


        int answer = 0;
        for(Set<Pair> set : result){
//            List<Pair> list = set.stream().collect(Collectors.toList());
            answer += 1;
            for(Pair p : set){
                System.out.println(p.y + " " + p.x);
            }
            System.out.println();
        }
//        for(int i = 0; i < result.size(); i++){
//            Set<Pair> inArr = result.iterator().next();
//
//
//        }

        System.out.println(answer);
        reader.close();
    }
}
