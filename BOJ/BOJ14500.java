import java.io.*;
import java.util.*;

public class BOJ14500 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, max;
    static int[][] board;
    static boolean[][] checked;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                checked[i][j] = true;
                dfs(0, i, j, 0);
                checked[i][j] = false;
                getOther(i, j);
            }
        }
        System.out.println(max);
    }

    static void init() throws IOException {
        max = -1;
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new int[n][m];
        checked = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }
    }

    static void dfs(int depth, int x, int y, int sum) {
        if(depth == 3) {
            max = Math.max(max, sum+board[x][y]);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx < 0 || yy < 0 || xx >= n || yy >= m || checked[xx][yy]) continue;

            checked[xx][yy] = true;
            dfs(depth+1, xx, yy, sum+board[x][y]);
            checked[xx][yy] = false;
        }
    }

    static void getOther(int x, int y) {
        if(x+1 >= 0 && x+1 < n && y+2 >= 0 && y+2 < m) {
            max = Math.max(max, board[x][y] + board[x][y+1] + board[x][y+2] + board[x+1][y+1]);
        }
        if(x+2 >= 0 && x+2 < n && y-1 >= 0 && y-1 < m) {
            max = Math.max(max, board[x][y] + board[x+1][y] + board[x+2][y] + board[x+1][y-1]);
        }
        if(x-1 >= 0 && x-1 < n && y+2 >= 0 && y+2 < m) {
            max = Math.max(max, board[x][y] + board[x][y+1] + board[x][y+2] + board[x-1][y+1]);
        }
        if(x+2 >= 0 && x+2 < n && y+1 >= 0 && y+1 < m) {
            max = Math.max(max, board[x][y] + board[x+1][y] + board[x+2][y] + board[x+1][y+1]);
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}