package 걷기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private static long x, y, w, s;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        x = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());


//        long minLen = Math.min(x, y);
        long result = 0;
        // 가로, 세로 한 블록으로 가는 것이, 대각선으로 가는 것 보다 작은 경우
        if(2 * w < s){
            result = (x + y) * w;
        }else if(2 * w > 2 * s){
            // 칸 이동하는 것이 대각선으로 이동하는 것보다 클 때
            if((x + y) % 2 == 0){
                result = Math.max(x, y) * s;
            }else{
                result = (Math.max(x, y) - 1) * s;
                result += w;
            }
        }
        else {
            // 대각선
            if(x == y) result = x * s;
            else{
                result = Math.min(x * s, y * s);
                result += Math.abs(x - y) * w;
            }
        }


        System.out.println(result);

        reader.close();
    }
}
