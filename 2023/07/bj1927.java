import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class bj1927 {

    private static int N, X;

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for(int tk = 1; tk <= N; tk++){
            X = Integer.parseInt(br.readLine());
//            System.out.println("tk : " + tk + " size : " + pq.size() + " X: " + X);
            if(X == 0){
                if(pq.isEmpty()) builder.append(0).append("\n");
                else builder.append(pq.poll()).append("\n");
            }else{
                pq.add(X);
            }
        }

        System.out.print(builder.toString());

        br.close();

    }
}
