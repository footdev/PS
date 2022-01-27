import java.util.*;
import java.io.*;

public class StormPrint {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int r1, r2, c1, c2;
    private static int[][] map;
    private static int cnt, x, y = 0;
    private static int val = 1;
    private static int rIdx;
    private static int cIdx;
    private static int dir, turnCnt, currDist = 0;
    private static int dist = 1;
    private static StringBuilder sb;

    public static void fill() {
        rIdx = r1 * -1;
        cIdx = c1 * -1;
        //cnt 다 채울 때까지
        while(cnt != (r2-r1+1)*(c2-c1+1)) {
            if(y >= r1 && y <= r2 && x >= c1 && x <= c2) {
                map[y+rIdx][x+cIdx] = val;
                cnt++;
            }

            y = y+dy[dir];
            x = x+dx[dir];
            val++;
            currDist++;

            //방향전환을 해야할 때
            if(currDist == dist) {
                turnCnt++;
                dir = (dir+1) % 4;
                currDist = 0;

                //2번 방향전환 시 마다 거리가 1씩 늘어남
                if(turnCnt == 2) {
                    turnCnt = 0;
                    dist++;
                }
            }
        }
    }

    public static void print() {
        int max = searchMax();
        sb = new StringBuilder("%");
        sb.append(String.valueOf(max).length()).append("d");
        for(int i = 0; i <= r2-r1; i++) {
            for(int j = 0; j <= c2-c1; j++) {
                if(j != 0) {
                    System.out.print(" ");
                }
                System.out.printf(sb.toString(), map[i][j]);
            }
            System.out.println();
        }
    }
    public static int searchMax() {
        int max = map[0][0];
        for(int i = 0; i < r2-r1+1; i++) {
            for(int j = 1; j < c2-c1+1; j++) {
                if(max < map[i][j]) {
                    max = map[i][j];
                }
            }
        }
        return max;
    }

    public static void nonono(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        map = new int[r2-r1+1][c2-c1+1];

        fill();
        print();
    }
}
