package ㅋㅋ루ㅋㅋ;

import java.io.*;

public class Solution {

    private static char[] cArr;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        cArr = reader.readLine().toCharArray();

        int left = 0;
        int right = 0;
        int answer = 0;

        while(left < cArr.length){
            if(cArr[right] == 'K'){
                right += 1;
            }else{
                answer += 1;
                left += 1;
            }
        }

        System.out.println(answer);

        reader.close();
    }
}
