package BOJ;

import java.io.*;
import java.util.*;

class Point_1 {
    int x;
    int y;
    Point_1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class AlgoSpot {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        input();
        System.out.println(bfs(0, 0));
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];
        for(int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                board[i][j] = tmp[j] - '0';
            }
        }

        for(int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
    }

    static int bfs(int x, int y) {
       Deque<Point_1> q = new LinkedList<>();
       q.offer(new Point_1(x, y));
       while(!q.isEmpty()) {
           Point_1 cur = q.poll();
           for(int i = 0; i < 4; i++) {
               int xx = cur.x + dx[i];
               int yy = cur.y + dy[i];

               if(xx < 0 || yy < 0 || xx >= n || yy >= m) continue;


               if (dp[cur.x][cur.y] + board[xx][yy] < dp[xx][yy]) {
                   dp[xx][yy] = dp[cur.x][cur.y] + board[xx][yy];
               }
               else continue;

               if(board[xx][yy] == 0) q.addFirst(new Point_1(xx, yy));
               if(board[xx][yy] == 1) q.addLast(new Point_1(xx, yy));
           }
       }
       return dp[n-1][m-1];
    }
}
