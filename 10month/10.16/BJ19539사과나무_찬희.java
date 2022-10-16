package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19539사과나무_찬희 {

	private static int odd;
	private static int even;
	private static int total;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("BJ19539사과나무_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		int[] input = new int[N];
		int[] board = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			total += input[i];
			if (input[i] % 2 == 1) {
				odd += 1;
			} else {
				even += 1;
			}
		}
		if (total % 3 == 0 && odd <= total / 3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
