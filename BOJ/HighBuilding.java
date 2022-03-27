import java.io.*;
import java.util.*;

public class HighBuilding {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] buildings;
    static int[] visibles;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        Arrays.sort(visibles);
        System.out.println(visibles[visibles.length-1]);
    }
    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        buildings = new int[n];
        visibles = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        for(int i = 0 ; i < n-1; i++) {
            //바로 옆 건물은 무조건 보임
            visibles[i]++;
            visibles[i+1]++;
            double slope = buildings[i+1] - buildings[i];

            for(int j = i+2; j < n; j++) {
                double nextSlope = (double) (buildings[j]-buildings[i]) / (j-i);
                if(slope >= nextSlope) continue;
                slope = nextSlope;
                visibles[i]++;
                visibles[j]++;
            }
        }
    }
}
