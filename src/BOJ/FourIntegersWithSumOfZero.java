package BOJ;

import java.io.*;
import java.util.*;

public class FourIntegersWithSumOfZero {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static long ans;
    static int[] a, b, c, d;
    static long[] left, right;

    public static void main(String[] args) throws IOException{
        input();
        calculate();
        Arrays.sort(left);
        Arrays.sort(right);
        getSum();
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }
        left = new long[n*n];
        right = new long[n*n];
    }

    static void calculate() {
        int cnt = 0;
       for(int i = 0; i < n; i++) {
           for(int j = 0; j < n; j++) {
               left[cnt] = a[i] + b[j];
               right[cnt] = c[i] + d[j];
               cnt++;
           }
       }
    }

    static void getSum() {
        int start = 0;
        int end = right.length - 1;

        while(start < left.length && end >= 0) {
            long leftVal = left[start];
            long rightVal = right[end];
            long sum = leftVal + rightVal;
            if(sum == 0) {
                long leftCnt = 0;
                while(start < left.length && left[start] == leftVal) {
                    leftCnt++;
                    start++;
                }
                long rightCnt = 0;
                while(end >= 0 && right[end] == rightVal) {
                    rightCnt++;
                    end--;
                }
                ans += leftCnt * rightCnt;
            }
            if(sum < 0) start++;
            if(sum > 0) end--;
        }
    }
}
