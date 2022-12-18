package Solution경창;

import java.util.*;

class Node {
    long x;
    long y;

    Node(long x, long y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    public ArrayList<String> solution(int[][] line) {


        ArrayList<Node> list = new ArrayList<>();
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length; i++) {
            long a1 = line[i][0];
            long b1 = line[i][1];
            long c1 = line[i][2];

            for (int j = i + 1; j < line.length; j++) {
                long a2 = line[j][0];
                long b2 = line[j][1];
                long c2 = line[j][2];

                long xup = b1 * c2 - c1 * b2;
                long xdown = a1 * b2 - b1 * a2;

                long yup = c1 * a2 - a1 * c2;
                long ydown = a1 * b2 - b1 * a2;


                if (xdown != 0 && ydown != 0) {
                    double x = xup / (double) xdown;
                    double y = yup / (double) ydown;

                    if (x == Math.ceil(x) && y == Math.ceil(y)) {
                        minX = Math.min(minX, (long) x);
                        maxX = Math.max(maxX, (long) x);
                        minY = Math.min(minY, (long) y);
                        maxY = Math.max(maxY, (long) y);
                        list.add(new Node((long) x, (long) y));
                    }
                }
            }
        }

        // 이제 점찍기
        boolean[][] graph = new boolean[(int) (maxY - minY) + 1][(int) (maxX - minX) + 1];
        // System.out.println("maxX : " + maxX + " maxY" + maxY);
        // System.out.println("minX : " + minX + " minY" + minY);
        // 해당 지점 true로 점찍는 위치 찾기
        for (Node node : list) {

            int curX = (int) (node.x - minX); // minX : 0 열에서
            int curY = (int) (node.y - maxY); // maxY : 0 행에서


            // System.out.println("점 찍는 위치 : " + (curX) + " , " + (curY));
            // System.out.println("점  : " + (node.x) + " , " + (node.y));

            graph[Math.abs(curY)][Math.abs(curX)] = true;
        }


        ArrayList<String> answer = new ArrayList<>();

        // 점 찍기
        for (int i = 0; i < graph.length; i++) {
            String curResult = "";
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j]) curResult += "*";
                else curResult += ".";
            }
            answer.add(curResult);
        }


        return answer;
    }
}
