package 주사위_굴리기2;

import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 0 ,2};
        int[] nextArr = Arrays.stream(arr).boxed().sorted((a, b) -> ( b- a)).mapToInt(Integer::intValue).toArray();

        System.out.println(Arrays.toString(nextArr));

    }
}
