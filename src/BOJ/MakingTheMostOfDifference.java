package BOJ;

import java.io.*;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakingTheMostOfDifference {
    static int N;
    static int ans = 0;
    static StringTokenizer st;

    public static void recur(int[] A, int depth, int n) {
        //종료조건
        if(depth == N) {
            ans = Math.max(ans, calculate(A, n));
            return;
        }
        //재귀호출
        for(int i = depth; i < N; i++) {
            swap(A, i ,depth);
            recur(A, depth+1, n);
            swap(A, i, depth);
        }
    }

    public static int calculate(int[] arr, int n) {
        int sum = 0;
        for(int i = 0; i < n-1; i++) {
            sum += Math.abs(arr[i] - arr[i+1]);
        }
        return sum;
    }

    public static void swap(int[] A, int a, int b) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        recur(A, 0, N);
        System.out.println(ans);
    }
}
