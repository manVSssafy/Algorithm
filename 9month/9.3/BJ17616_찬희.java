package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ17616_찬희 {
	static StringBuilder sb = new StringBuilder();
	private static String[] split;
	private static int N;
	private static int M;
	private static int X;
	private static boolean[] isVisited;
	static List<Integer>[][] list = new ArrayList[100001][2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]); // 학생의 수
		M = Integer.parseInt(split[1]); // 질문횟수
		X = Integer.parseInt(split[2]); // 이 값을 번호로 가지는 학생의 등수를 최대값 최소값을 뽑는다.

		isVisited = new boolean[N + 1];

		// 인접리스트
		for (int i = 1; i <= N; i++) {
			list[i][0] = new ArrayList<>();//나보다 못하는사람넣기
			list[i][1] = new ArrayList<>();//잘하는사람
		}	
		
		
		for (int tc = 0; tc < M; tc++) {
			split = in.readLine().split(" ");
			int first = Integer.parseInt(split[0]); // 잘하는쪽
			int second = Integer.parseInt(split[1]); // 못하는쪽

			list[second][0].add(first); //잘하는쪽을보자
			list[first][1].add(second); //못하는쪽
		}
		
		// 최고등수
		
		int max = dfs(X, 0);
		// 최소등수
		int min = N - dfs(X, 1) + 1;

		sb.append(max + " " + min);
		System.out.println(sb);

	}

	private static int dfs(int start, int dir) {
		//start 출발하는 곳
		//dir 나보다 잘하는쪽을 찾을지 못하는 쪽을 찾을지
		
		int temp = 1;
		isVisited[start] = true;
		// 유도부분

		for (int i = 0; i < list[start][dir].size(); i++) {

			if (!isVisited[list[start][dir].get(i)]) {
				temp += dfs(list[start][dir].get(i), dir);
			}
		}

		return temp;
	}

}
