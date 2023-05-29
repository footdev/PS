package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15685 {
    static final int K = 100;
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, ans;
    static Dragon[] dragons;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //input
        map = new boolean[K+1][K+1];
        n = stoi(br.readLine());
        dragons = new Dragon[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = stoi(st.nextToken());
            int x = stoi(st.nextToken());
            int d = stoi(st.nextToken());
            int g = stoi(st.nextToken());
            dragons[i] = new Dragon(x, y, d, g);
        }

        //implement
        //드래곤 커브 만들기
        List<Integer> dragonCurvePoint;
        for (int i = 0; i < n; i++) {
            Dragon curr = dragons[i];
             dragonCurvePoint = new ArrayList<>();
            int endX = 0, endY = 0;
            map[curr.x][curr.y] = true;
            //각 드래곤 커브는 주어진 g만큼의 세대까지 진화해야함
            for (int j = 0; j <= curr.g; j++) {
                //0세대는 선분 하나
                if(j == 0) {
                    int nx = dx[curr.d] + curr.x;
                    int ny = dy[curr.d] + curr.y;
                    map[nx][ny] = true;
                    dragonCurvePoint.add(curr.d);
                    endX = nx;
                    endY = ny;
                    continue;
                }
                //1세대 부터는 j-1세대의 정보를 기준으로 업데이트 해야함.
                int ex = endX;
                int ey = endY;
                int len = dragonCurvePoint.size();
                for (int k = len-1; k >= 0; k--) {
                    //90도 회전
                    int dir = (dragonCurvePoint.get(k) + 1) % 4;
                    //선긋기
                    ex = ex + dx[dir];
                    ey = ey + dy[dir];
                    map[ex][ey] = true;
                    //좌표저장
                    dragonCurvePoint.add(dir);
                }
                endX = ex;
                endY = ey;
            }
        }

        //정사각형 찾기
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j+1] && map[i+1][j]) {
                    ans++;
                }
            }
        }

        //output
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class Dragon {
    int x;
    int y;
    int d;
    int g;

    public Dragon(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
    }
}
