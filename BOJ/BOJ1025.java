import java.io.*;
import java.util.*;

public class BOJ1025 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, max;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(max == Integer.MIN_VALUE ? -1 : max);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new int[n][m];
        max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j)-'0';
            }
        }
    }

    static void solve() {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    for(int x = -n; x < n; x++) {
                        for(int y = -m; y < m; y++) {
                            if(x == 0 && y == 0) continue;
                            StringBuilder val = new StringBuilder();
                            int a = i;
                            int b = j;
                            while(a >= 0 && b >= 0 && a < n && b < m) {
                                if(isSquareNum(board[a][b])) {
                                    max = Math.max(max, board[a][b]);
                                }
                                val.append(board[a][b]);
                                if(isSquareNum(stoi(val.toString()))) {
                                    max = Math.max(max, stoi(val.toString()));
                                }
                                a = a + x;
                                b = b + y;
                            }
                        }
                    }
                }
            }
    }

    static boolean isSquareNum(int n) {
        int root = (int)Math.sqrt(n);
        if(root*root == n) {
            return true;
        }
        return false;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}