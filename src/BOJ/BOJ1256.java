package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1256 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k;
    static int[][] dp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();
        if(k > combination(n+m, m)) {
            System.out.println(-1);
        } else {
            query(n, m, k);
            System.out.println(sb);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());
        dp = new int[201][201];
    }

    static void query(int n, int m, int k) {
        //n과m을 모두 구했을 때
        if(n + m == 0) {
            return;
        }
        //a를 다 썼을 때
        else if(n == 0) {
            sb.append('z');
            query(n, m-1, k);
        }
        //z를 다 썼을 때
        else if(m == 0) {
            sb.append('a');
            query(n-1, m, k);
        } else {
            //둘 다 남았을 때 n+m-r C m 구하기
            int leftCount = combination(n+m-1, m);
            //'a'로 시작하는 문자열에 없는 경우
            if(k <= leftCount) {
                sb.append('a');
                query(n-1, m, k);
            } else {
                sb.append('z');
                query(n, m-1, k-leftCount);
            }
        }
    }

    static int combination(int n, int r) {
        if(n == r || r == 0) {
            return 1;
        }

        if(dp[n][r] != 0) {
            return dp[n][r];
        }

        return dp[n][r] = Math.min((int)1e9, combination(n - 1, r - 1) + combination(n - 1, r));
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}