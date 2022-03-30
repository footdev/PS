import java.io.*;
import java.util.*;

public class ContinuousSumOfPrimeNumbers {
    static BufferedReader br;
    static int n, ans, cntPrimeNum;
    static boolean[] numArr;
    static int[] primeNums;

    public static void main(String[] args) throws IOException{
        input();
        setIsPrime();
        findMinVal();
        System.out.println(ans);
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numArr = new boolean[n+1];
        ans = 0;
        cntPrimeNum = n;
    }

    static void findMinVal() {
        int start = 0;
        int end = 0;
        int sum = primeNums[start];
        while(end < primeNums.length) {
            if(sum == n) {
                ans++;
                sum = sum - primeNums[start];
                start++;
                if(start == primeNums.length) break;
            }
            if(sum < n) {
                end++;
                if(end == primeNums.length) break;
                sum += primeNums[end];
                continue;
            }
            if(sum > n) {
                sum = sum - primeNums[start];
                start++;
                if(start == primeNums.length) break;
            }
        }
    }

    static void setIsPrime() {
        //n개의 수들 중 소수를 판별한다.
        for(int i = 2; i <= n; i++) {
            if(numArr[i]) continue;
           for(int j = i+i; j <= n; j += i) {
               numArr[j] = true;
           }
        }
        //판별한 소수를 다시 크기를 갖춘 배열에 복사한다.
        primeNums = new int[cntPrimeNum];
        int cnt = 0;
        for(int i = 2; i <= n; i++) {
            if(!numArr[i]) primeNums[cnt++] = i;
        }
    }


}
