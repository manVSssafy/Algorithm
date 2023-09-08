package 물병;

import java.util.*;
import java.io.*;

public class Solution4 {

    private static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        String str = Integer.toString(N, 2);
        char[] c = str.toCharArray();
        int oneCount = 0;
        int startIndex = 0;
        int result = (int)Math.pow(2, c.length);

        for(int i = 0; i < c.length ; i++){
            if(c[i] == '1') oneCount++;

            if(oneCount == K && c[i] == '1'){
                startIndex = i;
//                System.out.println("시작" + startIndex);

//                if(startIndex < c.length - 1){
//                    for(int j = i + 1; j <= c.length - 1; j++){
//                        if(c[j] == '0'){
//                            System.out.println("j : " + j + " c[j] : " + c[j]);
//                            result = (int)Math.pow(2, j);
//                            break;
//                        }
//                    }
//                }
            }
        }
//        System.out.println(str);
        if(oneCount > K){
            for(int j = startIndex - 1; j >= 0; j--){
                if(c[j] == '0'){
//                    System.out.println("j 위치 : " + j);
                    result = (int)Math.pow(2, c.length - j - 1);
                    System.out.println("반갑습니다. 하잉하이ㅣ");
                    break;
                }
            }
        }else if(oneCount == K){
            System.out.println(0);
            return;
        }

        if(result >= N) startIndex = 0;
//        System.out.println("result : " + result + " startIndex : " + startIndex);
        for(int i =  startIndex; i < c.length; i++){
            if(c[i] == '1'){
//                System.out.println("i : " + i);
                result -= (int)Math.pow(2, c.length - i - 1);
            }
        }

        System.out.println(result);

        reader.close();
    }
}
