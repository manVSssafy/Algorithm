package 후보_추천하기;

import java.io.*;
import java.util.*;
import java.util.stream.Collector;

public class Solution {

    private static class UnityInnovators implements Comparable<UnityInnovators> {
        public final int peopleIndex;
        public final int time;
        public final int count;

        public UnityInnovators(int peopleIndex, int time, int count){
            this.peopleIndex = peopleIndex;
            this.time = time;
            this.count = count;
        }

        public UnityInnovators update(int peopleIndex, int time, int count){
            return new UnityInnovators(peopleIndex, time, count);
        }

        public UnityInnovators updateCount(){
            return new UnityInnovators(this.peopleIndex, this.time, this.count + 1);
        }

        public int compareTo(UnityInnovators innovators){
            // 횟수가 다르다면 정렬
            if(this.count != innovators.count) return innovators.count - this.count;
            else return innovators.time - this.time;
        }
    }

    private static int N;
    private static int peopleRecommandCount;
    private static int[] peopleNumberList;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        peopleRecommandCount = Integer.parseInt(reader.readLine());
        peopleNumberList = new int[peopleRecommandCount];

        UnityInnovators[] innovators = new UnityInnovators[N];

        for(int i = 0; i < N; i++){
            innovators[i] = new UnityInnovators(-100, -100, -100);
        }

        Set<Integer> set = new HashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for(int i = 0; i < peopleRecommandCount; i++){
            int number = Integer.parseInt(tokenizer.nextToken());
            set.add(number);
            peopleNumberList[i] = number;
        }

        // 사진틀의 개수보다 적거나 같은 경우라면 그냥 출력한다.
        if(set.size() <= N){
            ArrayList<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            for(int inList : list) System.out.print(inList + " ");
            System.exit(0);
        }

        for(int time = 1; time <= peopleRecommandCount; time++){
            int curInnovator = peopleNumberList[time - 1];

            for(int j = 0; j < N; j++){
                if(innovators[j].peopleIndex == curInnovator){
                    // 추천받은 사용자가 이미 사진틀에 있는 경우
                    innovators[j] = innovators[j].updateCount();
                    break;
                }else if(innovators[j].peopleIndex == -100 || j == N - 1){
                    // 초기값 or 마지막 인덱스라면 데이터 변경하기
                    innovators[j] = innovators[j].update(curInnovator, time, 1);
                    break;
                }
            }
            Arrays.sort(innovators);
        }

        int[] answer = new int[N];

        for(int i = 0; i < N; i++){
            answer[i] = innovators[i].peopleIndex;
        }

        Arrays.sort(answer);

        for(int inAnswer : answer){
            System.out.print(inAnswer + " ");
        }

        System.out.println();
        reader.close();
    }
}
