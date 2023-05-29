package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1753 {
    static final int MAX = Integer.MAX_VALUE;
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static ArrayList<Edge>[] adj;
    static int[] dp;
    static int v, e, k;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        //그래프 생성
        init();
        //dp[k]를 0으로 갱신 (나머지는 INF)하고 pq에 삽입
        dp[k] = 0;
        pq.add(new Edge(k, 0));
        //모든 정점 방문할 때 까지
        while(!pq.isEmpty()){
            //힙에서 최단거리 노드 가져오기
            Edge cur = pq.poll();
            //방문하고 있는 정점과 연결된 정점
            for(int i = 0; i < adj[cur.num].size(); i++) {
                Edge next = adj[cur.num].get(i);
                //이미 더 짧은 거리일 경우 갱신 할 필요 x
                if(dp[next.num] < next.weight) continue;
                //cur.weight = 시작 정점으로부터 cur 정점까지의 최소비용
                //next.weight = cur -> next 간선의 가중치
                //nextPath = 시작정점으로부터 cur 까지 최소비용 + next로 가는 가중치값
                int nextPath = cur.weight + next.weight;
                //현재 next까지의 최단거리보다 현재 지나온 경로가 더 빠른 경우 힙 삽입
                if(nextPath < dp[next.num]) {
                    dp[next.num] = nextPath;
                    pq.add(new Edge(next.num, dp[next.num]));
                }
            }
        }

        for (int i = 1; i < dp.length; i++) {
            sb.append(dp[i] == MAX ? "INF" : dp[i]).append("\n");
        }
        System.out.print(sb);

    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        st = new StringTokenizer(br.readLine());

        v = stoi(st.nextToken());
        e = stoi(st.nextToken());
        k = stoi(br.readLine());

        //최단거리 테이블 초기화
        dp = new int[v+1];
        Arrays.fill(dp, MAX);

        //그래프 초기화
        adj = new ArrayList[v+1];
        for (int i = 1; i < v+1; i++) {
            adj[i] = new ArrayList<>();
        }

        //그래프 생성
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());

            adj[from].add(new Edge(to, weight));
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class Edge {
    int num;
    int weight;

    public Edge(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}