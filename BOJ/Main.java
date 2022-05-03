import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br;
    static StringTokenizer st;
    static int n, res;
    static HashMap<Integer, List<Integer>> studentInfo;
    static int[][] map;
    static int[] order;

    public static void main(String[] args) throws IOException {
        input();
        assign();
        getSum();
        System.out.println(res);
    }

    static void input() throws IOException {
        studentInfo = new HashMap<>();
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        order = new int[n*n];
        for(int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int curNum = Integer.parseInt(st.nextToken());
            order[i] = curNum;
            studentInfo.put(curNum, new ArrayList<>());
            for(int j = 0; j < 4; j++) {
                studentInfo.get(curNum).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    static void assign() {

        for(int key : order) {
            boolean[][] candidates = new boolean[n][n];
            int[][] likeStudentNums = new int[n][n];
            int[][] numOfNulls = new int[n][n];
            int numOfCandidate = 0;
            List<Integer> cur = studentInfo.get(key);

            //1번 조건 탐색
            int max = getFirst(cur, likeStudentNums);
            //1번 조건을 만족하는 칸이 여러개인지 탐색
            numOfCandidate = count(candidates, likeStudentNums, max);
            //1번 조건을 만족하는 칸이 여러 개일 경우
            if(numOfCandidate > 1) {
                //2번조건을 만족하는 칸 구하기
                max = getSecond(numOfNulls, candidates);
                //2번 조건을 만족하는 칸이 여러개인지 구하기
                numOfCandidate = count(candidates, numOfNulls, max);
                //2번 조건을 만족하는 경우가 여러 개일 경우
                if(numOfCandidate > 1) {
                    getThird(candidates);
                }
            }

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    if(candidates[j][k]) {
                        map[j][k] = key;
                    }
                }
            }
        }
    }

    static int getFirst(List<Integer> list, int[][] arr) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) {
                    int cnt = 0;
                    for(int k = 0 ; k < 4; k++) {
                        int xx = i + dx[k];
                        int yy = j + dy[k];

                        if(xx < 0 || yy < 0 || xx >= n || yy >= n) continue;
                        if(list.contains(map[xx][yy])) cnt++;
                    }
                    arr[i][j] = cnt;
                    max = Math.max(max, arr[i][j]);
                }
            }
        }
        return max;
    }

    static int getSecond(int[][] arr, boolean[][] candidates) {
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(candidates[i][j]) {
                    int cnt = 0;
                    for(int k = 0; k < 4; k++) {
                        int xx = i + dx[k];
                        int yy = j + dy[k];

                        if(xx < 0 || yy < 0 || xx >= n || yy >= n) continue;
                        if(map[xx][yy] == 0) cnt++;
                    }
                    arr[i][j] = cnt;
                    max = Math.max(max, cnt);
                }
            }
        }
        return max;
    }

    static void getThird(boolean[][] candidates) {
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!flag && candidates[i][j]) {
                    flag = true;
                    continue;
                }
                if(flag && candidates[i][j]) {
                    candidates[i][j] = false;
                }
            }
        }
    }

    static int count(boolean[][] candidates, int[][] arr, int max) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == max && map[i][j] == 0) {
                    candidates[i][j] = true;
                    cnt++;
                }
                else candidates[i][j] = false;
            }
        }
        return cnt;
    }

    static void getSum() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int score = 0;
                List<Integer> tmp = studentInfo.get(map[i][j]);
                for(int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];

                    if(xx < 0 || yy < 0 || xx >= n || yy >= n) continue;
                    if(tmp.contains(map[xx][yy])) score++;

                }
                if(score == 0) continue;
                if(score == 1) res++;
                if(score == 2) res += 10;
                if(score == 3) res += 100;
                if(score == 4) res += 1000;
            }
        }
    }
}