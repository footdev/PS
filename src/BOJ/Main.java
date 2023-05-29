package BOJ;

import java.io.*;
import java.util.*;


class Top {
    int loc;
    int len;
    Top(int loc, int len) {
        this.loc = loc;
        this.len = len;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] tops;
    static Stack<Top> s = new Stack<>();
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(ans.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tops = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        ans.append("0 ");
        s.push(new Top(1, tops[0]));
        for(int i = 1; i < n; i++) {
            while(true) {
                if(s.isEmpty()) {
                    ans.append("0 ");
                    s.push(new Top(i+1, tops[i]));
                    break;
                }
                if(tops[i] < s.peek().len) {
                    ans.append(s.peek().loc + " ");
                    s.push(new Top(i+1, tops[i]));
                    break;
                }
                else {
                    s.pop();
                }
            }
        }
    }
}