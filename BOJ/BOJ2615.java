import java.io.*;
import java.util.*;

public class BOJ2615 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0, -1, 1, 1, -1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static StringBuilder ret;

    public static void main(String[] args) throws IOException {
        init();
        getWhoWin();
        System.out.println(ret);
    }

    static void init() throws IOException {
        ret = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[19][19];
        for(int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void getWhoWin() {
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                //0이면 그냥 통과
                if(board[i][j] == 0) continue;

                // 8방향에 대해서 갈 수 있는지 검사
                boolean[] dirValid = new boolean[8];
                int standard = board[i][j];

                for(int k = 0; k < 8; k++) {
                    int xx = dx[k] + i;
                    int yy = dy[k] + j;

                    if(xx < 0 || yy < 0 || xx >= 19 || yy >= 19) continue;
                    if(board[xx][yy] != standard) continue;
                    dirValid[k] = true;
                }

                //세로, 가로, 왼 대각선, 오 대각선 검사
                for(int m = 0; m < 8; m += 2) {
                    int sum = 1;
                    //두 쪽 다 돌이 있는 경우
                    if(dirValid[m] && dirValid[m+1]) {
                        int[] first = getNum(m, i, j, standard);
                        int[] second = getNum(m+1, i, j, standard);
                        sum += first[0] + second[0];
                        if(sum == 5) {
                            ret.append(standard+"\n");
                            //세로가 아닌경우
                            if(first[2] < second[2]) {
                                ret.append(first[1] + " " + first[2]);
                              //세로인 경우
                            } else if(first[2] == second[2]) {
                                if(first[1] < second[1]) {
                                    ret.append(first[1] + " " + first[2]);
                                } else {
                                    ret.append(second[1] + " " + second[2]);
                                }
                            }
                            return;
                        }
                     //한쪽만 돌이 있는 경우
                    } else if(dirValid[m]) {
                        int[] a = getNum(m, i, j, standard);
                        sum += a[0];
                        if(sum == 5) {
                            ret.append(standard + "\n");
                            ret.append(a[1] + " " + a[2]);
                            return;
                        }
                    } else {
                        int[] a = getNum(m+1, i, j, standard);
                        sum += a[0];
                        if(sum == 5) {
                            ret.append(standard + "\n");
                            ret.append(a[1] + " " + a[2]);
                            return;
                        }
                    }
                }
            }
        }
        ret.append(0);
    }

    static int[] getNum(int dir, int x, int y, int standard) {
        int[] ret = new int[3];
        ret[0] = 0;
        ret[1] = x;
        ret[2] = y;

        while(true) {
            x = dx[dir] + x;
            y = dy[dir] + y;

            if(x < 0 || y < 0 || x >= 19 || y >= 19) break;
            if(board[x][y] != standard) break;

            if(y < ret[2]) {
                ret[1] = x;
                ret[2] = y;
            } else if(y == ret[2]) {
                if(x < ret[1]) {
                    ret[1] = x;
                    ret[2] = y;
                }
            }
            ret[0]++;
        }
        ret[1]++;
        ret[2]++;
        return ret;
    }
}