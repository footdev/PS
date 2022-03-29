import java.io.*;
import java.util.*;

public class Lotto {
    static final int N = 6;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int k;
    static int[] lottoArr;

    public static void main(String[] args) throws IOException{
        solve();
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }

    static void solve() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;
            lottoArr = new int[k];
            for(int i = 0; i < k; i++) {
                lottoArr[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 6, new boolean[k]);
            sb.append('\n');
        }
    }

    static void dfs(int start, int r, boolean[] checked) {
        if(r == 0) {
            for(int i = 0; i < checked.length; i++) {
                if(checked[i]) {
                    sb.append(lottoArr[i]).append(' ');
                }
            }
            sb.append('\n');
            return;
        }

        for(int i = start; i < k; i++) {
            checked[i] = true;
            dfs(i+1, r-1, checked);
            checked[i] = false;
        }
    }
}

