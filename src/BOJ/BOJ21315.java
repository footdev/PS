package BOJ;

import java.io.*;
import java.util.*;

public class BOJ21315 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, k1, k2;
    static int[] input;
    static int[] deck;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(k1 + " " + k2);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        deck = new int[n];
        input = new int[n];
        for(int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            deck[i] = i+1;
        }
    }

    static void solve() {
        for(int i = 1; Math.pow(2,i) < n; i++) {
            for(int j = 1; Math.pow(2,j) < n; j++) {
                int[] arr = deck.clone();
                shuffle(i, arr);
                shuffle(j, arr);
                boolean flag = true;
                for(int k = 0; k < n; k++) {
                    if(arr[k] != input[k]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    k1 = i;
                    k2 = j;
                    return;
                }
            }
        }
    }

    static void shuffle(int k, int[] arr) {
        int range = n;
        int cnt;
        for(int i = 1; i <= k+1; i++) {
            cnt =(int)Math.pow(2, k-i+1);
            swap(range,cnt, arr);
            range = cnt;
        }
    }

    static void swap(int range, int cnt, int[] arr) {
        List<Integer> tmp = new ArrayList<>();
        //2^k-i+1개의 카드를 임시 배열에 옮기기
        for(int i = range-cnt; i < range; i++) {
            tmp.add(arr[i]);
            arr[i] = 0;
        }
        //임시 배열로 옮긴 원소들을 맨위로 옮기기
        for(int i = 0; i < n; i++) {
            if(arr[i] != 0) {
                tmp.add(arr[i]);
            }
            arr[i] = tmp.get(i);
        }
    }
}