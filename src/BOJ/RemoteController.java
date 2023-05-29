package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class RemoteController {
    static BufferedReader br;
    static StringTokenizer st;
    static String N;
    static int cnt;
    static String[] buttons;
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        System.out.println(ans);
    }
    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        cnt = Integer.parseInt(br.readLine());
        if(cnt > 0) {
            st = new StringTokenizer(br.readLine());
            buttons = new String[cnt];
            for(int i = 0; i < buttons.length; i++) {
                buttons[i] = st.nextToken();
            }
        }
    }

    public static void solve() {
        //예외케이스 1 기본 채널 100일 때
        if(Integer.parseInt(N) == 100) return;

        //예외케이스 2 버튼 10개가 전부 고장 났을 때
        if(cnt == 10) {
            ans += Math.abs(Integer.parseInt(N) - 100);
            return;
        }
        //버튼으로 눌렀을 때
        int buttonCnt = Math.abs(Integer.parseInt(N) - 100);
        //숫자 버튼을 눌렀을 때
        int numCnt = calculate(N);

        ans = Math.min(buttonCnt, numCnt);
    }

    public static int calculate(String n) {
        int tmpNum = Integer.parseInt(N);
        int minusNum = tmpNum - 1;
        int plusNum = tmpNum + 1;

        if(isValid(tmpNum)) return N.length();
        while(true) {
            if(minusNum >= 0) {
                if(isValid(minusNum)) {
                    String tmpStrNum = String.valueOf(tmpNum - minusNum);
                    return Integer.parseInt(tmpStrNum) + String.valueOf(minusNum).length();
                }
            }

            if(isValid(plusNum)) {
                String tmpStrNum = String.valueOf(plusNum - tmpNum);
                return Integer.parseInt(tmpStrNum) +  String.valueOf(plusNum).length();
            }
            minusNum--;
            plusNum++;
        }
    }

    public static boolean isValid(int n) {
        String tmp = String.valueOf(n);
        if(cnt == 0) return true;
        for(int i = 0; i < buttons.length; i++) {
            if(tmp.contains(buttons[i])) return false;
        }
        return true;
    }
}
