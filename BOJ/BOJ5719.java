import java.io.*;
import java.util.*;

public class BOJ5719 {
    static final int MAXN = 500;
    static final int INF = Integer.MAX_VALUE;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[][] adj;
    static int[] dp;
    static int n, m, s, d, from, to, w;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        adj = new int[MAXN][MAXN];
        dp = new int[MAXN];

        while (true) {
            st = new StringTokenizer(br.readLine());

            n = stoi(st.nextToken());
            m = stoi(st.nextToken());

            if(n == 0 && m == 0) break;

           for(int[] arr : adj) {
               Arrays.fill(arr, 0);
           }

            st = new StringTokenizer(br.readLine());
            s = stoi(st.nextToken());
            d = stoi(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                from = stoi(st.nextToken());
                to = stoi(st.nextToken());
                w = stoi(st.nextToken());
                adj[from][to] = w;
            }

            dijkstra(s);
            removeShortest(d);
            dijkstra(s);

            sb.append(dp[d] == INF ? -1 : dp[d]).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra(int s) {
        Arrays.fill(dp, INF);
        PriorityQueue<Route> pq = new PriorityQueue<>(((o1, o2) -> o1.w - o2.w));
        pq.add(new Route(s, 0));
        dp[s] = 0;
        while(!pq.isEmpty()) {
            Route cur = pq.poll();
            //현재 최단경로의 정점과 연결된 경로 순회
            for (int i = 0; i < n; i++) {
                //연결 안되어 있거나 이미 최단경로가 있는데 더 작은경우는 안봐도 됨
                if(adj[cur.v][i] == 0 || adj[cur.v][i] > dp[i]) continue;
                //현재의 경로로 갱신해야 한다면
                int nextWeight = adj[cur.v][i] + dp[cur.v];
                if(nextWeight < dp[i]) {
                    dp[i] = nextWeight;
                    pq.add(new Route(i, dp[i]));
                }
            }
        }
    }

    static void removeShortest(int d) {
        Queue<Integer> q = new LinkedList<>();
        q.add(d);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int to = 0; to < n; to++) {
                //해당 경로가 최단경로 라면
                if(adj[to][cur] != 0 && dp[cur] == adj[to][cur] + dp[to]) {
                    //삭제
                    adj[to][cur] = 0;
                    //큐에 삽입
                    q.add(to);
                }
            }
        }
    }


    static int stoi(String s) {return Integer.parseInt(s);}
}

class Route {
    int v;
    int w;

    public Route(int v, int w) {
        this.v = v;
        this.w = w;
    }
}