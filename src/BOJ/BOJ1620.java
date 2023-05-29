package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1620 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m;
    static Map<Integer, String> numInfo = new HashMap<>();
    static Map<String, Integer> nameInfo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        for(int i = 0; i < m; i++) {
            solve(br.readLine());
        }
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= n; i++) {
            String name = br.readLine();
            numInfo.put(i, name);
            nameInfo.put(name, i);
        }
    }

    static void solve(String str) {
        char c = str.charAt(0);
        if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
           sb.append(nameInfo.get(str)).append("\n");
        }
        else {
            sb.append(numInfo.get(Integer.parseInt(str))).append("\n");
        }
    }
}