package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15661 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, min, r;
    static int[][] state;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        init();
        solve(0, 0, 0);
        System.out.println(min);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        state = new int[n][n];
        checked = new boolean[n];
        min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                state[i][j] = stoi(st.nextToken());
            }
        }
    }

    static void solve(int p, int a, int b) {
        //팀을 다 짰을 경우
        if(p == n) {
            if(a == 0 && b == 0) return;
            int start = 0;
            int link = 0;

            //먼저 각자 팀의 모든 능력치 합을 구한다.
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == j) continue;
                    if(checked[i] && checked[j]) {
                        start += state[i][j];
                    } else if(!checked[i] && !checked[j]){
                        link += state[i][j];
                    }
                }
            }

            //다 구했다면 min값 갱신
            min = Math.min(min, Math.abs(start-link));
            return;
        }

        checked[p] = true;
        solve(p+1, a+1, b);
        checked[p] = false;
        solve(p+1, a, b+1);
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}