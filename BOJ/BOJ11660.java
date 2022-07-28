import java.io.*;
import java.util.*;

public class BOJ11660 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[][] board, dp;
    static int n, m, x1, x2, y1, y2;

    public static void main(String[] args) throws IOException {
        //초기화
        init();
        //dp초기화
        initDp();
        //구간합 구하기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = stoi(st.nextToken());
            y1 = stoi(st.nextToken());
            x2 = stoi(st.nextToken());
            y2 = stoi(st.nextToken());
            sb.append(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]).append("\n");
        }

        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken())+1;
        m = stoi(st.nextToken());

        board = new int[n][n];
        dp = new int[n][n];

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }
    }

    static void initDp() {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + board[i][j];
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}