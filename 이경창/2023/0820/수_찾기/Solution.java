package 수_찾기;

import java.io.*;
import java.util.*;

public class Solution {

    private static int N, M;
    private static long[] arr1, arr2;

    private static boolean binarySearch(long findData){
        int left = 0;
        int right = arr1.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
//            System.out.println("left : " + left + " mid : "  + mid + " right : " + right);
            if(arr1[mid] < findData){
                left = mid + 1;
            }else if(arr1[mid] > findData){
                right = mid - 1;
            }else{
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        List<Long> list = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) list.add(Long.parseLong(tokenizer.nextToken()));
        arr1 = list.stream().mapToLong(Long::valueOf).toArray();

        M = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        List<Long> list2 = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) list2.add(Long.parseLong(tokenizer.nextToken()));
        arr2 = list2.stream().mapToLong(Long::valueOf).toArray();

        Arrays.sort(arr1);
//        Arrays.sort(arr2);
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(Arrays.toString(arr2));
        for(long inArr2 : arr2){
            if(binarySearch(inArr2)) builder.append(1).append("\n");
            else builder.append(0).append("\n");
        }

        System.out.print(builder);

        reader.close();
    }
}
