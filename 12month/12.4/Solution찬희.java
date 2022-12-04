package programers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution찬희 {

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int areaMax = 0;
	private static int col = 0;
	private static int row = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
	}

	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		int[] answer = new int[2];
		col = m;
		row = n;
		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 배경이 아니면서
				if (picture[i][j] != 0) {
					// 방문한적이 없으면
					if (!visited[i][j]) {
						numberOfArea++;
						areaMax = 0;
						// dfs(i, j, picture, visited);
						// System.out.println(i + ", " + j);
						bfs(i, j, picture, visited);
						if (areaMax > maxSizeOfOneArea) {
							maxSizeOfOneArea = areaMax;
						}
					}
				}
			}

		}

		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;

		return answer;
	}

	private void bfs(int i, int j, int[][] picture, boolean[][] visited) {
		ArrayDeque<Position> deq = new ArrayDeque<>();
		deq.offer(new Position(i, j));
		areaMax++;
		visited[i][j] = true;

		while (!deq.isEmpty()) {
			Position now = deq.poll();
			for (int dir = 0; dir < 4; dir++) {
				int TempX = now.i + dx[dir];
				int TempY = now.j + dy[dir];

				if ((TempX < 0 || TempX >= col) || (TempY < 0 || TempY >= row)) {
					continue;
				}

				if (picture[i][j] == picture[TempX][TempY] && !visited[TempX][TempY]) {
					visited[TempX][TempY] = true;
					deq.offer(new Position(TempX, TempY));
					areaMax++;
				}

			}
		}
	}

	private void dfs(int i, int j, int[][] picture, boolean[][] visited) {
		if (visited[i][j]) {
			return;
		}
		visited[i][j] = true;
		areaMax++;
		for (int dir = 0; dir < 4; dir++) {
			int TempX = i + dx[dir];
			int TempY = j + dy[dir];
			// 범위를 벗어나면
			if ((TempX < 0 || TempX >= col) || (TempY < 0 || TempY >= row)) {
				continue;
			}
			if (picture[i][j] == picture[TempX][TempY] && !visited[TempX][TempY]) {
				dfs(TempX, TempY, picture, visited);
			}

		}
	}

	class Position {
		int i;
		int j;

		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}
}
