package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2503 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] arr;
    static int[][] states;
    static boolean[] nums;
    static int res;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(res);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        states = new int[N][2];
        nums = new boolean[1000];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            states[i][0] = st.nextToken().charAt(0) - '0';
            states[i][1] = st.nextToken().charAt(0) - '0';
        }
    }

    static void solve() {
        //0 제거, 중복 제거, 입력으로 들어온 값 제거, 입력값들과  s, b이 일치하지 않는 값 제거
        for(int i = 123; i <= 987; i++) {
            String num = String.valueOf(i);
            char[] chars = num.toCharArray();
            if(chars[0] != chars[1] && chars[0] != chars[2] && chars[1] != chars[2]) {
                if(chars[0] != '0' && chars[1] != '0' && chars[2] != '0') {
                    int sameCnt = 0;
                    for(int j = 0; j < N; j++) {
                        String source = String.valueOf(arr[j]);
                        int s = countStrike(num, source);
                        int b = countBall(num, source);
                        if(s == states[j][0] && b == states[j][1]) {
                            sameCnt++;
                        }
                    }

                    if(sameCnt == N) {
                        nums[i] = true;
                    } else {
                        nums[i] = false;
                    }
                }
            }

        }

        //true인 값 세기
        for(int i = 123; i <= 987; i++) {
            if(nums[i]) {
                res++;
            }
        }
    }

    static int countStrike(String target, String source) {
        int cnt = 0;
        for(int i = 0; i < 3; i++) {
           if(target.charAt(i) == source.charAt(i)) {
               cnt++;
           }
        }
        return cnt;
    }

    static int countBall(String target, String source) {
        int cnt = 0;
        for(int i = 0; i < 3; i++) {
            if(target.charAt(i) == source.charAt((i+1)%3)) {
                cnt++;
            }
            if(target.charAt(i) == source.charAt((i+2)%3)) {
                cnt++;
            }
        }
        return cnt;
    }
}