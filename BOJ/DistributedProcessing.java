import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DistributedProcessing {
    static int T;
    static int a;
    static int b;
    static StringBuilder sb;

    public static int calculate() {
        int ans = 1;
        for(int i = 0; i < b; i++) {
            ans = (ans*a) % 10;
        }
        if(ans == 0) {
            return 10;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(calculate()).append('\n');
        }

        System.out.print(sb.toString());
    }
}
