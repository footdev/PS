package BOJ;

import java.io.*;
import java.util.*;

public class BOJ12865 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, w, v;
    static int[][] dp;
    static Jewel[] jewels;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        //초기 입력
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        dp = new int[n+1][k+1];
        jewels = new Jewel[n+1];
        //초기 -1 선언
        for(int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        //물건을 고르지 않은 경우를 초기 0으로 선언
        Arrays.fill(dp[0], 0);
        //무게가 0인 경우를 초기 0으로 선언
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        //각 보석의 정보를 받아옴
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w = stoi(st.nextToken());
            v = stoi(st.nextToken());

            jewels[i] = new Jewel(w, v);
        }
        //정답 출력
        System.out.println(top_down(n, k));
        for(int[] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static int top_down(int n, int k) {
        //현재 위치의 최댓값이 이미 있을 경우 : 바로 return
        if (dp[n][k] != -1) return dp[n][k];
        //아닐 경우 : n번째 보석이 선택 가능한 경우 인지 확인 후 된다면 값을 구한다.
        int selected = 0;
        if (k - jewels[n].w >= 0) {
            selected = top_down(n - 1, k - jewels[n].w) + jewels[n].v;
        }
        //dp[현재 보석의 수 -1][현재 무게 - n번째 보석의 무게] + n번째 보석의 가치, dp[현재 보석의 수 -1][현재무게]
        return dp[n][k] = Math.max(selected, top_down(n - 1, k));
    }

    static int bottom_up(int n, int k) {
        return 1;
    }
    static class Jewel {
        int w;
        int v;

        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}

