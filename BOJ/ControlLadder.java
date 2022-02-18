import java.io.*;
import java.util.*;

enum DIRECTION {
        UNDER, LEFT, RIGHT;
    }

public class ControlLadder {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, H;
    static int num;
    static boolean finish = false;
    static DIRECTION[][] ladder;

    public static int solve() {
        for(int i = 0; i <= 3; i++) {
            num = i;
            dfs(0);
            if(finish) {
                return i;
            }
        }
        return -1;
    }

    public static void dfs(int cnt) {
        if(finish) return;
        if(num == cnt) {
            if(check()) finish = true;
            return;
        }

        for(int i = 1; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                if(ladder[i][j] == DIRECTION.UNDER && ladder[i][j+1] == DIRECTION.UNDER) {
                    ladder[i][j] = DIRECTION.RIGHT;
                    ladder[i][j+1] = DIRECTION.LEFT;
                    dfs(cnt+1);
                    ladder[i][j] = DIRECTION.UNDER;
                    ladder[i][j+1] = DIRECTION.UNDER;
                }
            }
        }
    }

    public static boolean check() {
        for(int i = 1; i <= N; i++) {
            int y = i;
            for(int x = 1; x <= H; x++) {
                if(ladder[x][y] == DIRECTION.RIGHT) y++;
                else if(ladder[x][y] == DIRECTION.LEFT) y--;
            }
            if(y != i) return false;
        }
        return true;
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new DIRECTION[H+1][N+1];
        for(DIRECTION[] a : ladder) {
            Arrays.fill(a, DIRECTION.UNDER);
        }
        int t = M;
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = DIRECTION.RIGHT;
            ladder[a][b+1] = DIRECTION.LEFT;
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        System.out.println(solve());
    }
}
