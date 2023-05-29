package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class InterleaveOperator {
    static BufferedReader br;
    static int N;
    static int[] A;
    static char[] operators;
    static int plus, minus, multiply, divide;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int sum = 0;

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multiply = Integer.parseInt(st.nextToken());
        divide = Integer.parseInt(st.nextToken());
        operators = new char[plus+minus+multiply+divide];
        int cnt = 0;
        for(int i = 0; i < plus; i++) {
            operators[cnt++] = '+';
        }
        for(int i = 0; i < minus; i++) {
            operators[cnt++] = '-';
        }
        for(int i = 0; i < multiply; i++) {
            operators[cnt++] = '*';
        }
        for(int i = 0; i < divide; i++) {
            operators[cnt++] = '/';
        }
    }

    public static void solve(char[] operators, int depth) {
        if(depth == N-1) {
            calculate(operators);
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            sum = 0;
        }

        for(int i = depth; i < N-1; i++) {
            swap(operators, i, depth);
            solve(operators, depth+1);
            swap(operators, i, depth);
        }
    }

    public static void calculate(char[] operators) {
        sum += A[0];
        for(int i = 0; i < N-1; i++) {
            if(operators[i] == '+') {
                sum += A[i+1];
            }
            if(operators[i] == '-') {
                sum -= A[i+1];
            }
            if(operators[i] == '*') {
                sum *= A[i+1];
            }
            if(operators[i] == '/') {
                sum /= A[i+1];
            }
        }
    }

    public static void swap(char[] operators, int x, int y) {
        char tmp = operators[x];
        operators[x] = operators[y];
        operators[y] = tmp;
    }

    public static void main(String[] args) throws IOException {
        input();
        solve(operators, 0);
        System.out.println(max);
        System.out.println(min);
    }
}
