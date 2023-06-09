package BOJ;

import java.io.*;
import java.util.*;


public class BOJ2961 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static Info[] resource;
    static boolean[] checked;
    static int min;

    public static void main(String[] args) throws IOException {
        init();
        solve(0);
        System.out.println(min);
    }

    static void init() throws IOException {
        min = Integer.MAX_VALUE;
        br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        resource = new Info[N];
        checked = new boolean[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            resource[i] = new Info(stoi(st.nextToken()), stoi(st.nextToken()));
        }
    }

    static void solve(int n) {
        if(n == N) {
            int sour = 1;
            int bitter = 0;
            //공집합 체크 변수
            boolean flag = false;
            for(int i = 0; i < N; i++) {
                //선택한 재료의 신맛과 쓴맛을 곱하고 더한다.
                if(checked[i]) {
                    flag = true;
                    sour *= resource[i].a;
                    bitter += resource[i].b;
                }
            }
            if(flag) {
                min = Math.min(min, Math.abs(sour-bitter));
            }
            return;
        }

        checked[n] = true;
        solve(n+1);
        checked[n] = false;
        solve(n+1);
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static class Info {
        int a;
        int b;
        Info(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
