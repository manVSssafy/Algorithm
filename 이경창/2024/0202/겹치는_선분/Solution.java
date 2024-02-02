package 겹치는_선분;

// 학습할 내용
// https://byeo.tistory.com/entry/%EC%8A%A4%EC%9C%84%ED%95%91-Sweeping
import java.util.*;
import java.io.*;

public class Solution {

    public static class Node{
        public final int first;
        public final int len;

        Node(int first, int len){
            this.first = first;
            this.len = len;
        }
    }

    private static int N;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        arr = new int[N][2];

        for(int i = 0; i < N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int first = Integer.parseInt(tokenizer.nextToken());
            int second = Integer.parseInt(tokenizer.nextToken());
            arr[i][0] = first;
            arr[i][1] = second - first; // 길이
        }

        Arrays.sort(arr, (a1, a2) -> {
            if(a1[0] == a2[0]) return a1[1] - a2[1];
            else return a1[0] - a2[0];
        });

        Stack<Node> stack = new Stack<>();
        int answer = 0;

        for(int i = 0; i < N; i++){
            int first = arr[i][0];
            int len = arr[i][1];

            while(stack.size() > 0){
                Node node = stack.peek();

                // node.second >= first && node.second <= second
                if(node.len + node.first > first) break;

                // 아닌 경우 pop
                stack.pop();
            }

            stack.push(new Node(first, len));
            answer = Math.max(answer, stack.size());
            System.out.print(first + " " + len + "  stack size : ");
            System.out.println(stack.size() + " 결과 : " + answer);
        }

        System.out.println(answer);

        reader.close();
    }
}
