import java.io.*;
import java.util.*;

public class GoodSequence {
    static BufferedReader br;
    static int N;
    static int[] ans;
    static boolean isFirst = false;

    public static void backtracking(int[] arr, int depth) {
        if(depth > 1) {
            if(!isBadSequence(arr, depth)) return;
        }
        if(depth == N) {
            for(int i = 0; i < N; i++) {
                if(arr[i] > ans[i]) break;
                if(arr[i] < ans[i]) {
                    for(int j = 0; j < N; j++) {
                        ans[j] = arr[j];
                    }
                    isFirst = true;
                    break;
                }
            }
            return;
        }

        for(int i = 1; i <= 3; i++) {
            arr[depth] = i;
            backtracking(arr, depth+1);
            if(isFirst) return;
            arr[depth] = 0;
        }
    }

    public static boolean isBadSequence(int[] arr, int depth) {
        boolean flag = false;
        int pointer = 0;
        int cnt = depth / 2;
        //cnt번의 경우의 수만큼 돈다.
        for(int i = 1; i <= cnt; i++) {
            pointer = (depth-1)-i;
            int idx = depth-1;
            //pointer와 k부터 하나씩 비교한다.
            int k = i;
            while(k != 0) {
                flag = false;
                if(arr[idx] != arr[pointer]) {
                    flag = true;
                    break;
                }
                idx--;
                pointer--;
                k--;
            }
            if(!flag){
                return false;
            }
        }
        return flag;
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = new int[N];
        Arrays.fill(ans, 4);
    }

    public static void main(String[] args) throws IOException{
        input();
        if(N == 1) {
            System.out.println(1);
        }
        else {
            int[] startArr = new int[N];
            Arrays.fill(startArr, 0);
            backtracking(startArr, 0);
            StringBuilder sb = new StringBuilder();
            for(int a : ans) {
                sb.append(a);
            }
            System.out.println(sb);
        }

    }
}
