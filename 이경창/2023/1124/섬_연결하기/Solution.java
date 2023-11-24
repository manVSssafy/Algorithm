package 섬_연결하기;

import java.util.*;

class Solution {

    private static class Edge implements Comparable<Edge> {
        public final int isLand;
        public final int cost;

        Edge(int isLand, int cost){
            this.isLand = isLand;
            this.cost = cost;
        }

        public int compareTo(Edge n){
            return this.cost - n.cost;
        }
    }

    private static List<Edge>[] graph;

    private static int prim(int n){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        pq.offer(new Edge(0, 0));

        int total = 0;

        while(pq.size() > 0){
            Edge edge = pq.poll();

            if(visited[edge.isLand]) continue;
            visited[edge.isLand] = true;
            total += edge.cost;

            for(Edge inEdge : graph[edge.isLand]){
                if(visited[inEdge.isLand]) continue;
                pq.offer(inEdge);
            }
        }

        return total;
    }

    public static int solution(int n, int[][] costs) {
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        // 점선 저장
        for(int i = 0; i < costs.length; i++){
            int a = costs[i][0];
            int b = costs[i][1];
            int cost = costs[i][2];

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        return prim(n);
    }
    public static void main(String[] args){
        int n = 4;
        int[][] arr = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};

        solution(n, arr);
    }
}
