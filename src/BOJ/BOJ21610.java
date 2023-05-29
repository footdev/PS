package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21610 {
    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int DIRECTION_NUM = 8;
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, d, s, sum;
    static int[][] board;
    static boolean[][] checked;
    static Queue<Pair> clouds;

    public static void main(String[] args) throws IOException {
        //input
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        board = new int[n][n];
        checked = new boolean[n][n];
        clouds = new LinkedList<>();
        sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = stoi(st.nextToken());
                sum += board[i][j];
            }
        }

        //초기 구름 생성
        clouds.offer(new Pair(n-1, 0));
        clouds.offer(new Pair(n-1, 1));
        clouds.offer(new Pair(n-2, 0));
        clouds.offer(new Pair(n-2, 1));

        //로직
        while(m-- > 0) {
            //check배열 초기화
            for(boolean[] arr : checked) {
                Arrays.fill(arr, false);
            }
            st = new StringTokenizer(br.readLine());
            d = stoi(st.nextToken()) - 1;
            s = stoi(st.nextToken());

            //구름 움직여서 물 1 증가
            for(Pair cloud : clouds) {
                cloud.x = ((s % n) * dx[d] + cloud.x + n) % n;
                cloud.y = ((s % n) * dy[d] + cloud.y + n) % n;

                board[cloud.x][cloud.y]++;
                sum++;
            }
            /*
            2에서 물이 증가한 칸 (r,c)에 물복사 버그 마법 시전 및 구름제거
            대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수 반큼 (r,c)에 있는 물 양 증가
             */
            while(!clouds.isEmpty()) {
                Pair cloud = clouds.poll();
                //움직인 구름 체크
                checked[cloud.x][cloud.y] = true;
                int cnt = 0;
                for (int i = 1; i < DIRECTION_NUM; i += 2) {
                    int x = cloud.x + dx[i];
                    int y = cloud.y + dy[i];

                    //보드판의 경계를 넘지않는가?
                    if(x >= 0 && y >= 0 && x < n && y < n) {
                        //대각선에 물이 있는가?
                        if(board[x][y] > 0) {
                            //cnt 증가
                            cnt++;
                        }
                    }
                }
                //cnt만큼 물의 양 증가
                board[cloud.x][cloud.y] += cnt;
                sum += cnt;
            }
            //바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름 생성, 물의 양 2 하락, 3에서 구름이 사라진 칸이 아니어야 함.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //3단계에서 사라진 구름 위치가 아니고 물의 양이 2 이상인가?
                    if(!checked[i][j] && board[i][j] >= 2) {
                        //물의 양 2 하락
                        board[i][j] -= 2;
                        sum -= 2;
                        //구름 생성
                        clouds.offer(new Pair(i, j));
                    }
                }
            }
       }

        //정답 출력
        System.out.println(sum);
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