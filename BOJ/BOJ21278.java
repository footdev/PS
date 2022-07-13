import java.io.*;
import java.util.*;

class Point {
    int node;
    int cnt;
    Point(int node, int cnt) {
        this.node = node;
        this.cnt = cnt;
    }
}
public class BOJ21278 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, p1, p2, min;
    static ArrayList<Integer>[] g;
    static int[][] dist;
    static int[] picked;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        getDist();
        solve(0, 1);
        System.out.println(p1 + " " + p2 + " " + min);
    }

    static void init() throws IOException {
        min = Integer.MAX_VALUE;
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        dist = new int[n+1][n+1];
        g = new ArrayList[n+1];
        visited = new boolean[n+1];
        picked = new int[2];
        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }
    }

    static void solve(int cnt, int start) {
        if(cnt == 2) {
            int sum = 0;
            //세운 치킨 집 마다 모든 건물의 거리 구하기
            for(int i = 1; i <= n; i++) {
                sum += Math.min(dist[picked[0]][i], dist[picked[1]][i])*2;
            }
            if(min == sum) {
                if(p1 == picked[0]) {
                    if(p2 > picked[1]) {
                        p1 = picked[0];
                        p2 = picked[1];
                    }
                } else if(p1 > picked[0]) {
                    p1 = picked[0];
                    p2 = picked[1];
                }
            } else if(min > sum) {
                min = sum;
                p1 = picked[0];
                p2 = picked[1];
            }
            return;
        }

        for(int i = start; i <= n; i++) {
            picked[cnt] = i;
            solve(cnt+1, i+1);
        }
    }
    static void getDist() {
        boolean[][] checked = new boolean[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) {
                    dist[i][j] = 0;
                    checked[i][j] = true;
                    continue;
                }
                if(!checked[i][j]) {
                    dist[i][j] = bfs(i,j);
                    dist[j][i] = dist[i][j];
                    checked[i][j] = true;
                    checked[j][i] = true;
                }
            }
        }
    }

    static int bfs(int a, int b) {
        boolean[] checked = new boolean[n+1];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(a, 0));
        checked[a] = true;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            if(cur.node == b) {
                return cur.cnt;
            }
            for(int i = 0; i < g[cur.node].size(); i++) {
                //현재 위치의 노드와 연결 되어있는 노드 중 아직 방문하지 않은 노드를 찾기
                int idx = g[cur.node].get(i);
                if(!checked[idx]) {
                    checked[idx] = true;
                    q.add(new Point(idx, cur.cnt+1));
                }
            }
        }
        return -1;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}