import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ19539경창 {

    // 규칙
    // (1) 총 합이 3의 배수여야 한다.
    // (2) 각각의 숫자들을 2와 1로 나눈다.
    // - 2 : 1 + 1
    // - 2를 기준으로 나눈다.
    // - 홀수이면 1이 하나 남는다.
    // - 3의 배수 규칙이 2의 갯수 - 1의 갯수가 3의 배수여야 한다.
    //   - ex) 6 : 2 x 2 x 2 (2의 개수 : 3, 1의 개수 : 0)
    //   - 15 : (2의 개수 : 7, 1의 개수 : 1)
    //   - 27 : (2의 개수 : 13, 1의 개수 : 1)

    static int N;
    static int[] arr;
    static int sum;
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        sum = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        answer = true;

        // (1) 3의 배수가 아니라면 False
        if (sum % 3 != 0) {
            answer = false;
        } else {
            // (2) 각 숫자 2의 개수 1의 개수를 구한다.
            int twoCnt = 0;
            int oneCnt = 0;

            for (int i = 0; i < N; i++) {
                oneCnt += arr[i] % 2;
                twoCnt += arr[i] / 2;
            }

            if (twoCnt < oneCnt || (twoCnt - oneCnt) % 3 != 0) answer = false;
        }

        System.out.println(answer ? "YES" : "NO");


        br.close();
    }
}
