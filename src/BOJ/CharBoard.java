package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CharBoard {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] ansWord;
    static char[][] board;
    static int[][][] dp;
    static int n, m, k;
    static int ans;

    public static void main(String[] args) throws IOException{
        input();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == ansWord[0]) ans += dfs(0, i, j);
            }
        }
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        for(int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        ans = 0;
        ansWord = br.readLine().toCharArray();
        dp = new int[n][m][ansWord.length];
        for(int[][] a : dp) {
            for(int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
    }

    static int dfs(int idx, int x, int y) {
        //마지막 문자까지 왔다는건 그 전까지 계속 유망했으며 주어진 단어와 동일하다는 뜻이므로 1 반환
        if(idx == ansWord.length-1) return dp[x][y][idx] = 1;
        //현재 위치가 이미 방문한적 있는 위치일 경우 메모이제이션 된 값 리턴
        if(dp[x][y][idx] != -1) return dp[x][y][idx];
        //현재 위치가 주어진 단어의 위치와 맞지 않는 경우 0 반환(유망x)
        if(board[x][y] != ansWord[idx]) return dp[x][y][idx] = 0;

        //0을 먼저 초기화 시키는 이유는 나아갈 수 있는 방향이 하나도 없을 경우 0을 리턴해야하기 때문이다.
        dp[x][y][idx] = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= k; j++) {
                int xx = x + dx[i] * j;
                int yy = y + dy[i] * j;

                //다음 위치가 범위를 벗어나거나 유망하지 않은 경우는 통과시킨다.
                if(xx < 0 || yy < 0 || xx >= n || yy >= m || board[xx][yy] != ansWord[idx+1]) continue;
                //다음 위치가 유망한 경우 dfs를 통해 현재 위치를 메모이제이션 한다.
                dp[x][y][idx] += dfs(idx+1, xx, yy);
            }
        }

        return dp[x][y][idx];
    }
}
