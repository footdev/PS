import java.io.*;
import java.util.*;
class Floor {
    int num;
    int cnt;
    Floor(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}
public class StartLink {
    static BufferedReader br;
    static StringTokenizer st;
    static int f, s, g, u, d;
    static Floor ans;
    static boolean[] checked;

    public static void main(String[] args) throws IOException{
        input();
        bfs();
        if(ans == null) System.out.println("use the stairs");
        else System.out.println(ans.cnt);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        checked = new boolean[f+u+1];
    }

    static void bfs() {
        //올라가거나 내려갈 수 없을 때
        if((u == 0 && s < g) || (d == 0 && s > g)) return;

        //갈 수 있다고 판단 되면 bfs로 몇번안에 갈 수 있는지 확인
        Queue<Floor> q = new LinkedList<>();
        q.add(new Floor(s, 0));
        checked[q.peek().num] = true;
        while(!q.isEmpty()) {
            Floor cur = q.poll();
            if(cur.num == g) {
                ans = cur;
                break;
            }
            if(cur.num + u <= f && !checked[cur.num+u]) {
                q.add(new Floor(cur.num+u, cur.cnt+1));
                checked[cur.num+u] = true;
            }
            if(cur.num - d > 0 && !checked[cur.num-d]) {
                q.add(new Floor(cur.num-d, cur.cnt+1));
                checked[cur.num-d] = true;
            }
        }
        return;
    }
}
