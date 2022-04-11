import java.io.*;
import java.util.*;

public class SumOfSubSequence2 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, s;
    static int[] arr;
    static List<Long> left;
    static List<Long> right;
    static long ans;

    public static void main(String[] args) throws IOException{
        input();
        dfs(left, arr.length/2, 0, 0);
        dfs(right, n, arr.length/2, 0);
        Collections.sort(left);
        Collections.sort(right);
        System.out.println(getCnt());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        left = new ArrayList<>();
        right = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
    }

    static void dfs(List<Long> tmpArr, int n, int idx, long sum) {
        if(idx == n) {
            tmpArr.add(sum);
            return;
        }

        dfs(tmpArr, n, idx+1, sum);
        dfs(tmpArr, n, idx+1, sum+arr[idx]);
    }

    static long getCnt() {
        int start = 0;
        int end = right.size()-1;

        while(true) {
            if(start >= left.size() || end < 0) break;
            long leftVal = left.get(start);
            long rightVal = right.get(end);
            long sum = leftVal + rightVal;

            if(sum == s) {
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

            if(sum < s) start++;
            if(sum > s) end--;
        }

        return s == 0 ? ans-1 : ans;
    }
}
