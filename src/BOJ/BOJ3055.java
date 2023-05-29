package BOJ;

import java.io.*;
import java.util.*;

public class BOJ3055 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static char[][] map;
    static boolean[][] visited;
    static int r, c, res;
    static Point start;
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(res != Integer.MIN_VALUE ? res : "KAKTUS");
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        res = Integer.MIN_VALUE;
        r = stoi(st.nextToken());
        c = stoi(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        q = new LinkedList<>();
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    start = new Point(i, j, 'S', 0);
                }
                if(map[i][j] == '*') {
                    q.add(new Point(i, j, '*', 0));
                }
            }
        }
    }

    static void solve() {
        //큐 생성
        //Q에 시작 노드삽입 : 고슴도치 위치와 물 위치 삽입
        visited[start.x][start.y] = true;
        q.add(start);
        //Q가 빌 때 까지
        while(!q.isEmpty()) {
            //Q에서 꺼내옴
            Point cur = q.poll();
            //목적지인가? : 현재 위치가 D인 경우
            if(cur.state == 'D') {
                res = cur.cnt;
                break;
            }
            //연결된 곳을 순회 : 상, 하, 좌, 우
            for(int i = 0; i < 4; i++) {
                int xx = dx[i] + cur.x;
                int yy = dy[i] + cur.y;

                //갈 수 있는가? (공통) : 벗어나지 않으면서 돌이 아닌 위치
                if(xx < 0 || yy < 0 || xx >= r || yy >= c || map[xx][yy] == 'X') continue;
                //물이 갈 수 있는가? : . , S 만 갈 수 있음
                if(cur.state == '*') {
                    if(map[xx][yy] == '.' || map[xx][yy] == 'S') {
                        map[xx][yy] = '*';
                        q.add(new Point(xx, yy, '*', cur.cnt+1));
                    }
                }
                //고슴도치가 갈 수 있는가? : 방문하지 않으면서 D이거나 .인 경우
                else if(cur.state == '.' || cur.state == 'S'){
                    if((map[xx][yy] == '.' || map[xx][yy] == 'D') && !visited[xx][yy]) {
                        //체크인
                        visited[xx][yy] = true;
                        //Q에 삽입
                        q.add(new Point(xx, yy, map[xx][yy], cur.cnt+1));
                    }
                }
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class Point {
    int x;
    int y;
    char state;
    int cnt;
    Point(int x, int y, char c, int cnt) {
        this.x = x;
        this.y = y;
        this.state = c;
        this.cnt = cnt;
    }
}