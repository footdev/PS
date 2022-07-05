import java.io.*;

public class BOJ12919 {
    static BufferedReader br;
    static String S, T;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dfs(T.length(), T));
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
    }

    static int dfs(int len, String str) {
       if(len == S.length()) {
           if(S.equals(str)) {
               return 1;
           } else {
               return 0;
           }
       }

        if(str.charAt(str.length()-1) == 'A') {
            if(dfs(len-1, str.substring(0, str.length()-1)) == 1) {
                return 1;
            }
        }
        if(str.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(str.substring(1));
            if(dfs(len-1, sb.reverse().toString()) == 1) {
                return 1;
            }
        }

        return 0;
    }
}
