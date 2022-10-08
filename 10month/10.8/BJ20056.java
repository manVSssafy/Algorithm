

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Fireball {
    int r; // 행
    int c; // 열
    int m; // 질량
    int s; // 속력
    int d; // 방향

    public Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class BJ20056 {

    static int N, M, K;
    static int[][] direct = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; // 방향
    static ArrayList<Fireball> list = new ArrayList<>();
    static ArrayList<Fireball>[][] map;

    static void moveFireball() {

        // fireball 이동시키기, 0, 1, 2, 3, 4, 5, 6, 7 방향으로
        for (Fireball fireball : list) {
            int nr = (fireball.r + N + (direct[fireball.d][0] * fireball.s) % N) % N;
            int nc = (fireball.c + N + (direct[fireball.d][1] * fireball.s) % N) % N;

            if (nr == 0) nr = N;
            if (nc == 0) nc = N;

            fireball.r = nr;
            fireball.c = nc;

            map[nr][nc].add(fireball);
        }
    }

    static void checkFireball() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 1이라면 아무런 변화가 없다.
                if (map[i][j].size() == 1) map[i][j].clear();

                // 2보다 작다면 continue 처리한다.
                if (map[i][j].size() < 2) continue;

                // 짝수, 홀수 판단
                boolean even = (map[i][j].get(0).d % 2 == 0 ? true : false);
                boolean odd = (map[i][j].get(0).d % 2 != 0 ? true : false);

                // 질량 총합, 속력 총합
                int massSum = 0, speedSum = 0;

                // 이차원에서 해당위치의 fireball을 꺼내서 제거한다.
                // 현재 도착한 파이어볼 개수 만큼 돌리게 된다. (현재 위치 인덱스)
                for (Fireball fireball : map[i][j]) {
                    massSum += fireball.m;
                    speedSum += fireball.s;

                    // 이전 홀 또는 짝수인데, 다음 또한 홀 또는 짝수라면
                    even = (even & (fireball.d % 2 == 0) ? true : false);
                    odd = (odd & (fireball.d % 2 != 0) ? true : false);

                    // 이전 위치 제거
                    list.remove(fireball);
                }

                // 질량은 합쳐진 파이어볼 질량의 합 / 5
                // 속력은 합쳐진 파이어볼 속력의 합 / 합쳐진 파이어볼 개수
                massSum /= 5;
                speedSum /= map[i][j].size();

                map[i][j].clear(); // 다 꺼낸 후, map의 현재 i, j를 초기화한다.

                // 4. 질량이 0인 파이어볼은 소멸되어 없어진다.
                if (massSum == 0) continue;
                else {
                    if (even || odd) {
                        // 모두 짝수이거나 홀수일 때 : 0, 2, 4, 6
                        for (int idx = 0; idx < 8; idx += 2) {
                            list.add(new Fireball(i, j, massSum, speedSum, idx));
                        }
                    } else {
                        // 모두 짝수이거나 홀수가 아닐 때 : 1, 3, 5, 7
                        for (int idx = 1; idx < 8; idx += 2) {
                            list.add(new Fireball(i, j, massSum, speedSum, idx));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.add(new Fireball(r, c, m, s, d));
        }

        map = new ArrayList[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        // k번 돌릴 때,
        for (int i = 0; i < K; i++) {
            // 이동하는 함수
            moveFireball();

            // check 하는 함수
            checkFireball();
        }

        int answer = 0;

        for (Fireball fireball : list) {
            answer += fireball.m;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.println(sb);

        br.close();
    }
}
