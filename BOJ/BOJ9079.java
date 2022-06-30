import java.io.*;
import java.util.*;

class Info {
    StringBuilder state;
    int cnt;
    Info(StringBuilder sb, int cnt) {
        this.state = sb;
        this.cnt = cnt;
    }
}

public class BOJ9079 {
    static final int N = 3;
    static BufferedReader br;
    static StringTokenizer st;
    static int T;
    static Set<String> set;
    static StringBuilder ret;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(ret.toString());
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ret = new StringBuilder();
        T = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException{
        while(T-- > 0) {
            StringBuilder sb = new StringBuilder();
            set = new HashSet<>();
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    sb.append(st.nextToken());
                }
            }
            int res = bfs(sb);
            if(res == -1) {
                ret.append("-1"+"\n");
            } else {
                ret.append(res+"\n");
            }
        }
    }

    static int bfs(StringBuilder start) {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(start, 0));
        while(!q.isEmpty()) {
            Info cur = q.poll();
            if(cur.state.toString().equals("HHHHHHHHH") || cur.state.toString().equals("TTTTTTTTT")) {
                return cur.cnt;
            }

            for(int i = 0; i < 8; i++) {
                if(i < 3) {
                    turnWidth(cur.state, i);
                } else if(i < 6) {
                    turnHeight(cur.state, i);
                } else {
                    turnDiaglnal(cur.state, i);
                }
                if(!set.contains(cur.state.toString())) {
                    q.add(new Info(new StringBuilder(cur.state.toString()), cur.cnt+1));
                    set.add(cur.state.toString());
                }

                if(i < 3) {
                    turnWidth(cur.state, i);
                } else if(i < 6) {
                    turnHeight(cur.state, i);
                } else {
                    turnDiaglnal(cur.state, i);
                }
            }
        }
        return -1;
    }

    static void swap(StringBuilder sb, int idx) {
        if(sb.charAt(idx) == 'H') {
            sb.setCharAt(idx, 'T');
        } else {
            sb.setCharAt(idx, 'H');
        }
    }

    static void turnWidth(StringBuilder s, int idx) {
        if(idx == 0) {
            swap(s,0);
           swap(s,1);
           swap(s,2);
           return;
        }
        if(idx == 1) {
            swap(s,3);
            swap(s,4);
            swap(s,5);
            return;
        }
        if(idx == 2) {
            swap(s,6);
            swap(s,7);
            swap(s,8);
            return;
        }
    }

    static void turnHeight(StringBuilder s, int idx) {
        if(idx == 3) {
            swap(s,0);
            swap(s,3);
            swap(s,6);
            return;
        }
        if(idx == 4) {
            swap(s,1);
            swap(s,4);
            swap(s,7);
            return;
        }
        if(idx == 5) {
            swap(s,2);
            swap(s,5);
            swap(s,8);
            return;
        }
    }

    static void turnDiaglnal(StringBuilder s, int idx) {
        if(idx == 6) {
            swap(s,0);
            swap(s,4);
            swap(s,8);
        } else {
            swap(s, 2);
            swap(s, 4);
            swap(s, 6);
        }
    }
}