import java.io.*;
import java.util.*;

public class BOJ14620 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] board;
    static boolean[][] checked;
    static int N, min;
    static final int[] dx = {0, -1, 1, 0, 0};
    static final int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        init();
        solve(3, 0);
        System.out.println(min);
    }

    static void init() throws IOException {
        min = Integer.MAX_VALUE;
        br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        board = new int[N][N];
        checked = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }
    }

    static void solve(int num, int sum) {
        if(num == 0) {
            min = Math.min(min, sum);
            return;
        }

        //각 자리마다 가능한 자리에 꽃 피우기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(isValid(i, j)) {
                    int getSum = makeFlower(i, j);
                    solve(num-1, sum+getSum);
                    makeFlower(i, j);
                }
            }
        }
    }

    static int makeFlower(int x, int y) {
        int sum = 0;
        for(int i = 0; i < 5; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            sum += board[xx][yy];
            checked[xx][yy] = !checked[xx][yy];
        }

        return sum;
    }

    static boolean isValid(int x, int y) {
        for(int i = 0; i < 5; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            if(xx < 0 || yy < 0 || xx >= N || yy >= N) {
                return false;
            }
            if(checked[xx][yy]) {
                return false;
            }
        }

        return true;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}