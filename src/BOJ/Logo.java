package BOJ;

import java.io.*;
import java.util.*;
class Rect {
    int x1;
    int y1;
    int x2;
    int y2;
    Rect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Logo {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int ans;
    static boolean[] checked;
    static Rect[] rectArr;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException{
        input();
        bfs();
        System.out.println(ans-1);
    }

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        ans = 0;
        n = Integer.parseInt(br.readLine());
        rectArr = new Rect[n+1];
        checked = new boolean[n+1];
        rectArr[0] = new Rect(0, 0, 0, 0);
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            rectArr[i] = new Rect(x1, y1, x2, y2);
        }
    }

    public static void bfs() {
        q = new LinkedList<>();

        for(int i = 0; i <= n; i++) {
            if(checked[i]) continue;
            checked[i] = true;
            q.add(i);
            while(!q.isEmpty()) {
                int cur = q.poll();
                for(int j = 0; j <= n; j++) {
                    //현재와 같거나 접점이 없거나 이미 방문한 경우는 제외
                    if(cur == j || !checkConnected(cur, j) || checked[j]) continue;
                    checked[j] = true;
                    q.add(j);
                }
            }
            ans++;
        }
    }

    public static boolean checkConnected(int cur, int next) {
        Rect c = rectArr[cur];
        Rect n = rectArr[next];
        if((c.x1 < n.x1 && n.x2 < c.x2 && c.y1 < n.y1 && n.y2 < c.y2)
                || (c.x1 > n.x1 && n.x2 > c.x2 && c.y1 > n.y1 && n.y2 > c.y2)
                || (c.x2 < n.x1 || n.x2 < c.x1 || c.y2 < n.y1 || n.y2 < c.y1)) {
            return false;
        }
        return true;
    }
}
