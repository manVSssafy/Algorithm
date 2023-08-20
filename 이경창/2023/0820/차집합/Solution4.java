package 차집합;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Solution4 {

    private static int nA, nB;
//    private static long[] arrA, arrB;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        nA = Integer.parseInt(tokenizer.nextToken());
        nB = Integer.parseInt(tokenizer.nextToken());

        Map<Long, Integer> map1 = new HashMap<>();
        Map<Long, Integer> map2 = new HashMap<>();

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < nA;i ++){
            long number = Long.parseLong(tokenizer.nextToken());
            map1.put(number, 1);
        }
        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < nB;i ++){
            long number = Long.parseLong(tokenizer.nextToken());
            map2.put(number, 2);
        }

        List<Long> list = new ArrayList<>(map1.keySet());
        List<Long> answer = new ArrayList<>();
        for(int i = 0; i < nA; i++){
            if(map2.getOrDefault(list.get(i), 0) == 0){
                answer.add(list.get(i));
            }
        }

        builder.append(answer.size());
        Collections.sort(answer);
        if(answer.size() > 0){
            builder.append("\n");
            for(long number : answer){
                builder.append(number).append(" ");
            }
        }

        System.out.println(builder.toString());

        reader.close();
    }
}
