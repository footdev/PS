package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CalculateDate {
    static BufferedReader br;
    static StringTokenizer st;
    static int E, S, M;
    static int ans = 1;
    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    public static void solve() {
        int e = 1;
        int s = 1;
        int m = 1;
        while(true) {
            if(e == E && s == S && m == M) break;
            e++;
            s++;
            m++;
            if(e > 15) e = 1;
            if(s > 28) s = 1;
            if(m > 19) m = 1;
            ans++;
        }
    }
}
