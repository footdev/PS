package BOJ;

import java.io.*;

public class BOJ17626 {
    static BufferedReader br;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(dp[N]);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[1] = 1;
    }

    static void solve() {
        for(int i = 2; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 1; j*j <= i; j++) {
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i] = min + 1;
        }
    }
}