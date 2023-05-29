package BOJ;

import java.io.*;
import java.util.*;

public class BOJ20056 {
    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] o = {0, 2, 4, 6};
    static final int[] x = {1, 3, 5, 7};
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static ArrayList<FireBall> list;
    static ArrayList<FireBall>[][] fireBalls;
    static int n, m, k, tmp = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //input
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());
        fireBalls = new ArrayList[n][n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fireBalls[i][j] = new ArrayList<>();
            }
        }

        //초기 파이어볼 지정
       for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken()) - 1;
            int c = stoi(st.nextToken()) - 1;
            int m = stoi(st.nextToken());
            int s = stoi(st.nextToken());
            int d = stoi(st.nextToken());

            list.add(new FireBall(r, c, m, s, d));
            tmp += m;
        }

        //logic
        while(k-- > 0) {
            move();
            divide();
        }

        //output
        bw.write(tmp + "\n");
        bw.flush();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static void move() {
        for(FireBall cur : list) {
            //좌표 모듈러 연산
            int nr = ((cur.s % n) * dx[cur.d] + n + cur.r) % n;
            int nc = ((cur.s % n) * dy[cur.d] + n + cur.c) % n;
            cur.r = nr;
            cur.c = nc;
            fireBalls[nr][nc].add(cur);
        }
    }

    static void divide() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //2개 이상의 파이어볼이 있을 경우
                if(fireBalls[i][j].size() == 1) {
                    fireBalls[i][j].clear();
                }
                if(fireBalls[i][j].size() < 2) {
                    continue;
                }

                //파이어볼 하나로 합치기
                int mSum = 0;
                int sSum = 0;
                int even = 0;
                int odd = 0;

                for(FireBall cur : fireBalls[i][j]) {
                    sSum += cur.s;
                    mSum += cur.m;

                    if(cur.d % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }

                    //파이어볼을 합쳐야 하니 리스트에서 하나씩 삭제해준다.
                    list.remove(cur);
                }
                tmp -= mSum;
                int newM = mSum / 5;
                int newS = sSum / fireBalls[i][j].size();
                fireBalls[i][j].clear();

                //파이어볼을 나눈 후 질량이 0이라면, 파이어볼 소멸
                if(newM < 1) {
                    continue;
                }

                //파이어볼 4개로 나누기
                for (int r = 0; r < 4; r++) {
                    int newD = odd == 0 || even == 0 ? o[r] : x[r];
                    list.add(new FireBall(i, j, newM ,newS, newD));
                    tmp += newM;
                }
            }
        }
    }
}

class FireBall {
    int r;
    int c;
    int m;
    int s;
    int d;

    public FireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
