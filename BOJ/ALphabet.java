import java.io.*;
import java.util.*;

public class ALphabet {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] board;
    static int ans;
    static int r, c;

    public static void main(String[] args) throws IOException {
        input();
        boolean[] arr = new boolean['Z'+1];
        dfs(0, 0, 0, arr);
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        for(int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }
    }

    static void dfs(int x, int y,int cnt, boolean[] arr) {
        //중복된 문자를 만난다면 현재 최댓값과 비교해서 값을 업데이트 한후 리턴한다.
        if(arr[board[x][y]]){
            ans = Math.max(ans, cnt);
            return;
        }

        arr[board[x][y]] = true;

        //상, 하, 좌, 우로 candidate들을 선별한다.
        for(int i = 0; i < 4; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            if(xx < 0 || yy < 0 || xx >= r || yy >= c) continue;
            //중복 문자의 상태를 저장하는 배열을 깊은복사 해준다.
            boolean[] tmpArr = arr.clone();
            dfs(xx, yy, cnt+1, tmpArr);
        }
    }
}
