import java.io.*;
import java.util.*;

public class BOJ7662 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int T;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
    }

    static void solve() throws IOException{
        for(int i = 1; i <= T; i++) {
            TreeMap<Long,Integer> q = new TreeMap<>();
            final int N = Integer.parseInt(br.readLine());
            for(int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                long val = Long.parseLong(st.nextToken());

                if(command.equals("I")) push(q, val);
                else if(command.equals("D")) {
                    if(q.size() == 0) continue;
                    if(val == -1) deleteMin(q);
                    else if(val == 1) deleteMax(q);
                }
            }
            if(q.size() == 0) sb.append("EMPTY");
            else {
                sb.append(q.lastKey()).append(" ").append(q.firstKey());
            }
            if(i != T) sb.append("\n");
        }
    }

    static void push(TreeMap<Long,Integer> q, long val) {
        q.put(val, q.getOrDefault(val, 0)+1);
    }

    static void deleteMax(TreeMap<Long,Integer> q) {
        int maxNum = q.get(q.lastKey());
        if(maxNum > 1) {
            q.replace(q.lastKey(), maxNum, maxNum-1);
        }
        else {
            q.remove(q.lastKey());
        }
    }

    static void deleteMin(TreeMap<Long,Integer> q) {
        int minNum = q.get(q.firstKey());
        if(minNum > 1) {
            q.replace(q.firstKey(), minNum, minNum-1);
        }
        else {
            q.remove(q.firstKey());
        }
    }

}