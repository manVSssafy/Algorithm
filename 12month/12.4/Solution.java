import java.util.*;

class Node{
    int x;
    int y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {

    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};


    // bfs 알고리즘
     int bfs(int x, int y, int m, int n, int[][] picture){
         int cnt = 1;

         Queue<Node> queue = new ArrayDeque<>();
         queue.add(new Node(x, y));
         visited[x][y] = true;

         // System.out.println("방문한 곳");
         while(queue.size() > 0){
             Node curN = queue.poll();
             // queue.pop();

             for(int i =0; i< 4; i++){
                 int nx = dx[i] + curN.x;
                 int ny = dy[i] + curN.y;

                 // 그림공간 안일 때
                 // 방문한 곳이 아니면서, 상하좌우중 찾고자 하는 값이 있다면
                 // 0또한 아니다.
                 if(0<= nx && nx < m && 0 <= ny && ny < n){
                     if(picture[nx][ny] != 0 && picture[x][y] == picture[nx][ny] && !visited[nx][ny]){
                         // System.out.println("nx, ny : " + nx + " " + ny);
                         visited[nx][ny] = true;
                         cnt += 1;
                         queue.add(new Node(nx, ny));
                     }
                 }
             }
         }
         return cnt;
     }


    // dfs 알고리즘
    int cntMax = 0;
    int cx, cy;
    int dfs(int curx, int cury, int m, int n, int[][] picture, int cnt){

        for(int i =0; i< 4; i++){
            int nx = dx[i] + curx;
            int ny = dy[i] + cury;
            if(0<= nx && nx < m && 0 <= ny && ny < n){
                if(picture[nx][ny] != 0 && picture[cx][cy] == picture[nx][ny] && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    cntMax += 1;
                    dfs(nx, ny, m, n, picture, cnt);
                }
            }
        }

        return cntMax;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        visited = new boolean[m][n];
        int space = 0;
        int maxSize = 0;

        for(int i =0 ;i<m; i++){
            for(int j =0; j< n; j++){
                // 0이 아니면 방문한 곳이 아닐 때, 체크한다.
                if(picture[i][j] != 0 && !visited[i][j]){
                    space += 1;
                    cntMax = 0;
                    // maxSize = Math.max(maxSize, bfs(i, j, m, n, picture));
                    cx = i;
                    cy = j;
                    maxSize = Math.max(maxSize, dfs(i, j, m, n, picture, 0));
                }
            }
        }

        answer[0] = space;
        answer[1] = maxSize;
        return answer;
    }
}