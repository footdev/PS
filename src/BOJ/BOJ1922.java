package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1922 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] parent;
    static int n, m, minCost;

    public static void main(String[] args) throws IOException {
        //초기화
        init();
        //간선 데이터를 비용에 따라 오름차순 정렬(pq 사용)
        PriorityQueue<InputData> pq = new PriorityQueue<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new InputData(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())));
        }
        while(!pq.isEmpty()) {
            InputData cur = pq.poll();
            //간선을 하나씩 확인하며 현재 간선이 사이클을 발생하는지 확인
            if(find(cur.from) != find(cur.to)) {
                //발생하지 않는 경우 mst에 포함
                union(cur.from, cur.to);
                minCost += cur.weight;
            }
        }
        System.out.println(minCost);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        m = stoi(br.readLine());

        parent = new int[n+1];

        for (int i = 1; i < n+1; i++) {
            parent[i] = i;
        }
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        parent[aRoot] = bRoot;
    }

    static int find(int a) {
        return parent[a] = (parent[a] == a) ? a : find(parent[a]);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class InputData implements Comparable<InputData>{
    int from;
    int to;
    int weight;

    public InputData(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(InputData o) {
        return this.weight - o.weight;
    }
}