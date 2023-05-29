package BOJ;

import java.io.*;
import java.util.*;

class Point {
    int h;
    int w;
    Point(int h, int w) {
        this.h = h;
        this.w = w;
    }
}

public class BOJ16937 {
    static BufferedReader br;
    static StringTokenizer st;
    static int H, W, N;
    static List<Point> inputList, pickedList;
    static boolean[] visited;
    static int max;

    public static void main(String[] args) throws IOException {
        init();
        dfs(2, 0);
        System.out.println(max);
    }

    static void init() throws IOException {
        max = 0;
        inputList = new ArrayList<>();
        pickedList = new ArrayList<>();
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        N = stoi(br.readLine());
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            inputList.add(new Point(stoi(st.nextToken()), stoi(st.nextToken())));
        }

    }

    static void dfs(int p, int start) {
        if(p == 0) {
            int h1 = pickedList.get(0).h;
            int w1 = pickedList.get(0).w;
            int h2 = pickedList.get(1).h;
            int w2 = pickedList.get(1).w;

            //둘 다 그대로인 경우
            if(checkBoundary(h1, w1, h2, w2)) {
                int sum = h1 * w1 + h2 * w2;
                max = Math.max(max, sum);
                return;
            }

            for(int i = 0; i < 3; i++) {
                int a = 0, b = 0, c = 0, d = 0;
                //1만 회전하는 경우
                if(i == 0) {
                    a = w1;
                    b = h1;
                    c = h2;
                    d = w2;
                }
                //2만 회전하는 경우
                if(i == 1) {
                    a = h1;
                    b = w1;
                    c = w2;
                    d = h2;
                }
                //둘 다 회전하는 경우
                if(i == 2) {
                    a = w1;
                    b = h1;
                    c = w2;
                    d = h2;
                }

                if(checkBoundary(a, b, c, d)) {
                    int sum = a * b + c * d;
                    max = Math.max(max, sum);
                    return;
                }
            }
            return;
        }

        //조합
        for(int i = start; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                pickedList.add(inputList.get(i));
                dfs(p-1, i);
                pickedList.remove(pickedList.size()-1);
                visited[i] = false;
            }
        }
    }

    static int stoi(String s) {return Integer.parseInt(s);}

    static boolean checkBoundary(int h1, int w1, int h2, int w2) {
        if(checkUpDown(h1, w1, h2, w2) || checkSide(h1, w1, h2, w2)) return true;
        return false;
    }

    static boolean checkUpDown(int h1, int w1, int h2, int w2) {
        if(h1+h2 > H || Math.max(w1, w2) > W) return false;
        return true;
    }

    static boolean checkSide(int h1, int w1, int h2, int w2) {
        if(Math.max(h1, h2) > H || w1+w2 > W) return false;
        return true;
    }
}