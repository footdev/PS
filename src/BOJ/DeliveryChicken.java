package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class DeliveryChicken {
    static int N;
    static int M;
    static int[][] map;
    static boolean[] checked;
    static ArrayList<Point> chickenMap;
    static ArrayList<Point> houseMap;
    static Stack<Point> selectedChicken;
    static int minDist;

    public static void dfs(int start, int cnt) {
        if(cnt == M) {
            calculate();
            return;
        }

        for(int i = start; i < chickenMap.size(); i++) {
            if(!checked[i]) {
                selectedChicken.push(chickenMap.get(i));
            }
            dfs(i+1, cnt+1);
            selectedChicken.pop();
            if(selectedChicken.isEmpty()) {
                checked[i] = true;
            }
        }
    }

    public static void calculate() {
        int sum = 0;
        for(Point house : houseMap) {
            int min = Integer.MAX_VALUE;
            for (Point chicken : selectedChicken) {
                int dist = (Math.abs(house.x - chicken.x)) + (Math.abs(house.y - chicken.y));
                min = Math.min(min, dist);
            }
            sum += min;
            //한 집으로부터 최소 치킨 거리가 현재까지 구한 최소 치킨 거리보다 클 경우 유망하지 않기 때문에 가지치기(백트래킹)
            if(sum > minDist) {
                return;
            }
        }
        minDist = Math.min(sum, minDist);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        chickenMap = new ArrayList<>();
        houseMap = new ArrayList<>();
        selectedChicken = new Stack<>();
        minDist = Integer.MAX_VALUE;

        //집과 치킨집의 개수를 구한다.
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    chickenMap.add(new Point(i, j));
                }
                //집의 좌표값을 미리 저장
                if(map[i][j] == 1) {
                    houseMap.add(new Point(i, j));
                }
            }
        }
        checked = new boolean[chickenMap.size()];
        dfs(0, 0);
        System.out.println(minDist);
    }
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

