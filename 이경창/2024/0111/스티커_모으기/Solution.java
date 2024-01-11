package 스티커_모으기;

class Solution {

    private int[] sticker;
    private int len;
    private int answer;

    private void dfs(int index, int sum, boolean check){

        // 범위를 벗어난 경우
        if(index >= len){
            answer = Math.max(answer, sum);
            // System.out.println("첫 번째 실행");
            // System.out.println(builder.toString());
            return;
        }


        if(index == len - 1 && check){
            answer = Math.max(answer, sum);
            // System.out.println("두 번째 실행");
            // System.out.println(builder.toString());
            return;
        }


        // dfs(index + 2, sum + sticker[index], check, new StringBuilder(builder).append(index).append(" "));
        // dfs(index + 3, sum + sticker[index], check, new StringBuilder(builder).append(index).append(" "));

        dfs(index + 2, sum + sticker[index], check);
        dfs(index + 3, sum + sticker[index], check);
    }

    public int solution(int _sticker[]) {
        sticker = _sticker;
        len = sticker.length;
        // System.out.println("len : " + len);
        // 홀수인 경우 첫 번째와 마지막은 겹침
//         dfs(0, 0, true, new StringBuilder());
//         dfs(1, 0, false, new StringBuilder());

        dfs(0, 0, true);
        dfs(1, 0, false);


        return answer;
    }
}