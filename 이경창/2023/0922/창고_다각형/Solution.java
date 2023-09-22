package 창고_다각형;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N;
    private static int curHeight;
    private static int curWeightLoc;
    private static int beforeHeight, beforeWeightLoc;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
//        int[][] arr2 = new int[][]{{5,10},{3,30},{1,50},{4,20},{2,40},{5,60}};
//        Arrays.sort(arr2);
        arr = new int[N][2];

        int maxHeightFirstIndex = 0;
        int maxHeightLastIndex = 0;

        for(int i = 0 ; i < N ; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int height = Integer.parseInt(tokenizer.nextToken());

            arr[i][0] = weight;
            arr[i][1] = height;
        }

        Arrays.sort(arr,(a1, a2) -> {
            if(a1[0] < a2[0]) return -1;
            else if(a1[0] == a2[0]) return 0;
            else {
                return 1;
            }
        });

        for(int i = 1 ; i < N; i++){
            if(arr[maxHeightLastIndex][1] <= arr[i][1]) maxHeightLastIndex = i;
        }

        for(int i = 0; i < N; i++){
            if(arr[maxHeightLastIndex][1] == arr[i][1]){
                maxHeightFirstIndex = i;
                break;
            }
        }

        int answer = 0;
        curWeightLoc = arr[0][0];
        curHeight = arr[0][1];

        // 가장 높은 구간
        answer += (arr[maxHeightLastIndex][0] - arr[maxHeightFirstIndex][0] + 1) * arr[maxHeightFirstIndex][1];

        // 오른쪽 방향으로 검토
        for(int i = 0; i <= maxHeightFirstIndex; i++){
            if(curHeight < arr[i][1]){
                answer += curHeight * (arr[i][0] - curWeightLoc);
//                System.out.println("i : " + i + " " + answer);
                curHeight = arr[i][1];
                curWeightLoc = arr[i][0];
            }
        }

//        System.out.println("앞부분 : " + answer);

//        int after = 0;

        curWeightLoc = arr[arr.length - 1][0];
        curHeight = arr[arr.length - 1][1];
        // 왼쪽 방향으로 검토
        for(int i = arr.length - 1; i >= maxHeightLastIndex; i--){
            if(curHeight < arr[i][1]){
                answer += curHeight * (curWeightLoc - arr[i][0]);
                curHeight = arr[i][1];
                curWeightLoc = arr[i][0];
            }
        }
//        System.out.println("뒷부분 : " + answer + " 앞 : " + maxHeightFirstIndex + " 뒤 : " + maxHeightLastIndex);
        System.out.println(answer);

        reader.close();
    }
}
