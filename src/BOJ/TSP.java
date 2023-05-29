package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TSP {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] W;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void solve() {
        for(int i = 0; i < N; i++) {
            boolean[] checked = new boolean[N];
            dfs(i,i,0, checked);
        }
    }

    public static void dfs(int s, int c, int sum, boolean[] checked) {
        //한바퀴 돌았을 때
        if(checked[c] && s == c) {
            //모든 도시를 방문했는지 체크해야함
            for(int i = 0; i < N; i++) {
                if(!checked[i]) return;
            }
            //모든 도시를 방문했다면 맞게 돈것이므로 ans와 sum을 비교해서 적은 값을 ans에 넣음
            ans = Math.min(ans, sum);
        }
        //현재 위치가 출발 지점은 아니고 방문한 도시일 때 return
        if(checked[c]) return;

        checked[c] = true;
        for(int i = 0; i < N; i++) {
            if(W[c][i] != 0) dfs(s, i, sum+W[c][i], checked);
        }
        checked[c] = false;
    }
}
