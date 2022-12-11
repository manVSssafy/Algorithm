package programers;

import java.util.PriorityQueue;

public class µğÆÒ½º°ÔÀÓ_ÂùÈñ {
	public static void main(String[] args) {
		int n = 2;
		int k = 4;
		int[] enemy = { 3, 3, 3, 3 };
		int result = 4;
		int answer = solution(n, k, enemy);

		if (result == answer) {
			System.out.println("correct");
		} else {
			System.out.println("¿À´ä¤Ì¤Ì : " + answer);
		}

	}

	public static int solution(int n, int k, int[] enemy) {
		int sum = 0;
		int len = enemy.length;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < len; i++) {
			int temp = enemy[i];
			pq.add(temp);

			if (pq.size() > k) {
				sum += pq.poll();
			}

			if (sum > n) {
				return i;
			}
		}
		return len;
	}

}
