import java.io.*;
import java.util.*;

public class BOJ4358 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static double num;
    static SortedMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        map = new TreeMap<>();
        num = 0;
    }

    static void solve() throws IOException{
        // 1. 각 나무와 개수를 저장
        String treeName;
        while((treeName = br.readLine()) != null) {
            map.put(treeName, map.getOrDefault(treeName, 0)+1);
            num++;
        }

        // 2. 나무를 불러와서 비율을 구하고 sb에 저장
        for(String name : map.keySet()) {
            double per = (double)(map.get(name)*100.0) / num;
            sb.append(name).append(" ").append(String.format("%.4f", per)).append("\n");
        }
    }
}