package 단축키_지정;

import java.io.*;

public class Solution {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        boolean[] visited = new boolean['z' - 'a' + 1];

        for(int i = 0; i < N; i++){
            String s = reader.readLine();
            String[] sArr = s.split(" ");
            boolean firstCase = false;
            int j = 0;
            // 첫 번째 문자를 아직 방문하지 않았는지 검토
            for(; j < sArr.length; j++){
                String inS = sArr[j].toUpperCase();
                char[] c = inS.toCharArray();

                // 방문하지 않았다면
                if(!visited[c[0] - 'A']){
                    visited[c[0] - 'A'] = true;
                    for(int k = 0; k < j; k++){
                        builder.append(sArr[k]).append(" ");
                    }
                    builder.append("[").append(sArr[j].substring(0, 1)).append("]").append(sArr[j].substring(1, inS.length())).append(" ");
                    firstCase = true;
                    j += 1;
                    break;
                }
            }
            while(firstCase && j < sArr.length){
                builder.append(sArr[j++]).append(" ");
            }


            if(!firstCase){
//                System.out.println("검토 : " + s.toString());
//                System.out.println(builder.toString());
//                System.out.println("끝");
                boolean secondCase = false;
                int j2 = 0;
                Loop1:
                for(; j2 < sArr.length; j2++){
                    String inS = sArr[j2].toUpperCase();

                    for(int k2 = 0; k2 < j2; k2++){
                        builder.append(sArr[k2]).append(" ");
                    }
                    char[] c = inS.toCharArray();
                    for(int k = 0; k < c.length; k++){
                        if(!visited[c[k] - 'A']){
                            visited[c[k] - 'A'] = true;
                            secondCase = true;
                            builder.append(sArr[j2].substring(0, k)).append("[").append(sArr[j2].substring(k, k+1)).append("]").append(sArr[j2].substring(k + 1, inS.length())).append(" ");
                            j2 += 1;
                            break Loop1;
                        }
                    }
                }

                while(secondCase && j2 < sArr.length){
                    builder.append(sArr[j2++]).append(" ");
                }

                if(!secondCase) builder.append(s);
            }
            builder.append("\n");
        }

        System.out.print(builder.toString());

        reader.close();
    }
}