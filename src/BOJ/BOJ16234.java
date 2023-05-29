package BOJ;

import java.io.*;
import java.util.*;

public class BOJ16234 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, low, upper, ansCnt;
    static int[][] countries, populations, united;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //n, low, upper입력
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        low = stoi(st.nextToken());
        upper = stoi(st.nextToken());
        countries = new int[n][n];
        populations = new int[n][n];
        united = new int[1251][];
        //n개의 줄에 각 나라의 인구수 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                populations[i][j] = stoi(st.nextToken());
                countries[i][j] = 0;
            }
        }

        //인구 이동 플래그
        boolean flag = true;
        while(flag) {
            flag = false;
            //매 인구이동 마다 정보 저장 테이블들을 새로 업데이트 해주어야 함.
            int lavel = 1;
            for(int[] arr : countries) {
                Arrays.fill(arr, 0);
            }
            for (int i = 0; i < 1251; i++) {
                united[i] = null;
            }

            //연합 생성 로직
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int[] ret = null;
                    //i, j에 해당하는 나라와 국경선을 공유하는 나라 순회
                    //방문하지 않았는가? : 라벨링 안된 나라
                    if(countries[i][j] == 0) {
                        //연합 생성
                        ret = bfs(i, j, lavel);
                    }
                    //하나라도 국경을 해제 했는가?
                    if(ret != null) {
                        //플래그 변경
                        flag = true;
                        //캐시 테이블에 저장
                        united[lavel++] = ret;
                    }
                }
            }

            //인구 이동 로직
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //연합이 있는가?
                    if(countries[i][j] != 0) {
                        //인구 수 변경
                        int idx = countries[i][j];
                        populations[i][j] = united[idx][1] / united[idx][0];
                    }
                }
            }
            //국경선이 하나라도 개방 되었는가?
            if(flag) {
                //일 수 증가
                ansCnt++;
            }
        }

        //답 출력
        System.out.println(ansCnt);
    }

    static int[] bfs(int x, int y, int lavel) {
        //연합을 생성하고 나오는 총 인구 수, 나라의 개수 return 해야함
        int[] ret = new int[2];
        int cnt = 0;
        int total = 0;
        //큐 생성
        Queue<Pair> q = new LinkedList<>();
        //시작 노드 삽입
        q.offer(new Pair(x, y));
        //큐가 빌 때 까지 진행
        while(!q.isEmpty()) {
            //큐에서 꺼냄
            Pair cur = q.poll();
            int curr = populations[cur.x][cur.y];
            //원소와 인접한 노드 순회
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + cur.x;
                int nextY = dy[i] + cur.y;

                //갈 수 있는가? : 맵을 벗어나지 않는가?
                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) continue;
                //요건을 충족하는가? : 갭이 조건을 충족하면서 라벨링 되지 않았는가?
                int next = populations[nextX][nextY];
                int gap = Math.abs(curr - next);
                if(gap >= low && gap <= upper && countries[nextX][nextY] == 0) {
                    //조건에 충족한다면 큐에 삽입
                    q.offer(new Pair(nextX, nextY));
                    //라벨링 및 나라의 개수, 총 인구 수 update
                    countries[nextX][nextY] = lavel;
                    cnt++;
                    total += populations[nextX][nextY];
                }
            }
        }

        //연합이 생성 되었는가? : 개방된 나라가 1개 이상
        if(cnt > 0) {
            //결과 return [0] : 나라 개수, [1] : 총 인구 수
            ret[0] = cnt;
            ret[1] = total;
            return ret;
        }
        //아니라면 null return
        return null;
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}