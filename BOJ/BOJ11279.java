import java.io.*;

public class BOJ11279 {
    static BufferedReader br;
    static StringBuilder sb;
    static int n;
    static int[] heap;
    static int rear;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        heap = new int[2000002];
        rear = 1;
    }

    static void solve() throws IOException{
        for(int i = 0; i < n; i++) {
            int in = Integer.parseInt(br.readLine());
            if(in == 0) {
              sb.append(poll(heap)).append("\n");
            }
            else {
                maxHipify(heap, in);
            }
        }
    }

    static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }


    static int poll(int[] arr) {
        if(rear == 1) return 0;
        // 1. 루트 값을 담는다.
        int res = arr[1];
        //2. 2. 맨 아래에 있는 값을 루트로 올린다.
        arr[1] = arr[--rear];
        arr[rear] = 0;
        int idx = 1;
        int biggerIdx;
        //3. 마지막으로 내려갈 때 까지 비교해가며 내린다.
        while(arr[idx*2] != 0 || arr[idx*2+1] != 0) {
            // 자식 노드 중 더 큰 노드를 찾는다.
            if(arr[idx*2] >= arr[idx*2+1]) biggerIdx = idx*2;
            else biggerIdx = idx*2+1;
            // 더 큰 자식과 부모 노드를 비교해서 부모가 더 작다면 스왑한다.
            if(arr[idx] < arr[biggerIdx]) swap(arr, idx, biggerIdx);
            // 부모가 더 크다면 더이상 스왑을 할 필요가 없으니 break 한다.
            else break;
            idx = biggerIdx;
        }
        return res;
    }

    static int maxHipify(int[] arr, int val) {
        //힙이 비어있는 경우
        if(rear == 1) {
            arr[rear++] = val;
            return rear;
        }
        int idx = rear;
        arr[rear++] = val;
        while(idx != 1) {
            //자식 노드가 부모 노드보다 큰 경우
            if(arr[idx] > arr[idx/2]) {
                swap(arr, idx, idx/2);
                idx = idx / 2;
            }
            //부모가 더 크다면 더이상 스왑을 할 필요가 없으니 break 한다.
            else if(arr[idx] <= arr[idx/2]) break;
        }
        return rear;
    }
}