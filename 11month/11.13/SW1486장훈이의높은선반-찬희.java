import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
점원들을 '일부 사용(부분집합을 사용)'하여 선반보다 높은 탑을 만들려고 한다.
부분집합을 사용해 문제를 풀어보도록 하자

조건 :
    1. 선반의 높이보다 클것
    2. 그 중에서 제일 작을 것

모두 다 선택한 CASE부터 한명씩 FALSE처리하여 선택하지 않으면서 부분집합을 만든다

    OOOOO - > 모두 선택한 경우
    
    OOOOX - > 맨 끝사람을 선택 안한경우
    OOOXO
    OOOXX
    OOXOO
      ~
    XXXXO
    XXXXX - > 모두 선택 안 한 경우
 */


public class SW1486장훈이의높은선반 {
    private static int answer;
    private static int[] worker;
    private static boolean[] selected;
    private static int N;
    private static int B;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SW1486장훈이의높은선반input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int TESTCASE = Integer.parseInt(br.readLine());

        for (int TC = 1; TC <= TESTCASE; TC++) {
            //입력부
            st = new StringTokenizer(br.readLine());
            //점원의 수 입력
            N = Integer.parseInt(st.nextToken());
            //탑의 높이 입력
            B = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            //매 테스트케이스에 사용할 디폴트
            //가장 큰값으로 시작하여 계산값이 낮아질수록 변경
            answer = Integer.MAX_VALUE;
            //점원의 키를 넣어둘 배열
            worker = new int[N];
            //점원이 선택되었는지 확인하기 위한 배열
            selected = new boolean[N];

            //점원들의 키 입력
            for (int i = 0; i < N; i++) {
                worker[i] = Integer.parseInt(st.nextToken());
            }
            //입력끝

            //부분집합
            powerset(0);

            //결과값은 탑의 높이와 B의 차이
            sb.append("#" + TC + " " + (answer - B) + "\n");
        }
        System.out.println(sb);
    }

    private static void powerset(int idx) {
        //모든점원을 확인했는지 조건을 확인
        if (idx == N) {
            int tempsum = 0;

            //그 점원들 중에 선택받은 점원들의 키를 더한다
            for (int i = 0; i < N; i++) {
                if (selected[i]) tempsum += worker[i];
            }

            //이전의 정답보다 작고, 탑의 높이보다 크면 정답을 교체
            if (tempsum < answer && B <= tempsum) {
                answer = tempsum;
            }
            return;
        }

        //선택하고 다음사람으로 넘어간다
        selected[idx] = true;
        powerset(idx + 1);

        //선택하지 않고 다음사람으로 넘어간다
        selected[idx] = false;
        powerset(idx + 1);
    }
}
