package 맥주_축제;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, K;
    private static long M;
    private static long[][] arr;

    // 공부해야할 것, 투 포인터, 2차원 배열 정렬

    private static long twoPointerFun(){
        int start = 0;
        int end = 0;
        long sumV = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((c1, c2) -> c2.compareTo(c1));
        long sumC = 0;
        long result = Long.MAX_VALUE;
        while(start < arr.length){
            if(end < start + N){
                end += 1;
            }else{

                if(start >= N) {

                    sumV -= arr[start - N][0];
                    PriorityQueue<Long> nq = new PriorityQueue();

//                    System.out.println("sumV : " + (sumV + arr[start - N][0]));
                    // pq에서 제거
                    while (sumV + arr[start - N][0]  >= M && pq.size() > 0) {
                        long findData = pq.poll();
                        if (findData == arr[start - N][1]) {
//                            System.out.println("findData : " + findData + " pq size : " + pq.size());
                            pq.addAll(nq);
                            break;
                        } else {
                            nq.add(findData);
                        }
                    }

                }

                sumV += arr[start][0];
                pq.add(arr[start][1]);
//                System.out.println("start: " + start + " , sumV : " + sumV);
                if(sumV >= M && start >= N){
                    result = Math.min(result, pq.peek());
                }
                start += 1;
            }
        }
        if(result == Long.MAX_VALUE) return -1;
        else return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Long.parseLong(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        arr = new long[K][2];

        for(int i = 0; i < K; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            long n1 = Long.parseLong(tokenizer.nextToken());
            long n2 = Long.parseLong(tokenizer.nextToken());

            arr[i] = new long[]{n1, n2};
        }

        Arrays.sort(arr, (c1, c2)-> {
            if(c1[0] < c2[0]) return -1;
            else if(c1[0] > c2[0]) return 1;
            else{
                if(c1[1] < c2[1]) return -1;
                else if(c1[1] > c2[1]) return 1;
                else return 0;
            }
        });

//        System.out.println(Arrays.deepToString(arr));
        System.out.println(twoPointerFun());


        reader.close();
    }
}
