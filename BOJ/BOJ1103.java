import java.io.*;
import java.util.*;

public class BOJ1103 {
    static final int[] dx = {1, -1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, max;
    static int[][] board, dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0, 0);
        System.out.println(max);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        max = Integer.MIN_VALUE;
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if(c == 'H') {
                    board[i][j] = 0;
                } else {
                    board[i][j] = c - '0';
                }
            }
        }
    }

    static void dfs(int x, int y, int cnt) {
        //싸이클이 생겼다면 바로 탈출
        if(visited[x][y]) {
            max = -1;
            return;
        }
        //이미 전에 지나온 적 있는 곳
        if(dp[x][y] > 0) {
            //현재 이동횟수보다 크거나 같은 경우 가볼 필요 x
            if(dp[x][y] >= cnt) {
                return;
            }
        }
        //아직 지나가지 않았거나 현재 이동횟수가 더 큰 경우 dp값 갱신하고 계속 탐색
        dp[x][y] = cnt;

        //체크인
        visited[x][y] = true;

        //연결된 곳을 순회 : 상, 하, 좌, 우
        for(int i = 0; i < 4; i++) {
            int xx = dx[i] * board[x][y] + x;
            int yy = dy[i] * board[x][y] + y;

            //갈 수 있는가? : 보드판을 벗어나지 않으면서 구멍에도 빠지지 않는 곳
            if(xx >= 0 && yy >= 0 && xx < n && yy < m && board[xx][yy] > 0) {
                //간다.
                dfs(xx, yy, cnt+1);
            }
            // 물에 빠지거나 구멍에 빠지게 된다면
            else {
                //사이클이 이미 돌았을 경우 그냥 리턴
                if(max == -1){
                    visited[x][y] = false;
                    return;
                }
                //물에 빠진 횟수까지 해서 max값 갱신
                max = Math.max(max, cnt+1);
            }
        }
        //체크 아웃
        visited[x][y] = false;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}