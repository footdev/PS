package BOJ;

import java.io.*;

public class BOJ9663 {
    static int[] dx = {-1, -1};
    static int[] dy = {-1, 1};
    static BufferedReader br;
    static int n, ans;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < n; i++) {
            dfs(0, i);
        }
        System.out.println(ans);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];
    }

    static void dfs(int x, int y) {
        //목적지인가? : n개까지 말을 놓았을 때
        if(x == n-1) {
            ans++;
            return;
        }

        board[x][y] = true;

        //연결된 곳 순회 : x행의 열
        for (int i = 0; i < n; i++) {
            //갈 수 있는가? : 서로 공격할 수 없는 위치
            if(check(x+1, i)) {
                dfs(x+1, i);
            }
        }

        //말 빼기
        board[x][y] = false;
    }

    static boolean check(int x, int y) {
        //세로 줄에 퀸이 있는지 확인
        for (int i = 0; i < n; i++) {
            if(board[i][y]) {
                return false;
            }
        }

        //왼쪽 위, 오른쪽 위 대각선만 알면 된다. (현재 줄에서 놓을 수 있는지만 보면 되기 때문에)
        int xx, yy;
        for (int i = 0; i < 2; i++) {
            xx = dx[i] + x;
            yy = dy[i] + y;
            while(xx >= 0 && yy >= 0 && xx < n && yy < n) {
                if(board[xx][yy]) {
                    return false;
                }
                xx += dx[i];
                yy += dy[i];
            }
        }

        return true;
    }
}