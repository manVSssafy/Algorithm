package 탑_보기;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Solution {

    private static int N;
    private static int[] arr;
    private static int[] cnt;
    private static int[] nearData;
//    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        arr = new int[N];
        cnt = new int[N];
        nearData = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
            nearData[i] = -1000000;
        }

        Stack<Integer> stack = new Stack<>();


        // 왼쪽 방향
        for(int i = 0; i < N; i++){
            while(stack.size() > 0 && arr[stack.peek()] <= arr[i]){
                stack.pop();
            }

            cnt[i] = stack.size();
            if(cnt[i] > 0) nearData[i] = stack.peek();

            stack.push(i);

        }

        stack = new Stack<>();

//        System.out.println("오른쪽 탐색");
        for(int i = N - 1; i >= 0; i--){
//            System.out.println("i 번째 : " + i);

            while(stack.size() > 0 && arr[stack.peek()] <= arr[i]){
                stack.pop();
            }

            int curSize = stack.size();
            cnt[i] += curSize;
            if(curSize > 0 && stack.peek() - i < i - nearData[i]){
//                System.out.println("i : " + i + " nearData : " + (i - nearData[i]));
                nearData[i] = stack.peek();
            }
            // -1인 경우 큰 값이 없다.

            stack.push(i);
//            System.out.println();
        }


        for(int i = 0; i < N ; i++){
            System.out.print(cnt[i] + " ");
            if(cnt[i] > 0) System.out.print(nearData[i] + 1);
            System.out.println();
//            else System.out.println(nodes[i].cnt + " " + (nodes[i].index + 1));
        }

        reader.close();
    }
}
