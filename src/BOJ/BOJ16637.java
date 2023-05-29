package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16637 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, max;
    static char[] opls;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        dfs(nums[0], 0);
        System.out.println(max);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        max = Integer.MIN_VALUE;
        n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        opls = new char[n/2];
        nums = new int[n/2+1];
        int oplsIdx = 0;
        int numsIdx = 0;
        for(int i = 0; i < n; i++) {
            char tmp = input.charAt(i);
            if(tmp == '+' || tmp == '*' || tmp == '-') {
                opls[oplsIdx++] = tmp;
            } else {
                nums[numsIdx++] = tmp - '0';
            }
        }
    }

    static void dfs(int res, int idx) {
        if(idx == opls.length) {
            max = Math.max(max, res);
            return;
        }

        int next = calc(opls[idx], res, nums[idx+1]);
        dfs(next, idx+1);

        if(idx+1 < opls.length) {
            next = calc(opls[idx], res, calc(opls[idx+1], nums[idx+1], nums[idx+2]));
            dfs(next, idx+2);
        }
    }

    static int calc(char opls, int val1, int val2) {
        if(opls == '+') {
            return val1 + val2;
        }

        if(opls == '-') {
            return val1 - val2;
        }

        if(opls == '*') {
            return val1 * val2;
        }

        return -1;
    }
}