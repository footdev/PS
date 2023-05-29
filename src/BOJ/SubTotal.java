package BOJ;

import java.io.*;
import java.util.*;

public class SubTotal {
    static BufferedReader br;
    static StringTokenizer st;
    static int[] nums;
    static int n, s, ans;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        ans = Integer.MAX_VALUE;
    }

    static void solve() {
        int start = 0;
        int end = 0;
        int sum = nums[start];

        while(end < n) {
           if(sum < s) {
               end++;
               //처음과 끝까지 모두 더했어도 작았다는 뜻이기 때문에 구할 수 없음. 따라서 0 출력
               if(end == n && start == 0) {
                   ans = 0;
                   return;
               }

               if(end < n) sum += nums[end];
               continue;
           }
           if(sum >= s) {
               ans = Math.min(ans, end-start+1);
               sum = sum-nums[start];
               start++;
               continue;
           }
        }
    }
}
