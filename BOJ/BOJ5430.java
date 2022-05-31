import java.io.*;
import java.util.*;

public class BOJ5430 {
    static BufferedReader br;
    static int t;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        input();
        for(int i = 0; i < t; i++) {
            Deque<Integer> q = new LinkedList<>();
            char[] command = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());

            //문자열 가공 과정
            String in = br.readLine();
            in = in.substring(1, in.length()-1);

            if(n == 1) q.add(Integer.parseInt(in));
            else if(n > 1){
                String[] tmp = in.split(",");
                for(String str : tmp) {
                    q.add(Integer.parseInt(str));
                }
            }
            solve(command, n, q);
        }
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
    }

    static void solve(char[] command, int n, Deque<Integer> q) {
        boolean flag = true;
        for(char c : command) {
            if(c == 'R') flag = !flag;
            if(c == 'D') {
                if(q.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }
                if(flag) q.pollFirst();
                else q.pollLast();
            }
        }
        sb.append("[");
        while(!q.isEmpty()) {
            if(flag) {
                sb.append(q.poll());
            }
            else {
                sb.append(q.pollLast());
            }
            if(!q.isEmpty()) sb.append(",");
        }
        sb.append("]").append("\n");
    }

}