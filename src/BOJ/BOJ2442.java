package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2442 {
    static BufferedReader br;
    static StringTokenizer st;
    static boolean[][] check;
    static int[] result;
    static boolean[] visited;
    static int N, M, cnt;

    public static void main(String[] args) throws IOException {
        init();
        comb(1, 0);
        System.out.println(cnt);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        check = new boolean[N+1][N+1];

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            check[a][b] = true;
            check[b][a] = true;
        }

        result = new int[3];
        visited = new boolean[N+1];
    }

    static void comb(int idx, int depth) {
        if(depth == 3)  {
            if(isValid()){
                cnt++;
            }
            return;
        }

        for(int i = idx; i <= N; i++) {
            if(visited[i])continue;
            visited[i] = true;
            result[depth] = i;
            comb(i, depth+1);
            visited[i] = false;
        }
    }
    static boolean isValid() {
        if(!check[result[0]][result[1]] && !check[result[0]][result[2]] && !check[result[1]][result[2]]) {
            return true;
        }
        return false;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}