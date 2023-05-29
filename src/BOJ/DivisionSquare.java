package BOJ;

import java.io.*;
import java.util.*;

public class DivisionSquare {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M;
    static int[][] arr;
    static long[][] sum;
    static long ans = -1;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        sum = new long[N+1][M+1];
        for(int[] a : arr) {
            Arrays.fill(a, 0);
        }
        for(int i = 1; i <= N; i++) {
            String s = br.readLine();
            for(int j = 1; j <= M; j++) {
                arr[i][j] = s.charAt(j-1) - '0';
            }
        }
    }


    public static void solve() {
        //1. sum 구하기
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + (long)arr[i][j];
            }
        }

        //2. 6개의 경우 별로 곱의 합 구하기
        //가로 블록 3개 나누기
        for(int i = 1; i <= N-2; i++) {
            for(int j = i+1; j <= N-1; j++) {
                long r1 = sum(1, 1, i, M);
                long r2 = sum(i+1, 1, j, M);
                long r3 = sum(j+1, 1, N, M);
                ans = Math.max(ans, r1 * r2 * r3);
            }
        }

        //세로 블록 3개 나누기
        for(int i = 1;  i <= M-2; i++) {
            for(int j = i+1; j <= M-1; j++) {
                long r1 = sum(1, 1, N, i);
                long r2 = sum(1, i+1, N, j);
                long r3 = sum(1, j+1, N, M);
                ans = Math.max(ans, r1 * r2 * r3);
            }
        }
        //나머지 가로1 세로2, 가로2 세로1개 나누기

        //ㅜ형
       for(int i = 1; i <= N-1; i++) {
           for(int j = 1; j <= M-1; j++) {
               long r1 = sum(1, 1, i, M);
               long r2 = sum(i+1, 1, N, j);
               long r3 = sum(i+1, j+1, N, M);

               ans = Math.max(ans, r1*r2*r3);
           }
       }
        //ㅗ형
       for(int i = N; i > 1; i--) {
           for(int j = 1; j <= M-1; j++) {
               long r1 = sum(i, 1, N, M);
               long r2 = sum(1, 1, i-1, j);
               long r3 = sum(1, j+1, i-1, M);

               ans = Math.max(ans, r1*r2*r3);
           }
       }

       //ㅏ형
        for(int i = 1; i <= M-1; i++) {
            for(int j = 1; j <= N-1; j++) {
                long r1 = sum(1, 1, N, i);
                long r2 = sum(1, i+1, j, M);
                long r3 = sum(j+1, i+1, N, M);

                ans = Math.max(ans, r1*r2*r3);
            }
        }

        //ㅓ형
        for(int i = M; i > 1; i--) {
            for(int j = 1; j <= N-1; j++) {
                long r1 = sum(1, i, N, M);
                long r2 = sum(1, 1, j, i-1);
                long r3 = sum(j+1, 1, N, i-1);

                ans = Math.max(ans, r1*r2*r3);
            }
        }
    }

    public static long sum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
    }
}
