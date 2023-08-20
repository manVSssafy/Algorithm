package 좌표_압축;

import java.io.*;
import java.util.*;

public class Solution3 {

    private static int N;
    private static long[] inputArr;
    private static long[] arr;


    private static int binarySearch(long findData){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(findData < arr[mid]){
                right = mid - 1;
            }else if(findData > arr[mid]){
                left = mid + 1;
            }else{
                return mid;
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        Set<Long> set = new HashSet<>();
        inputArr = new long[N];

        for(int i = 0; i < N; i++){
            long input = Long.parseLong(tokenizer.nextToken());
            set.add(input);
            inputArr[i] = input;
        }
        arr = set.stream().mapToLong(Long::longValue).toArray();

        Arrays.sort(arr);

        for(int i = 0; i < N; i++){
            int findNumber = binarySearch(inputArr[i]);
            builder.append(findNumber).append(" ");
        }

        System.out.println(builder.toString());
        reader.close();
    }
}
