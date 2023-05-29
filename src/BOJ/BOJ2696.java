package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2696 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int T;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException{
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 0;
            PriorityQueue<Integer> min = new PriorityQueue<>();
            PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

            // 1. 중앙값의 개수 출력
            sb.append(((N+1) / 2) + "\n");

            for(int i = 0; i < N; i++) {
                if(i % 10 == 0) {
                    st = new StringTokenizer(br.readLine());
                }

                // 2. 최대힙과 최소힙의 사이즈가 같다면 최대힙, 다르다면 최소힙에 넣는다.
                int val = Integer.parseInt(st.nextToken());
                if(max.size() == min.size()) {
                    max.add(val);
                } else {
                    min.add(val);
                }

                // 3. 최소힙에서 peek한 값과 최대힙에서 peek한 값보다 작을 경우 스위칭 해준다.
                //최대힙은 중앙값 이하의 값들만 가져야 성립하기 때문이다.
                if(!min.isEmpty()) {
                    if(min.peek() < max.peek()) {
                        int minVal = min.poll();
                        int maxVal = max.poll();

                        min.add(maxVal);
                        max.add(minVal);
                    }
                }

                // 4. 홀수번 째 마다 max.peek 값 출력 (여기선 0부터 시작이니 짝수번 째)
                if(i % 2 == 0) {
                    //10개 째면 끊어주어야 함.
                    if(cnt == 9 || i == N - 1) {
                        sb.append(max.peek() + "\n");
                        cnt = 0;
                    } else {
                        sb.append(max.peek() + " ");
                    }
                    cnt++;
                }
            }
        }
    }
}