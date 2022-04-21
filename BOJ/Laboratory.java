import java.io.*;
import java.util.*;

public class Laboratory {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int res = Integer.MIN_VALUE;
    static int[][] map, copyMap, virusMap;

    public static void main(String[] args) throws IOException{
        input();
        solve(0);
        System.out.println(res);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        copyMap = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';
                copyMap[i][j] = map[i][j];
            }
        }
    }

    static void solve(int depth) {
        if(depth == 3) {
            bfs();
            count();
            return;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copyMap[i][j] == 0) {
                    copyMap[i][j] = 1;
                    solve(depth+1);
                    copyMap[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<XyPoint> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(copyMap[i][j] == 2) {
                    q.add(new XyPoint(i, j));
                }
            }
        }
        virusMap = new int[n][m];
        for(int i = 0; i < n; i++) {
            virusMap[i] = copyMap[i].clone();
        }
        while(!q.isEmpty()) {
            XyPoint cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int xx = cur.x + dx[i];
                int yy = cur.y + dy[i];

                if(xx < 0 || yy < 0 || xx >= n || yy >= m || virusMap[xx][yy] != 0) continue;
                virusMap[xx][yy] = 2;
                q.add(new XyPoint(xx, yy));
            }
        }
    }

    static void count() {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(virusMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        res = Math.max(res, cnt);
    }
}

class XyPoint {
    int x;
    int y;
    XyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
