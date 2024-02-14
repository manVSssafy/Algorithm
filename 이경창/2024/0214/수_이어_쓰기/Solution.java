package 수_이어_쓰기;

import java.util.*;
import java.io.*;

public class Solution {


    private static String s;
    private static int N;
    private static int number;
    private static int index;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        s = reader.readLine();

        while(true){
            if(index == s.length()) break;
            else{
                number++;
                char[] numberChar = String.valueOf(number).toCharArray();

                // cArr 잘라야 함

                char[] cArr;
                if(index + numberChar.length > s.length()){
                    cArr = s.substring(index, s.length()).toCharArray();
                }else{
                    cArr = s.substring(index, index + numberChar.length).toCharArray();
                }

                int i = 0;
                int i2 = 0;
                for(; i < numberChar.length && i2 < cArr.length; i++){
                    if(cArr[i2] == numberChar[i]){
                        i2++;
                    }
                }

                index += i2;
            }

        }

        System.out.println(number);

        reader.close();
    }
}
