package BOJ;

import java.io.*;
import java.util.*;

public class Bottle {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> ans;
    static int[] cur = new int[3];
    static int[] capacity = new int[3];
    static HashSet<String> set;

    public static void main(String[] args) throws IOException{
        input();
        int[] arr = cur.clone();
        dfs(arr);
        Collections.sort(ans);
        for(int a : ans) {
            sb.append(a).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) {
            capacity[i] = Integer.parseInt(st.nextToken());
        }
        cur[0] = cur[1] = 0;
        cur[2] = capacity[2];
        ans = new ArrayList<>();
        set = new HashSet<>();
        set.add(Arrays.toString(cur));
    }

    static void dfs(int[] cur) {
        String curStr = Arrays.toString(cur);
        //리스트 추가 조건
        if(cur[0] == 0 && set.contains(curStr)) ans.add(cur[2]);

        for(int i = 0; i < 3; i++) {
            //각각 컵에 물이 담겨 있을 때 모든 경우의 수로 계산해야함
            if(cur[i] != 0) {
                for(int j = 0; j < 3; j++) {
                    if(j != i) {
                        int[] tmp = cur.clone();
                        int jCupCanCapacity = capacity[j] - cur[j];
                        if(tmp[i] <= jCupCanCapacity) {
                            tmp[j] += tmp[i];
                            tmp[i] = 0;
                        }
                        else {
                            tmp[j] += tmp[i] - (tmp[i] - jCupCanCapacity);
                            tmp[i] -= tmp[i] - (tmp[i] - jCupCanCapacity);
                        }
                        if(!set.contains(Arrays.toString(tmp))) {
                            set.add(Arrays.toString(tmp));
                            dfs(tmp);
                        }
                    }
                }
            }
        }
        return;
    }
}
