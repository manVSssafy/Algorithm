import java.util.*;
import java.io.*;

public class bg1138 {

    private static int N;
    private static boolean[] visited;
    private static List<Integer> input;
//    private static List<Integer> answer;
    private static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        input = new ArrayList<>();
//        answer = new ArrayList<>();
        answer = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
        // hasMoreTokens()
        while(tokenizer.hasMoreTokens()){
            input.add(Integer.parseInt(tokenizer.nextToken()));
        }

        for(int humanIndex = 1; humanIndex <= N; humanIndex++){
            int count = 0;
            for(int i = 0; i < N; i++){
                if(visited[i]) continue;
                if(input.get(humanIndex - 1) == count){
                    visited[i] = true;
//                    answer.add(i + 1);
//                    builder.append(i + 1).append(" ");
                    answer[i] = humanIndex;
                }
                count++;
            }
        }

//        int[] answerInt = answer.stream().mapToInt(Integer::intValue).toArray();
//        System.out.println(builder.toString());
        for(int inAnswer : answer) System.out.print(inAnswer + " ");
        br.close();
    }
}
