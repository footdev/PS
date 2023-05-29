package BOJ;

import java.io.*;
import java.util.*;

public class SumOfTwoArrays {
    static BufferedReader br;
    static StringTokenizer st;
    static int t, n, m;
    static int[] a, b;
    static long ans;
    static List<Integer> left, right;

    public static void main(String[] args) throws IOException{
        input();
        getSubSequences();
        Collections.sort(left);
        Collections.sort(right);
        getSum();
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        left = new ArrayList<>();
        right = new ArrayList<>();
    }

    static void getSubSequences() {
        for(int i = 0; i < a.length; i++) {
            calculate(a, left, i, a[i]);
        }
        for(int i = 0; i < b.length; i++) {
            calculate(b, right, i, b[i]);
        }
    }

    static void calculate(int[] arr, List<Integer> list, int idx, int sum) {
        list.add(sum);
        //마지막 인덱스면 종료
        if(idx == arr.length-1){
            return;
        }
        calculate(arr, list, idx+1, sum+arr[idx+1]);
    }

    static void getSum() {
        int start = 0;
        int end = right.size() - 1;
        while(start < left.size() && end >= 0) {
            int leftVal = left.get(start);
            int rightVal = right.get(end);
            int sum = leftVal + rightVal;
            if(sum == t) {
                long leftCnt = 0;
                while(start < left.size() && left.get(start) == leftVal) {
                    leftCnt++;
                    start++;
                }
                long rightCnt = 0;
                while(end >= 0 && right.get(end) == rightVal) {
                    rightCnt++;
                    end--;
                }
                ans += leftCnt * rightCnt;
            }
            if(sum < t) start++;
            if(sum > t) end--;
        }
    }
}
