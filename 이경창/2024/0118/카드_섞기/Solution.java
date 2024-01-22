package 카드_섞기;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Solution {

    private static int N;
    private static char[] pArr;
    private static int[] sArr;
    private static int[] answerArr;
    private static String answerStr;

    private static int gameStation(){

        int time = 0;

        // game 시작
        while(true){

            StringBuilder curData = new StringBuilder().append(pArr);

//            System.out.println(curData);

            // (1) 게임 결과와 같다면 out
            if(answerStr.equals(curData.toString())) break;

            time++;

            // (2) 게임 결과가 아니라면
            char[] testCase = pArr.clone();

            for(int i = 0; i < pArr.length; i++){
                int nextIdx = sArr[i];
                pArr[nextIdx] = testCase[i];
            }
            
        }

        return time;
    }
    public static void main(String[] args)  throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());


        pArr = new char[tokenizer.countTokens()];
        answerArr = new int[tokenizer.countTokens()];

        boolean isitStartEnd = false;

        for(int i = 0; i < pArr.length; i++){
            pArr[i] = tokenizer.nextToken().charAt(0);
            answerArr[i] = i % 3;
        }

        answerStr = Arrays.stream(answerArr).mapToObj(String::valueOf).collect(Collectors.joining());

        tokenizer = new StringTokenizer(reader.readLine());
        sArr = new int[tokenizer.countTokens()];

        for(int i = 0; i < sArr.length; i++){
            sArr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for(int i = 0; i < sArr.length; i++){
            // 만약 start와 목표 값이 다른데, 위치가 계속 유지되는 경우
            if(pArr[i] - '0' != answerArr[i] && sArr[i] == i) isitStartEnd = true;
        }

        int answer = (isitStartEnd ? -1 : gameStation());

        System.out.println(answer);

        reader.close();
    }
}
