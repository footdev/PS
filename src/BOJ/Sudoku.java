package BOJ;

import java.io.*;
import java.util.*;

class EmptyPoint {
    int x;
    int y;
    EmptyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Sudoku {
    private static final int N = 9;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] board;
    static ArrayList<EmptyPoint> emptyPoints;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        input();
        EmptyPoint first = emptyPoints.get(0);
        backtracking(first.x, first.y, 0);


    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        emptyPoints = new ArrayList<>();
        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0) - '0';
                if(board[i][j] == 0) {
                    emptyPoints.add(new EmptyPoint(i, j));
                }
            }
        }
    }

    static void backtracking(int x, int y, int cntNum) {
        //현재 위치에 넣을 수 있는 값을 찾는다.
        boolean[] visited = isPossible(x, y);
        if(cntNum == emptyPoints.size()){
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(j == N-1) sb.append(board[i][j]);
                    else sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.print(sb);
            System.exit(0);
        }
        //현재 위치에 값을 넣고 다음 0의 위치로 가서 다시 찾는다.
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                board[x][y] = i;
                //종료조건
                if(cntNum == emptyPoints.size()-1) backtracking(-1, -1, cntNum+1);
                backtracking(emptyPoints.get(cntNum+1).x, emptyPoints.get(cntNum+1).y, cntNum+1);
            }
        }
        board[x][y] = 0;

    }

    static boolean[] isPossible(int x, int y) {
        boolean[] visited = new boolean[10];
        //가로, 세로 줄에 중복되는 수 탐색
        for(int i = 0; i < 4; i++) {
            int xx, yy;
            for(int j = 1; j < N; j++) {
                xx = x + dx[i] * j;
                yy = y + dy[i] * j;
                if(xx < 0 || yy < 0 || xx >= N || yy >= N) continue;
                visited[board[xx][yy]] = true;
            }
        }

        //3x3에 중복되는 수 탐색
        int setX = (x / 3) * 3;
        int setY = (y / 3) * 3;

        for(int i = setX; i < setX+3; i++) {
            for(int j = setY; j < setY+3; j++) {
                visited[board[i][j]] = true;
            }
        }

        return visited;
    }
}
