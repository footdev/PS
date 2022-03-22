import java.io.*;
import java.util.*;

public class MakingPassword {
    static BufferedReader br;
    static StringTokenizer st;
    static int l, c;
    static char[] arr;
    static StringBuilder sb;
    static Set<Character> gather;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0,  "");
        System.out.print(sb);
    }

    static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        checked = new boolean[c];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        gather = new HashSet<>();
        gather.add('a');
        gather.add('e');
        gather.add('i');
        gather.add('o');
        gather.add('u');
    }

    static void dfs(int depth, int idx, String str) {
        //정답 조건
        if(depth == l) {
            //1개의 모음, 2개의 자음 인지 확인
           for(int i = 0; i < str.length(); i++) {
               if(gather.contains(str.charAt(i))) {
                   int cnt = 0;
                   for(int j = 0; j < str.length(); j++) {
                       if(!gather.contains(str.charAt(j))) cnt++;
                   }
                   if(cnt >= 2) sb.append(str).append('\n');
                   return;
               }
           }
           return;
        }

        //뻗어나가는 구간
        for(int i = idx; i <= arr.length-(l-depth); i++) {
            dfs(depth+1, i+1, str+arr[i]);
        }

    }


}
