package BOJ;

import java.io.*;
import java.util.*;

public class BOJ17144 {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static final int DIRECTION_NUM = 4;
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int r, c, t, ans;
    static int[][] map;
    static Pair[] airCleaner;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //input
        st = new StringTokenizer(br.readLine());
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        t = stoi(st.nextToken());
        map = new int[r][c];
        airCleaner = new Pair[2];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j] == -1) {
                    //공기청정기 위치 저장
                    if(airCleaner[0] == null) {
                        airCleaner[0] = new Pair(i, j);
                    } else {
                        airCleaner[1] = new Pair(i, j);
                    }
                }
            }
        }

        //로직
        while(t-- > 0) {
            map = spreadAirPollution();
            turnOnAirCleaner();
        }

        //미세먼지 양 구하기
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] != -1) {
                    ans += map[i][j];
                }
            }
        }

        //정답 출력
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
    }

    public static int stoi(String s) {return Integer.parseInt(s);}

    public static int[][] spreadAirPollution() {
        int[][] tmp = new int[r][c];
        //공기청정기 위치 생성
        tmp[airCleaner[0].x][airCleaner[0].y] = -1;
        tmp[airCleaner[1].x][airCleaner[1].y] = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //먼지가 있을 때
                if(map[i][j] > 0) {
                    tmp[i][j] += map[i][j];
                    //네 방향으로 확산
                    for (int k = 0; k < DIRECTION_NUM; k++) {
                        int x = dx[k] + i;
                        int y = dy[k] + j;
                        //확산이 가능한가? : 범위를 벗어나지 않으면서 공기청정기가 없는 경우
                        if(x < 0 || y < 0 || x >= r || y >= c) continue;
                        if(map[x][y] == -1) continue;
                        //미세먼지 증가
                        tmp[x][y] += map[i][j] / 5;
                        tmp[i][j] -= map[i][j] / 5;
                    }
                }
            }
        }
        return tmp;
    }

    public static void turnOnAirCleaner() {
        //위쪽 공기청정기
        for (int i = airCleaner[0].x - 1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < airCleaner[0].x; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        for (int i = c - 1; i > airCleaner[0].y; i--) {
            map[airCleaner[0].x][i] = map[airCleaner[0].x][i - 1];
        }
        map[airCleaner[0].x][1] = 0;
        //아래쪽 공기청정기
        for (int i = airCleaner[1].x + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }

        for (int i = r - 1; i > airCleaner[1].x; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }

        for (int i = c - 1; i > airCleaner[1].y + 1; i--) {
            map[airCleaner[1].x][i] = map[airCleaner[1].x][i - 1];
        }

        map[airCleaner[1].x][1] = 0;
    }
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

