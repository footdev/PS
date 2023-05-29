package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class StartAndLink {
    static BufferedReader br;
    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[] start;
    static int[] link;
    static int startSum = 0;
    static int linkSum = 0;
    static boolean[] picked;
    static int[][] abilityMap;

    public static void solve(int[] start, int[] link, boolean[] picked, int depth, int idx) {
        if(depth == N/2) {
            int startCnt = 0;
            int linkCnt = 0;
            for(int i = 0; i < N; i++) {
                if(!picked[i]) {
                    link[linkCnt++] = i;
                }
                else {
                    start[startCnt++] = i;
                }
            }
            calculate(start, link, 0);
            ans = Math.min(Math.abs(startSum-linkSum), ans);
            startSum = 0;
            linkSum = 0;
            return;
        }

        for(int i = idx; i < N; i++) {
            picked[i] = true;
            solve(start, link, picked, depth+1, i+1);
            picked[i] = false;
        }
    }

    public static void calculate(int[] start, int[] link, int depth) {
        if(depth == 2) {
           startSum += abilityMap[start[0]][start[1]];
           linkSum += abilityMap[link[0]][link[1]];
            return;
        }
        for(int i = depth; i < N/2; i++) {
            swap(start, i, depth);
            swap(link, i, depth);
            calculate(start, link, depth+1);
            swap(start, i, depth);
            swap(link, i, depth);
        }
    }


    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        abilityMap = new int[N][N];
        start = new int[N/2];
        link = new int[N/2];
        picked = new boolean[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                abilityMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        link[0] = 1;
        picked[0] = true;
        solve(start, link, picked, 1, 1);
        System.out.println(ans);
    }
}
