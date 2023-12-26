package 미로_탈출;

import java.util.*;

class Solution {

    private static class Node{
        public final int x;
        public final int y;
        public final int time;

        Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }


    int[][] graph;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};


    private boolean isWithInRange(int row, int col){
        return 0 <= row && 0 <= col && row < graph.length && col < graph[0].length;
    }

    private int bfs(int startX, int startY, int endX, int endY){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startX, startY, 0));
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        visited[startY][startX] = true;

        while(queue.size() > 0){
            Node node = queue.poll();

            if(node.x == endX && node.y == endY) return node.time;

            for(int i = 0; i < 4; i++){
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(!isWithInRange(ny, nx)) continue;
                if(graph[ny][nx] == -10) continue;
                if(visited[ny][nx]) continue;
                visited[ny][nx] = true;

                queue.add(new Node(nx, ny, node.time + 1));
            }
        }

        return -1;
    }

    public int solution(String[] maps) {
        graph = new int[maps.length][maps[0].length()];
        int startX = 0, startY = 0;
        int levelX = 0, levelY = 0;
        int endX = 0, endY = 0;
        for(int i = 0; i < maps.length; i++){
            String map = maps[i];
            char[] c = map.toCharArray();

            for(int j = 0; j < map.length(); j++){
                if(c[j] == 'S'){
                    // System.out.println("실행");
                    startX = j;
                    startY = i;
                    // 벽인 경우 -10
                }else if(c[j] == 'X'){
                    graph[i][j] = -10;
                }else if(c[j] == 'E'){
                    endX = j;
                    endY = i;
                }else if(c[j] == 'L'){
                    levelX = j;
                    levelY = i;
                }
            }
        }

        // System.out.println(startX + " " + startY);
        int answer1 = bfs(startX, startY, levelX, levelY);
        int answer2 = bfs(levelX, levelY, endX, endY);
        // System.out.println(answer1 + " " + answer2);
        if(answer1 == -1 || answer2 == -1) return -1;

        return answer1 + answer2;
    }
}