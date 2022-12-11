import java.util.*;

class Solution경창 {
    public int solution(int n, int k, int[] enemy) {
        // 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());


        // 90점 나온 소스 (12, 16, 20 소스)
//         long sum = 0;
//         int answer = 0;
//         int i = 0;
//         for(; i < enemy.length; i++){
//             // 총합에 현재 적의 수를 더해준다.
//             sum += enemy[i];
//             answer += 1;
//             // System.out.println("i : " + i + " " + sum);
//             // 만약 적의 총합 > 병사의 수인경우
//             if(sum > n){
//                 // 무작권횟수가 > 0인 경우
//                 if(k > 0 && pq.size() > 0){
//                     int insertMaxInPQ = pq.poll();
//                     k -= 1; // 무작권 횟수 -1 한다.

//                     // 만약 적의 수가 들어있는 pq에서 가장 큰 값보다 작은 경우는 해당 값을 넣고 가장 큰 값은 제외시킨다.
//                     // 아니라면 pq에서 넣지 않는다. (이전 그대로)
//                     if(enemy[i] < insertMaxInPQ){
//                         // 다시 넣는다.
//                         pq.add(enemy[i]);
//                         // 현재 적의 수 합한 것에서 다시 빼준다.
//                         sum -= insertMaxInPQ;

//                     }else{
//                         // 적의 수 priority queue에 다시 가장 큰 값을 넣어준다.
//                         // 현재 적의 수가 더 크거나 같으므로 sum에서 제외시켜준다.
//                         pq.add(insertMaxInPQ);
//                         sum -= enemy[i];
//                     }
//                 }else if(k > 0 && pq.size() == 0){
//                     k -= 1;
//                 }else{
//                     // k도 없고 넣을 수도 없는 경우
//                     break;
//                 }
//             }else{
//                 pq.add(enemy[i]);
//             }
//         }
//         answer = i;


        // 구글 참고 : https://velog.io/@sheltonwon/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%94%94%ED%8E%9C%EC%8A%A4-%EA%B2%8C%EC%9E%84-JAVA
        int answer = enemy.length; // k가 enemy size보다 큰경우가 있을 수 있어 제외
        for(int i =0; i< enemy.length; i++){
            n -= enemy[i];
            pq.add(enemy[i]);

            // 만약 병사의 수가 작고 무적권이 있을 경우
            // 병사의 수가 전체 적의 수보다 많은 경우는 총 적의 개수 만큼 받아 들일 수 있다.
            // n >= sum(enemy) 인 경우
            if(n < 0){
                if(k > 0 && pq.size() > 0){
                    n += pq.poll();
                    k -= 1;
                }else{
                    // 무적권이 없는 경우, pq에 저장된 값이 없을 때 break
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }
}