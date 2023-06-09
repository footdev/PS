package BOJ;

import java.io.*;
import java.util.*;



public class HideAndSeek {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, K;
    static int ans;
    static boolean[] checked;


    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        checked = new boolean[100001];
    }

    public static void solve() {
        Queue<Node> q = new LinkedList<>();
        //큐에 시작 숫자인 N을 넣고 방문처리를 해준다.
        q.add(new Node(N, 0));
        checked[q.peek().num] = true;

        //bfs로 돌면서 수를 찾는다.
        while (true) {
            int num = q.peek().num;
            int numCnt = q.poll().cnt;
            if(num == K) {
                ans = numCnt;
                break;
            }

            //인덱스 에러가 나기 때문에 경계값 처리를 해준다.
            if(num-1 >= 0){
                if(!checked[num-1]) {
                    q.add(new Node(num-1, numCnt+1));
                    checked[num-1] = true;
                }
            }
            if(num+1 <= 100000){
                if(!checked[num+1]) {
                    q.add(new Node(num+1, numCnt+1));
                    checked[num+1] = true;
                }
            }

            if(num*2 <= 100000) {
                if(!checked[num*2]) {
                    q.add(new Node(num*2, numCnt+1));
                    checked[num*2] = true;
                }
            }
        }
    }
    static class Node {
        int num;
        int cnt;

        Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}

