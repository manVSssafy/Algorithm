package 줄서기;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node implements Comparable<Node>{
        public final char alpba;
        public final int number;
        public final int index;

        Node(char alpba, int number, int index){
            this.alpba = alpba;
            this.number = number;
            this.index = index;
        }

        public int compareTo(Node node){
            if(node.alpba != this.alpba) return this.alpba - node.alpba;
            else return this.number - node.number;
        }
    }

    private static int N;
    private static Node nodes[];
    private static int[][] arrIndex;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        // node 길이
        nodes = new Node[N * 5];
        arrIndex = new int[N][5];

        for(int i = 0; i < N; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for(int j = 0; j < 5; j++){
                String[] sArr = tokenizer.nextToken().split("-");
                int curIndex = i * 5 + j;
                nodes[curIndex] = new Node(sArr[0].charAt(0),Integer.parseInt(sArr[1]), curIndex);
            }
        }

        Arrays.sort(nodes);
        int rank = 0;
        for(int i = 0; i < N * 5; i++){
            int row = nodes[i].index / 5;
            int col = nodes[i].index % 5;
            arrIndex[row][col] = rank++;
        }

        rank = 0;
        Stack<Integer> store = new Stack<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 5; j++){
                int curRank = arrIndex[i][j];

                // (1) 현재 순위 증가
                if(curRank == rank){
                    rank++;
                }else if(store.size() > 0 && curRank > store.peek()){
                    // (2) stack이 더 작거나 같으면 꺼냄
                    while(store.size() > 0){
                        if(curRank < store.peek()){
                            break;
                        }
                        if(store.peek() != rank) break;
                        store.pop();
                        rank++;
                    }

                    if(rank == curRank) rank++;
                    else store.add(curRank);
                }else {
                    store.add(curRank);
                }
            }
        }

        while(store.size() > 0){
            if(store.size() >= 2 && store.pop() == store.peek() - 1) rank++;
            else if(store.size() == 1){
                store.pop();
                rank++;
            }else break;
        }

        if(rank == N * 5) System.out.println("GOOD");
        else System.out.println("BAD");

        reader.close();
    }
}
