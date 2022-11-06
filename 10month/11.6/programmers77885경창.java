import java.util.ArrayList;

class Solution {
    public ArrayList<Long> solution(long[] numbers) {

        ArrayList<Long> answer = new ArrayList<>();

        for(long num : numbers){
            // String에 나머지 하나씩 저장
            String s = "";

            while(num > 0){
                int div = (int)(num % 2);
                s += Integer.toString(div);
                num /= 2;
            }

            boolean check = false;
            long curAnswer = 0;
            long binary_num = 1;

            for(int i =0; i< s.length();i++){

                if(s.charAt(i) == '1'){
                    curAnswer += binary_num;
                }else if(check == false && s.charAt(i) == '0'){
                    curAnswer += binary_num;
                    check = true;

                    if(i > 0){
                        curAnswer -= (binary_num / 2);
                    }
                }
                // System.out.println("실행 결과 " + " binary_num : " + binary_num + " answer : " + curAnswer);
                binary_num *= 2;
            }


            if(!check){
                curAnswer += binary_num;
                binary_num /= 2;
                curAnswer -= binary_num;
            }

            answer.add(curAnswer);
        }

        return answer;
    }
}