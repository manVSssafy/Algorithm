package 빌런_호석;

import java.io.*;
import java.util.*;

public class Solution {

    private static int[][] display;
    private static int[][] reversal;
    private static int N, K, P, X;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        P = Integer.parseInt(tokenizer.nextToken());
        X = Integer.parseInt(tokenizer.nextToken());

        display = new int[10][10];
        reversal = new int[10][10];

        display[0] = new int[]{0, 1, 2, 3, 4, 5};
        display[1] = new int[]{1, 2};
        display[2] = new int[]{0, 1, 3, 4, 6};
        display[3] = new int[]{0, 1, 2, 3, 6};
        display[4] = new int[]{1, 2, 5, 6};
        display[5] = new int[]{0, 2, 3, 5, 6};
        display[6] = new int[]{0, 2, 3, 4, 5, 6};
        display[7] = new int[]{0, 1, 2};
        display[8] = new int[]{0, 1, 2, 3, 4, 5, 6};
        display[9] = new int[]{0, 1, 2, 3, 5, 6};

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == j) continue;
                Set<Integer> set = new HashSet<>();
                for(int inDisplay : display[i]){
                    set.add(inDisplay);
                }
                for(int inDisplay : display[j]){
                    set.add(inDisplay);
                }

                reversal[i][j] += (set.size() - display[i].length);
                reversal[i][j] += (set.size() - display[j].length);
            }
        }

        // X를 문자열로 변경하고 N자리에 맞추기
        StringBuilder builderX = new StringBuilder(String.valueOf(X));
//        System.out.println("시작값 : " + builderX.toString());
        if(builderX.toString().length() < K){
            StringBuilder addZeroBuilder = new StringBuilder();
            int len = K - builderX.toString().length();
            while(len-- > 0){
                addZeroBuilder.append(0);
            }
            builderX = new StringBuilder(addZeroBuilder.append(builderX));
        }

        int answer = 0;
        for(int number = 1; number <= N; number++){

            if(number == X) continue;

            int count = 0;

            // 현재 number를 N자리에 맞추기
            StringBuilder builder = new StringBuilder(String.valueOf(number));
            if(builder.toString().length() < K){
                StringBuilder addZeroBuilder2 = new StringBuilder();
                int len2 = K - builder.toString().length();
                while(len2-- > 0){
                    addZeroBuilder2.append("0");
                }
                builder = new StringBuilder(addZeroBuilder2.append(builder));
            }

            // 이제 a -> b 숫자로 변경 가능한지 확인한다.
//            System.out.println(builderX.toString() + " " + builder.toString());
            char[] c = builderX.toString().toCharArray();
            char[] c2 = builder.toString().toCharArray();

            for(int idx = 0; idx < K; idx++){
                int left = c[idx] - '0';
                int right = c2[idx] - '0';
//                System.out.println("실행 " + left + " " + right);
                count += reversal[left][right];
            }

//            System.out.println(number + " " + "count : " + count);
            if(count <= P){
                answer += 1;
//                System.out.println(number + " 는 가능합니다. " +  answer);
            }
//            else System.out.println(number + " 는 불가능합니다. " +  answer);
        }

        System.out.println(answer);

        reader.close();
    }
}
