package 전력망을_둘로_나누기;

import java.util.*;

class Solution {

    private ArrayList<Integer> list[];
    private boolean[][] visited;

    private int bfs(int index){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        int count = 0;

        while(queue.size() > 0){
            int curIdx = queue.poll();

            for(int i = 0; i < list[curIdx].size(); i++){
                // System.out.println("실행");
                int nextIdx = list[curIdx].get(i);
                if(visited[curIdx][nextIdx] && visited[nextIdx][curIdx]) continue;
                visited[curIdx][nextIdx] = true;
                visited[nextIdx][curIdx] = true;
                count++;
                queue.add(nextIdx);
            }
        }

        return count;
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n + 1];
        visited = new boolean[n + 1][n + 1];

        for(int i = 0; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < wires.length; i++){
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }

        for(int startIdx = 1; startIdx <= n; startIdx++){
            if(list[startIdx].size() == 0) continue;
            for(int j = 0; j < list[startIdx].size(); j++){
                int nextIdx = list[startIdx].get(j);
                // 방문처리 초기화
                for(int i = 0; i < n + 1; i++){
                    Arrays.fill(visited[i], false);
                }
                visited[startIdx][nextIdx] = true;
                visited[nextIdx][startIdx] = true;

                // 1번 bfs();
                int count = bfs(startIdx);
                // System.out.print("왼쪽 : " + count + " , ");
                // 2번 bfs();
                int count2 = bfs(nextIdx);
                // System.out.println("오른쪽 : " + count2);

                // System.out.println("횟수 : " + (count - count2));
                answer = Math.min(answer, Math.abs(count - count2));
            }

            System.out.println();

        }

        return answer;
    }
}