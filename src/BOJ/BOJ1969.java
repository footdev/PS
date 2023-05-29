package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1969 {
    static BufferedReader br;
    static StringTokenizer st;
    static String[] arr;
    static boolean[] checked;
    static int N, L;
    static StringBuilder minStr;
    static int min;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(minStr);
        System.out.println(min);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new String[N];
        minStr = new StringBuilder();
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
    }

    static void solve() {
       for(int i = 0; i < L; i++) {
           Map<Character, Integer> map = new TreeMap<>();
           Set<Character> set = new TreeSet<>();
           int max = Integer.MIN_VALUE;
           // 1. 각 i번째 자리 문자를 map에 갱신한다.
           for(int j = 0; j < N; j++) {
               char c = arr[j].charAt(i);
               map.put(c, map.getOrDefault(c, 0)+1);
           }
           // 2. 최고값을 탐색한다.
           for(Character key : map.keySet()) {
               max = Math.max(map.get(key), max);
           }
           //3. 최고값을 가진 key들을 TreeSet에 넣는다.
           for(Character key : map.keySet()) {
               if(max == map.get(key)) {
                   set.add(key);
               }
           }
           // 4. TreeSet에서 하나를 꺼내서 append 한다.
           Iterator<Character> iterator = set.iterator();
           minStr.append(iterator.next());
           min += N - max;
       }
    }
}