package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2467 {

    /*
    목표 : 양수, 음수를 더했을 때 0에 가장 가깝게 만들 수 있는 두 수를 찾아라.
    조건
        1. 오름차순 정렬되어 있음.
        2. 음수, 양수끼리 더할 수 있다.
        3. N은 1 이상 10만 이하의 정수
        4. 0에 가장 가깝게 만들 수 있는 경우가 2개 이상일 경우 아무거나 출력해도 된다.
    생각
    1. O(N log N) 까지 가능할 듯
    2. -10억 ~ 10억 이니 int 범위 안
    3. 현재 비교될 용액 a가 있을 때, 비교 할 용액 b가 0보다 크면 b보다 큰 용액은 검사 할 필요가 없음.
    4. 그렇다면 용액 1개당 비교 횟수는 log n 이 된다.
    5. O(n log n) 으로 해결 가능
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, ansVal, ansA, ansB;
    static int[] nums;

    public static void main(String[] args) throws IOException {

        //초기화
        ansVal = Integer.MAX_VALUE;
        n = stoi(br.readLine());
        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = stoi(st.nextToken());
        }

        //로직
        L:for (int i = 0; i < nums.length; i++) {
            int curWater = nums[i];
            int left = i == 0 ? 1 : 0;
            int right = i == nums.length - 1 ? nums.length - 2 : nums.length - 1;


            while (left <= right) {
                int mid = (left + right) / 2;

                //mid 자리에 있는 값이 curWater 값과 같은 경우 바로 넘어감.
//                if (curWater == nums[mid]) {
//
//                }

                int sum = nums[mid] + curWater;
                //nums[mid] + curWater 가 0이면 바로 출력
                if (sum == 0) {
                    ansA = curWater;
                    ansB = nums[mid];
                    break L;
                }

                //현재 가장 0에 가까운 값 갱신
                if (Math.abs(sum) < ansVal && curWater != nums[mid]) {
                    ansVal = Math.abs(sum);
                    ansA = curWater;
                    ansB = nums[mid];
                }

                if (sum < 0) {
                    left = mid + 1;
                }

                if (sum > 0) {
                    right = mid - 1;
                }
            }
        }

        bw.write(ansA + " " + ansB);

        //출력
        bw.flush();
        br.close();
        bw.close();
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}
