package K진수에서_소수_구하기;

import java.util.*;

class Solution {
    private boolean decimal(long number){
        if(number == 1) return false;

        for(long i = 2; i < (long)Math.sqrt(number) + 1; i++){
            if(number % i == 0) return false;
        }
        return true;
    }

    public long solution(int n, int k) {

        // if(n == 1) return 0;
        long count = 0;
        String s = Integer.toString(n, k);

        StringBuilder builder = new StringBuilder();
        // System.out.println("변환 결과 : " + s);
        for(char c : s.toCharArray()){
            if(c == '0' && !builder.toString().isEmpty()){
                if(!builder.toString().equals("1")){
                    // System.out.println("소수 판결 숫자 : " + builder);
                    // System.out.println(decimal(Integer.parseInt(builder.toString())));
                    // builder가 소수인지 판별
                    if(decimal(Long.parseLong(builder.toString()))){
                        count++;
                    }
                }
                builder = new StringBuilder();
            }else if(c != '0'){
                // 숫자 추가
                builder.append(c);
            }
        }

        if(!builder.toString().isEmpty()
                && !builder.toString().equals("1")
                && decimal(Long.parseLong(builder.toString()))
        ){
            count++;
        }

        return count;
    }
}