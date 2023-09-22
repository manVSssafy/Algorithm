package 공유기_설치;

import java.io.*;
import java.util.*;


public class Solution2 {

    private static int N;
    private static int C;
    private static long[] arr;

    private static boolean Check(long mid){
        long modem = arr[0];
        int count = 1;
//        System.out.println("시작값 : " + modem);
        for(int i = 1; i < arr.length; i++){
//            System.out.println("left : " + (modem + mid) + " right : " + arr[i]);
            if(modem + mid <= arr[i]){
                modem = arr[i];
                count++;
            }
        }

//        System.out.println("mid : " + mid + " count : " + count);
        return count >= C;
    }
    private static long binarySearch(){
        long left = 0;
        long right = arr[arr.length - 1] - arr[0] + 1;

        while(left + 1 < right){
            long mid = (left + right) / 2;

            if(Check(mid)){
                left = mid;
            }else{
                right = mid;
            }
        }
        return left;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        arr = new long[N];
        for(int i = 0; i < N; i++){
            arr[i] = Long.parseLong(reader.readLine());
        }

        Arrays.sort(arr);
        System.out.println(binarySearch());
        reader.close();
    }
}
