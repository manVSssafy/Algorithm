import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class bj1927_찬희 {
    public static void main(String[] args) throws IOException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st=new StringTokenizer(br.readLine(),"");
            int temp=Integer.parseInt(st.nextToken());
            if (temp==0){
                if(pq.isEmpty()){
                    System.out.println("0");
                }
                else {
                    System.out.println(pq.poll());
                }
            }
            else{
                pq.add(temp);
            }
        }
    }
}

