import java.io.*;
import java.util.*;

public class BOJ14391 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] arr;
    static boolean[][] marked;
    static int n, m, total, max;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0);
        System.out.println(max);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        total = n * m;
        arr = new int[n][m];
        marked = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
    }

    static void dfs(int x, int y) {
        if(x == n) {
           check();
           return;
        }
        //가로, 세로
        marked[x][y] = true;
        if(y+1 == m) {
            dfs(x+1, 0);
        } else {
            dfs(x, y+1);
        }

        marked[x][y] = false;
        if(y+1 == m) {
            dfs(x+1, 0);
        } else {
            dfs(x, y+1);
        }

    }

    static void check() {
        int sum = 0;
        int colSum, rowSum;
        //가로
        for(int i = 0; i < n; i++) {
            colSum = 0;
            for(int j = 0; j < m; j++) {
                if(marked[i][j]) {
                    colSum *= 10;
                    colSum += arr[i][j];
                } else {
                    sum += colSum;
                    colSum = 0;
                }
            }
            sum += colSum;
        }

        //세로
        for(int i = 0; i < m; i++) {
            rowSum = 0;
            for(int j = 0; j < n; j++) {
                if(!marked[j][i]) {
                    rowSum *= 10;
                    rowSum += arr[j][i];
                } else {
                    sum += rowSum;
                    rowSum = 0;
                }
            }
            sum += rowSum;
        }

        max = Math.max(max, sum);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}