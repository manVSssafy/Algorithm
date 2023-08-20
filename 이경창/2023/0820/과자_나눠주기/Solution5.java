package 과자_나눠주기;

import java.io.*;
import java.util.*;

public class Solution5 {

    private static int N, M;
    private static long[] snacks;
    private static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        List<Long> list = new ArrayList<>();

        StringTokenizer tokenizezr = new StringTokenizer(reader.readLine());

        M = Integer.parseInt(tokenizezr.nextToken());
        N = Integer.parseInt(tokenizezr.nextToken());

        tokenizezr = new StringTokenizer(reader.readLine());

        snacks = new long[N];
        for(int i = 0; i < N; i++){
            snacks[i] = Long.parseLong(tokenizezr.nextToken());
        }

        Arrays.sort(snacks);

        if(M <= N){
            answer = snacks[N -M];
        }else{
            // 조카수가 더 많은 경우
            // java 올림 : ceil, 내림 : floor, 반올림 : round
            int share = M / N;
            int maxShare = (int)Math.ceil((double)M / N);

            long number1 = snacks[0] / share;
            long number2 = snacks[snacks.length - 1] / maxShare;

            answer = Math.min(number1, number2);
        }

        System.out.println(answer);


        reader.close();
    }
}
