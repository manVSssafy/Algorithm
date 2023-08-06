package 캐시;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private Queue<String> copyOfQueueExceptToLastData(Queue<String> queue, String city){
        // System.out.println("함수 시작");
        Queue<String> queue2 = new LinkedList<>();
        String inputLastData = "";
        while(queue.size() > 0){
            String curCity = queue.poll();
            // System.out.println("curCity : " + curCity + " city : " + city);
            if(curCity.equals(city)) inputLastData = curCity;
            else queue2.add(curCity);
        }

        // System.out.println("1 : queue2 : " + list + " inputLastData : " + inputLastData);
        if(!inputLastData.isEmpty()) queue2.add(inputLastData);

        // List<String> list = queue2.stream().collect(Collectors.toList());
        // System.out.println("2 : queue2 : " + list + " inputLastData : " + inputLastData);
        // System.out.println("함수 종료");

        return new LinkedList<>(queue2);
        // queue = new LinkedList<>(queue2);
        // List<String> list2 = queue.stream().collect(Collectors.toList());
        // System.out.println("queue : " + list2);
    }

    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        int answer = 0;

        for(String city : cities){
            city = city.toUpperCase();
            // 만약 캐시 사이즈 보다 작을 때
            if(queue.size() < cacheSize){
                if(map.getOrDefault(city, 0) == 0){
                    queue.add(city);
                    answer += 5;
                    map.put(city, 1);
                }
                else{
                    queue = copyOfQueueExceptToLastData(queue, city);
                    answer += 1;
                    map.put(city, 1);
                }


            }else{
                // 캐시 사이즈 일 때
                if(map.getOrDefault(city, 0) == 0){
                    map.put(queue.poll(), 0);
                    queue.add(city);
                    map.put(city, 1);
                    answer += 5;
                }else{
                    queue = copyOfQueueExceptToLastData(queue, city);
                    answer += 1;
                }
            }

            // List<String> list = queue.stream().collect(Collectors.toList());

            // System.out.println("city : " + city + " queue : " + Arrays.asList(list) + " answer : " + answer);

        }

        return answer;
    }
}
