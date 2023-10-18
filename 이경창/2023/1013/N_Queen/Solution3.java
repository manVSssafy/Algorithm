package N_Queen;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Solution3 {
    private static class Node2{
        public final int x;
        public final int y;

        public Node2(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int hashCode(){
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object o){
            Node2 node2 = (Node2)o;
            if(node2.x == this.x && node2.y == this.y) return true;
            else return false;
        }


    }
    public static void main(String[] args) {
        Set<Node2> set = new HashSet<>();

        set.add(new Node2(0 , 0));
        set.add(new Node2(0 , 0));
        System.out.println(set.size());
    }
}
