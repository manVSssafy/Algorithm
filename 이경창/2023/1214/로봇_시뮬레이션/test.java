package 로봇_시뮬레이션;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();

        builder2.append("A-");
        builder = new StringBuilder(builder.toString().toUpperCase());
        builder.append("B-");

        String[] s2 = new String[]{builder.toString(), builder2.toString()};
        Arrays.sort(s2, (a, a2) ->{
            if(a.compareTo(a2) < 0) return -1;
            else return 1;
        });
        System.out.println(s2[0] + " " + s2[1]);

    }
}
