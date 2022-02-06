import java.io.*;
import java.util.StringTokenizer;

public class Blackjack {
    static final int R = 3;
    static int N;
    static int M;
    static int ans = 0;

    public static void backtracking(int[] arr, int idx, int depth, int sum) {
        //백트래킹 조건
        if(sum > M || idx > N) {
            return;
        }
        //종료 조건
        if(depth == R) {
            ans = Math.max(ans, sum);
            return;
        }
        //재귀조건
        for(int i = idx; i < N; i++) {
            backtracking(arr, i+1, depth+1, sum+arr[i]);
        }
    }

       public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(arr, 0, 0, 0);
        System.out.println(ans);

    }
}
