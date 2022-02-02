import java.io.*;
import java.util.*;

public class SumOfSubSequences {
    static int N;
    static int S;
    static int ans = 0;
    static int[] arr;
    static StringTokenizer st;

    public static void backtracking(int idx, int sum) {
       if(idx == N) {
           if(sum == S) {
               ans++;
           }
           return;
       }
       backtracking(idx+1, sum);
       backtracking(idx+1, sum+arr[idx]);
    }
    public static void main(String[] args) throws IOException{
        //입출력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0);
        if(S == 0) ans--;
        System.out.println(ans);
    }
}
