import java.io.*;
import java.util.*;

public class BOJ1548 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, maxLen;
    static int[] a;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        if(maxLen != Integer.MIN_VALUE) {
            System.out.println(maxLen);
        } else {
            System.out.println(2);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = stoi(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = stoi(st.nextToken());
        }
        Arrays.sort(a);
        maxLen = Integer.MIN_VALUE;
    }

    static void solve() {
        if(n < 3) {
            maxLen = n;
            return;
        }
        for(int i = 0; i < n-3; i++) {
            int sum = a[i] + a[i+1];
            for(int j = n-1; j >= i+2; j--) {
                if(sum > a[j]) {
                    maxLen = Math.max(maxLen, j-i+1);
                    break;
                }
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}