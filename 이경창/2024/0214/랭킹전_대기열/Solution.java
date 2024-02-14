package 랭킹전_대기열;

import java.io.*;
import java.util.*;

public class Solution {

    private static class Node{
        public final int index;
        public final ArrayList<Integer> indexList;

        Node(int index, ArrayList<Integer> indexList){
            this.index = index;
            this.indexList = indexList;
        }

        public Node add(int index){
            this.indexList.add(index);
            return new Node(this.index, this.indexList);
        }
    }

    private static int p, m;
    private static ArrayList<Node> nodes;
    private static int[] arr;
    private static String[] sArr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        p = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        nodes = new ArrayList<>();
        arr = new int[p];
        sArr = new String[p];

        for(int i = 0; i < p; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int l = Integer.parseInt(tokenizer.nextToken());
            String s = tokenizer.nextToken();

            arr[i] = l;
            sArr[i] = s;
        }

        for(int i = 0; i < p; i++){
            boolean check = false;

            for(int j = 0; j < nodes.size(); j++){

                if(nodes.get(j).indexList.size() < m && Math.abs(arr[i] - arr[nodes.get(j).index]) <= 10){
                    check = true;
                    nodes.set(j, nodes.get(j).add(i));
                    break;
                }
            }

            if(!check){
                nodes.add(new Node(i, new ArrayList<>()));
                nodes.set(nodes.size() - 1, nodes.get(nodes.size() - 1).add(i));
            }
        }

        StringBuilder builder = new StringBuilder();
        for(Node node : nodes){
            if(node.indexList.size() == m) builder.append("Started!").append("\n");
            else builder.append("Waiting!").append("\n");

            ArrayList<Integer> list = node.indexList;
            Collections.sort(list, (l1, l2)->{
                return sArr[l1].compareTo(sArr[l2]);
            });

            for(int idx : list){
                builder.append(arr[idx]).append(" ").append(sArr[idx]).append("\n");
            }
        }

        System.out.print(builder.toString());

        reader.close();
    }
}
