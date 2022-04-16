import java.io.*;
import java.util.*;

public class SalePizza {
    static BufferedReader br;
    static StringTokenizer st;
    static int orderNum, m, n, ans;
    static int[] mPizzaPieces, nPizzaPieces;
    static List<Integer> left, right;

    public static void main(String[] args) throws IOException{
        /*
        1. 피자 A의 부분합을 구한다.
        2. 피자 B의 부분합을 구한다.
        3. 두 배열의 합 중 정답과 일치하는 것을 고른다.
        유의할 점은 피자는 원형이기 때문에 부분합을 구할 때 신경써준다.
         */
        input();
        getSubSequences();
        Collections.sort(left);
        Collections.sort(right);
        getSum();
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        orderNum = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        mPizzaPieces = new int[m];
        nPizzaPieces = new int[n];
        left = new ArrayList<>();
        right = new ArrayList<>();
        left.add(0);
        right.add(0);
        for(int i = 0; i < m; i++) {
            mPizzaPieces[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i < n; i++) {
            nPizzaPieces[i] = Integer.parseInt(br.readLine());
        }
    }

    static void getSubSequences() {
        for(int i = 0; i < m; i++) {
            boolean[] checked = new boolean[m];
            checked[i]= true;
            calculate(mPizzaPieces, left, checked, mPizzaPieces[i], i, i+1);
        }
        for(int i = 0; i < n; i++) {
            boolean[] checked = new boolean[n];
            checked[i]= true;
            calculate(nPizzaPieces, right, checked, nPizzaPieces[i], i, i+1);
        }
    }

    static void calculate(int[] arr, List<Integer> list, boolean[] checked, int sum, int startIdx, int idx) {
        if(idx == arr.length) idx = 0;
        list.add(sum);
        //아직 방문하지 않았고 합이 orderNum보다 크지 않아야 하고 idx가 시작점을 넘지 않았어야 한다.
        if(!checked[idx] && sum + arr[idx] <= orderNum && idx != startIdx-1) {
            checked[idx] = true;
            calculate(arr, list, checked, sum+arr[idx], startIdx, idx+1);
        }
        else return;
    }

    static void getSum() {
        int start = 0;
        int end = right.size() - 1;
        while(start < left.size() && end >= 0) {
            int leftVal = left.get(start);
            int rightVal = right.get(end);
            int sum = leftVal + rightVal;
            if(sum == orderNum) {
                int leftCnt = 0;
                while(start < left.size() && left.get(start) == leftVal) {
                    start++;
                    leftCnt++;
                }
                int rightCnt = 0;
                while(end >= 0 && right.get(end) == rightVal) {
                    rightCnt++;
                    end--;
                }
                ans += leftCnt * rightCnt;
            }
            if(sum < orderNum) start++;
            if(sum > orderNum) end--;
        }
    }
}
