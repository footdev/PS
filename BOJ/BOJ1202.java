import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel>{
    int weight;
    int cost;

    public Jewel(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }


    @Override
    public int compareTo(Jewel o) {
        int comp = Integer.compare(weight, o.weight);
        if(comp == 0) {
            return Integer.compare(cost, o.cost);
        }
        return comp;
    }
}

public class BOJ1202 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, m, v, c;
    static long res;
    static Jewel[] jewels;
    static int[] bags;
    static PriorityQueue<Jewel> picked;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(res);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        k = stoi(st.nextToken());
        jewels = new Jewel[n];
        bags = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            m = stoi(st.nextToken());
            v = stoi(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        for (int i = 0; i < k; i++) {
            c = stoi(br.readLine());
            bags[i] = c;
        }

    }

    static void solve() {
        /*
        시간복잡도는 O(K log K)가 나와야 한다.
        보석 개수 N 가방 개수 K
        보석의 무게 M 보석의 가격 V
        가방의 무게 C
        보석 배열 [1, 65], [5, 23], [2, 99]
        가방 배열 [10], [2]
        각각 가방에 들어갈 수 있는 무게의 보석 중 최댓값을 넣어주면 된다.
        큰 가방은 넣을 수 있는게 많아 작은 것 부터 구해야함. 가방을 오름차순 정렬해서 작은 가방부터 탐색한다.
        보석도 무게순, 같다면 가격순으로 오름차순 정렬

        가방 하나씩 탐색 = 선형탐색 T(K)
        가방에 들어가는 보석 = 작은 것 부터 선정 T(N)
        가방에 들어가는 보석 중 최댓값 탐색 = 최대힙으로 맨 앞에 것 선정 T()
         */

        //가방 무게순 오름차순 정렬, 보석 무게순, 무게가 같다면 가격순 정렬
        Arrays.sort(bags);
        Arrays.sort(jewels);
        picked = new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return Integer.compare(o2.cost, o1.cost);
            }
        });

        //작은 가방 부터 하나씩
        int idx = 0;
        for (int i = 0; i < k; i++) {
            //가방에 들어가는 보석 찾기
            while(idx < n) {
                //가방의 무게안에 들어가는 보석인가?
                if(jewels[idx].weight <= bags[i]) {
                    picked.add(jewels[idx]);
                } else {
                    break;
                }
                idx++;
            }

            if(!picked.isEmpty()) {
                //최대값 더하기
                res += picked.poll().cost;
            }
        }
    }


    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}