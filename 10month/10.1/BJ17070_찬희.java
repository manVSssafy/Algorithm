package baekjoon.solved;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070_찬희 {
	private static int N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());

		int[][] input = new int[N + 1][N + 1];

		int[][][] dp = new int[N + 1][N + 1][3];// 0 가로 ,1 대각선 , 2 세로
		dp[1][2][0] = 1;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

// 현재의 가로는 왼쪽의 가로 or 대각선
// dp[i][j][0]=dp[i][j-1][0]+dp[i][j-1][1]

// 현재의 대각선은 왼쪽의위 가로 or 대각선 or 세로
// dp[i][j][1]=dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2]

// 현재의 세로는 위의 대각선 or 세로
// dp[i][j][1]=dp[i-1][j][1]+dp[i-1][j][2]

//		display(dp, 0);
//		display(dp, 1);
//		display(dp, 2);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (j != 1) {
					if (input[i][j] != 1) {
						if (i == 1 && j == 2) {
							continue;
						} else {
							dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
//							display(dp, 0);
							if (input[i][j - 1] != 1 && input[i - 1][j] != 1) {
								dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
//								display(dp, 1);
							}
							dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
//							display(dp, 2);
						}
					}

				}
			}
		}
//		display(dp, 0);
//		display(dp, 1);
//		display(dp, 2);
		sb.append(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
		System.out.println(sb);

	}

	private static void display(int[][][] dp, int Dimension) {
		System.out.println("호출");
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(dp[i][j][Dimension] + " ");
			}
			System.out.println();
		}

	}

}
