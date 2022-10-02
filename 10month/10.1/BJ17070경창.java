package baekjoon.solved;


import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//class Node17070 {
//	int x;
//	int y;
//	int dir;
//
//	public Node17070(int x, int y, int dir) {
//		this.x = x;
//		this.y = y;
//		this.dir = dir;
//	}
//}
//
//public class BJ17070경창 {
//

//
//	// (1) 가로 (0, 1), (1, 1) 이동
//	// (2) 세로 (1, 0), (1, 1) 이동
//	// (3) 대각선 (0, 1), (1, 0), (1, 1) 이동
//	// - 예외 : 벽인 경우 패스
//
//
//	static void bfs() {
//		Queue<Node17070> queue = new ArrayDeque<>();
//		queue.add(new Node17070(0, 1, 0));
//
//		// 처음 (1, 1) -> (1, 2)
//		visited[0][1][0] = true;
//
//		while (queue.size() > 0) {
//			Node17070 node = queue.poll();
//
//
//			if (node.x == (N - 1) && node.y == (N - 1) && graph[node.x][node.y] == 0) {
//				if(node.dir == 2 && (graph[node.x - 1][node.y] == 1 || graph[node.x][node.y - 1] == 1 || graph[node.x][node.y] == 1)){
//					continue;
//				}else{
//					answer += 1;
//					continue;
//				}
//			}
//
//			if (0 <= node.x && node.x < N && 0 <= node.y && node.y < N) {
//
//				// 2인경우, 왼쪽 오른쪽 현재가 1이라면 패스
//				if(node.dir == 2 && (graph[node.x - 1][node.y] == 1 || graph[node.x][node.y - 1] == 1 || graph[node.x][node.y] == 1)){
//					continue;
//				}
//
//				if (graph[node.x][node.y] == 0) {
//
//					if (node.dir == 0) {
//						// (1) 가로인 경우 : 0, 1
//						queue.add(new Node17070(node.x + direction[0][0], node.y + direction[0][1], 0));
//						// 대각선인 경우
//						queue.add(new Node17070(node.x + direction[2][0], node.y + direction[2][1], 2));
//
//					} else if (node.dir == 1) {
//						// (2) 세로인 경우 : 2, 1
//						queue.add(new Node17070(node.x + direction[2][0], node.y + direction[2][1], 2));
//						queue.add(new Node17070(node.x + direction[1][0], node.y + direction[1][1], 1));
//
//					} else if (node.dir == 2) {
//						// (3) 대각선 : 0, 1, 2
//						queue.add(new Node17070(node.x + direction[0][0], node.y + direction[0][1], 0));
//						queue.add(new Node17070(node.x + direction[1][0], node.y + direction[1][1], 1));
//						queue.add(new Node17070(node.x + direction[2][0], node.y + direction[2][1], 2));
//					}
//				}
//			}
//		}
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//		graph = new int[N][N];
//		visited = new boolean[N][N][3];
//		StringTokenizer st;
//		for (int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 0; j < N; j++) {
//				graph[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//
//		if(graph[N-1][N-1] == 1){
//			System.out.println(0);
//		}else{
//			bfs();
//			System.out.println(answer);
//		}
//
//		br.close();
//	}
//}


public class BJ17070경창 {


    static int N, answer;
    static int[][] graph;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {

                if (i == 0 && j == 1) continue;

                // i, j-1의 대각선, 가로
                if (graph[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j - 1][2] + dp[i][j - 1][0];

                if (i == 0) continue;
                // i-1, j-1의 대각선, 세로, 가로
                if (graph[i - 1][j] != 1 && graph[i][j - 1] != 1 && graph[i - 1][j - 1] != 1) {
                    dp[i][j][2] = dp[i - 1][j - 1][1] + dp[i - 1][j - 1][0] + dp[i - 1][j - 1][2];
                }

                // i-1, j-1 번째의 대각선, 세로
                dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
            }
        }

        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }

}
