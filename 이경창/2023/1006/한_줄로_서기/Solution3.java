package 한_줄로_서기;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution3 {

    private static int N;
    private static int[] arr, result;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        arr = new int[N];
        result = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for(int i =0 ; i < N; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int index = 1;
        result[arr[0]] = index++;
        for(int i = 1; i < N; i++){
            int count = 0;
            for(int j = 0; j < N; j++){
                if(result[j] == 0 && count == arr[i]){
                    result[j] = index++;
                    break;
                }
                if(result[j] == 0) count++;

            }
        }

        System.out.println(Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        reader.close();
    }
}
