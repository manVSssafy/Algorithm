package 감시;

import java.util.*;
import java.io.*;

public class Solution {


    private static class Node{
        public final int x;
        public final int y;

        public final int cctv;

        public List<Integer> direction = new ArrayList<>();


        public Node(int x, int y, int cctv) {
            this.x = x;
            this.y = y;
            this.cctv = cctv;
        }

        public final void setDirection(int num){
            direction.add(num);
        }

        public final int getDirectionSize(){
            return direction.size();
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int N, M;
    private static int[][] arr;
    private static int zeroCnt;
    private static int result;
    private static List<Node> alList = new ArrayList<>();

    private static boolean isWithInRange(int x, int y){
        if(0 <= x && 0 <= y && x < M && y < N) return true;
        return false;
    }
    private static void obtainMinMum(Node[] nodes, boolean[][] visited){
        int count = 0;
        for(Node node : nodes){
            for(int i = 0 ; i < node.getDirectionSize(); i++){
                int loc = node.direction.get(i);
                int nx = dx[loc] + node.x;
                int ny = dy[loc] + node.y;

                while(isWithInRange(nx, ny)){
                    if(arr[ny][nx] == 0){
                        if(!visited[ny][nx]){
//                            System.out.println("ny : "  + ny + " nx : " + nx);
                            visited[ny][nx] = true;
                            count++;
                        }
                    }else if(arr[ny][nx] == 6) break;

                    nx += dx[loc];
                    ny += dy[loc];
                }
            }
        }

//        System.out.println("zeroCnt : " + zeroCnt + " count : " + count);
        result = Math.min(result, zeroCnt - count);
    }

    private static void backtracking(int index, int alSize, Node[] nodes){
        if(index == alSize){
            obtainMinMum(nodes, new boolean[N][M]);
        }else{
            Node curNode = alList.get(index);
            for(int i = 0; i < 4; i++){
                Node node = new Node(curNode.x, curNode.y, curNode.cctv);
                if(node.cctv == 1){
                    node.setDirection(i);
                    nodes[index] = node;
                    backtracking(index + 1, alSize, nodes);
                }else if(node.cctv == 2){
                    if(i >= 2) break;
                    node.setDirection(i);
                    node.setDirection(i + 2);
                    nodes[index] = node;
                    backtracking(index + 1, alSize, nodes);
                }else if(node.cctv == 3){
                    node.setDirection(i);
                    node.setDirection((i + 1) % 4);
                    nodes[index] = node;
                    backtracking(index + 1, alSize, nodes);
                }else if(node.cctv == 4){
                    node.setDirection(i);
                    node.setDirection((i + 2) % 4);
                    node.setDirection((i + 3) % 4);
                    nodes[index] = node;
                    backtracking(index + 1, alSize, nodes);
                }
                else if(node.cctv == 5){
                    if(i >= 1) break;
                    node.setDirection(i);
                    node.setDirection(i + 1);
                    node.setDirection(i + 2);
                    node.setDirection(i + 3);
                    nodes[index] = node;
                    backtracking(index + 1, alSize, nodes);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());

                if(1 <= arr[i][j] && arr[i][j] <= 5){
                    alList.add(new Node(j, i, arr[i][j]));
                }else if(arr[i][j] == 0) zeroCnt += 1;
            }
        }
        result = Integer.MAX_VALUE;
        // index, al.Size,
        backtracking(0, alList.size(), new Node[alList.size()]);

        System.out.println(result);
        reader.close();
    }
}
