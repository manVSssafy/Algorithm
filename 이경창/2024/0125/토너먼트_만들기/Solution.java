package 토너먼트_만들기;

import java.util.*;
import java.io.*;

public class Solution {

    private static class Node{
        public final int startIdx;
        public final int value;
        public final int endIdx;

        Node(int startIdx, int value, int endIdx){
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.value = value;

        }
    }
    private static int n;
    private static int[] arr;
    private static int weight, height;

    private static int gameStation(){
        Map<Integer, Node> map = new HashMap<>();
        boolean[] visited = new boolean[n];
        int result = 0;

        while(height-- > 0){
            // 현재 방문안한 노드 파악하기
            // 파악한 후, 노드에 넣어주기
            Loop1:
            for(int i = 0; i < n - 1; i++){
                if(visited[i]){
                    if(map.containsKey(i)) map.remove(i);
                    continue;
                }
                for(int j = i + 1; j < n; j++){
                    if(!visited[i] && !visited[j]){
                        int value = Math.abs(arr[i] - arr[j]);
                        map.put(i, new Node(i, value, j));
                        break;
                    }
                }
            }

            List<Integer> list = new ArrayList<>(map.keySet());
            Collections.sort(list, (a, b) -> {
                Node leftNode = map.get(a);
                Node rightNode = map.get(b);

                // value 작은 것을 우선으로
                return leftNode.value - rightNode.value;
            });

//            System.out.println(list.size());
            boolean[] checkBox = new boolean[n];
            for(int i = 0; i < list.size(); i++){
                Node node = map.get(list.get(i));

                if(!visited[node.startIdx] && !visited[node.endIdx]) {
                    result += node.value;
                    System.out.println(arr[node.startIdx] + " " + arr[node.endIdx]);
                }


                if(arr[node.startIdx] <= arr[node.endIdx]) visited[node.endIdx] = true;
                else visited[node.startIdx] = true;
            }
            System.out.println();

        }


        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        arr = new int[tokenizer.countTokens()];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for(int i = 1; i <= n; i *= 2){
            height++;
        }
        // gameStation
        System.out.println(gameStation());


        reader.close();
    }
}
