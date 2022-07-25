import java.io.*;
import java.util.*;

public class BOJ2252 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m;
    static int[] in;
    static ArrayList<Integer>[] adj;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        /*
        n명의 학생을 키 순서대로 줄 세우기
        두 명의 학생만 비교할 수 있다.
         */

        //그래프 만들기
        init();
        //초기 테이블에서 진입 차수가 0인 V 큐에 모두 삽입
        for (int i = 1; i < n+1; i++) {
            if(in[i] == 0) {
                q.add(i);
            }
        }
        //큐가 빌 때 까지
        while(!q.isEmpty()) {
            //큐에서 꺼내옴
            int cur = q.poll();
            sb.append(cur + " ");
            //꺼내온 노드의 인접한 노드들 순회
            for(int next : adj[cur]) {
                //인접한 노드의 진입차수 테이블의 값 -1
                in[next]--;
                //만약 0이라면  큐에 삽입
                if(in[next] == 0) {
                    q.add(next);
                }
            }
        }

        //출력
        System.out.println(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        in = new int[n+1];
        adj = new ArrayList[n+1];

        for (int i = 0; i < n+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());

            adj[from].add(to);
            in[to]++;
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}