package BOJ;

import java.io.*;
import java.util.*;

public class Puzzle {
    static BufferedReader br;
    static StringTokenizer st;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static String input = "";
    static final String ans = "123456780";
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                int n = Integer.parseInt(st.nextToken());
                input += n;
            }
        }
        map.put(input, 0);
    }

    static void solve() {
        Queue<String> q = new LinkedList<>();
        q.add(input);
        while(!q.isEmpty()) {
            String cur = q.poll();
            int cnt = map.get(cur);
            int loc = cur.indexOf("0");
            int x = loc / 3;
            int y = loc % 3;

            if(cur.equals(ans)) {
                System.out.println(cnt);
                break;
            }
            for(int i = 0; i < 4; i++) {
                int xx = dx[i] + x;
                int yy = dy[i] + y;

                if(xx < 0 || xx > 2 || yy < 0 || yy > 2) continue;

                int replaceIdx = xx*3+yy;
                //0과 스위칭 할 값
                char tmp = cur.charAt(replaceIdx);
                String next = cur.replace(tmp, 'c');
                next = next.replace('0', tmp);
                next = next.replace('c', '0');

                if(!map.containsKey(next)) {
                    q.add(next);
                    map.put(next, cnt+1);
                }
            }
        }
        if(!map.containsKey(ans)) System.out.println(-1);
    }

}
