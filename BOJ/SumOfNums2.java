import java.io.*;
import java.util.StringTokenizer;

public class SumOfNums2 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] nums;
    static int n, m, ans;
    public static void main(String[] args) throws IOException{
        input();
        for(int i = 0; i < n; i++) {
            dfs(0, i);
        }
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

    }

    static void dfs(int num, int idx) {
        if(num == m) {
            ans++;
            return;
        }
        if(num > m || idx >= n) return;

        dfs(num+nums[idx], idx+1);
    }
}
