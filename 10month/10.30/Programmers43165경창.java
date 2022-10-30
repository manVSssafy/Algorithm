public class Programmers43165경창 {

    static int answer = 0;

    void dfs(int[] numbers, int cnt, int target, int res) {
        if (cnt == numbers.length) {
            if (res == target) answer += 1;
            return;
        }

        dfs(numbers, cnt + 1, target, res + numbers[cnt]);
        dfs(numbers, cnt + 1, target, res - numbers[cnt]);
    }

    public int solution(int[] numbers, int target) {


        dfs(numbers, 0, target, 0);

        System.out.println(answer);

        return answer;
    }
}
