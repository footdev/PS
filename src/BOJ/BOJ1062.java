package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1062 {
    static BufferedReader br;
    static StringTokenizer st;
    static String[] input;
    static int n, k, max;
    static boolean[] alphabet;

    public static void main(String[] args) throws IOException {
        init();
        if(k < 5) {
            System.out.println(0);
        } else {
            solve(0, 'a');
            System.out.println(max);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        input = new String[n];
        alphabet = new boolean['z'+1];
        for(int i = 0; i < n; i++) {
            input[i] = br.readLine();
        }
        for(int i = 'a'; i <= 'z'; i++) {
            if((char)i == 'a' || (char)i == 'c' || (char)i == 'i' || (char)i == 'n' || (char)i == 't') {
                alphabet[i] = true;
            }
        }
    }

    static void solve(int depth, char start) {
        /*
        1. 체크인
        2. 목적지인가?
        3. 연결된 곳을 순회
            4. 갈 수 있는가?
                5. 간다.
        6. 체크아웃
        */
        if(depth == k-5) {
            int cnt = 0;

            for (int i = 0; i < n; i++) {
                boolean flag = true;
                for (int j = 4; j < input[i].length()-4; j++) {
                    char c = input[i].charAt(j);
                    if(!alphabet[c]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }
        if(start == 'z') return;
        for(char c = start; c <= 'z'; c++) {
            if(alphabet[c]) continue;
            alphabet[c] = true;
            solve(depth+1, c);
            alphabet[c] = false;
        }
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}