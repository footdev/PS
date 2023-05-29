package BOJ;

import java.util.Arrays;

public class SearchNumber {
    int N;
    int[] Anums;
    int[] Mnums;
    int M;
    StringBuilder sb;

    public SearchNumber(int n, int[] Anums, int[] Mnums, int m) {
        N = n;
        this.Anums = Anums;
        this.Mnums = Mnums;
        M = m;
        sb = new StringBuilder();
    }

    public boolean bst(int start, int end, int num) {
        while(start <= end) {
            int mid = (start + end) / 2;
            if(num == Anums[mid]) {
                return true;
            }
            else if(num < Anums[mid]) {
                end = mid;
            }
            else if(num > Anums[mid]) {
                start = mid + 1;
            }
        }

        return false;
    }

    public void solve() {
        Arrays.sort(Anums);
        /*
        M개의 수만큼 돌면서 이분탐색을 통해 정렬된 A 값들 중에 M값이 있는지 판별
        이 때 시간복잡도는 O(M log A)
        * */
        for (int i = 0; i < Mnums.length; i++) {
            if(bst(0, Anums.length-1, Mnums[i])) {
                sb.append(1).append('\n');
            }
            else {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
    }
}