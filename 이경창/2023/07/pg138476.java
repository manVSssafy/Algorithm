import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;

public class pg138476 {

    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < tangerine.length; i++){
            map.putIfAbsent(tangerine[i],0);
            map.put(tangerine[i], map.get(tangerine[i]) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (m1, m2) -> (map.get(m2).compareTo(map.get(m1))));


        int i = 0;
        for(; i < list.size(); i++){
            k -= map.get(list.get(i));
            // System.out.println("i : " + i + " k : " + k + " list : " + map.get(list.get(i)));
            if(k <= 0) break;
        }

        return i + 1;
    }
}
