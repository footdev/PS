package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1339 {
    static BufferedReader br;
    static int n, maxLen;
    static long max;
    static String[] words;
    static long[] alphabetWeight;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(max);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        words = new String[n];
        maxLen = -1;
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            maxLen = Math.max(maxLen, words[i].length());
        }
        max = 0;
        alphabetWeight = new long[26];
    }

    static void solve() {
        //각 단어별 자릿수에 따라 가중치 부여 (ex. GCF = 100G, 10C, 1F)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int idx = words[i].charAt(j) - 'A';
                alphabetWeight[idx] += Math.pow(10, words[i].length() - j - 1);
            }
        }
        //높은 합을 가진 알파벳부터 높은값 부여 : pq 사용해서 높은값 빼기
        PriorityQueue<Long> pq = new PriorityQueue(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            pq.add(alphabetWeight[i]);
        }

        for (int i = 9; i >= 0; i--) {
            max += pq.poll() * i;
        }

    }
}
