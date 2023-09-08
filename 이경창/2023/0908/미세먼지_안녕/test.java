package 미세먼지_안녕;

import java.util.Arrays;

public class test {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    // 우, 하, 좌, 상
    private static int[] dx2 = {1, 0, -1, 0};
    private static int[] dy2 = {0, 1, 0, -1};

    private static boolean isWithInRange(int x, int y){
        if(0 <= x && x < 6 && 0 <= y && y <6) return true;
        else return false;
    }

    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int v = 1;
        int d = 0;

        int[][] arr = new int[6][6];

        while(true){

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!isWithInRange(nx, ny)){

                d = (d + 1) % 4;
                nx = x + dx[d];
                ny = y + dy[d];

                if(arr[ny][nx] != 0) break;
            }
            arr[y][x] = v++;
            x = nx;
            y = ny;
        }

        x = 0;
        y = 0;
        d = 0;
        int startData = arr[0][0];

        while(true){
            int nx = x + dx2[d];
            int ny = y + dy2[d];

            if(!isWithInRange(nx, ny)){
                d = (d + 1) % 4;
                nx = x + dx2[d];
                ny = y + dy2[d];
            }
            if(nx == 0 && ny == 0){
                arr[y][x] = startData;
                break;
            }else {
                arr[y][x] = arr[ny][nx];
                x = nx;
                y = ny;
            }
        }

        for(int i = 0 ; i < arr.length ; i++) System.out.println(Arrays.toString(arr[i]));
    }
}
