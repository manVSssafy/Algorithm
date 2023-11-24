package 이차원_배열과_연산;

import java.util.*;
import java.io.*;

public class Solution {

    private static int r, c, k;
    private static int result;

    private static void function(int[][] arr, int time){
        if(arr[r][c] == k){
            result = Math.min(result, time);
            return;
        }


        if(time >= 100) return;

        Map<Integer, Integer> map = new HashMap<>();
        // 행 검토
        for(int i = 0; i < 110; i++){
            if(arr[i][c] == 0) break;

            map.putIfAbsent(arr[i][c], 0);
            map.put(arr[i][c], map.get(arr[i][c]) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, ((l1, l2) -> {
            // 0인 경우 제외
            if(l1 == 0 || l2 == 0) return 0;

            if(map.get(l1) != map.get(l2)) return map.get(l1) - map.get(l2);
            else {
                return l1 - l2;
            }
        }));

        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < list.size(); i++){
            s.append(list.get(i)).append(" ").append(map.get(list.get(i)));
        }

        int[] nextArr = s.toString().chars().boxed().map(c -> (c - '0')).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nextArr));

//        if(arr[r][i] == 0) break;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int[][] arr = new int[110][110];

        r = Integer.parseInt(tokenizer.nextToken()) - 1;
        c = Integer.parseInt(tokenizer.nextToken()) - 1;
        k = Integer.parseInt(tokenizer.nextToken());

        for(int i = 0; i < 3; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        result = Integer.MAX_VALUE;
        function(arr, 0);


        reader.close();
    }
}
