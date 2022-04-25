import java.io.*;
import java.util.*;

public class DeleteBracket {
    static BufferedReader br;
    static StringBuilder sb;
    static Stack<Integer> s;
    static List<brckXy> list;
    static int brckNum;
    static List<String> res;
    static HashSet<String> dup;

    public static void main(String[] args) throws IOException{
        input();
        searchBrck();
        solve(0, "");
        Collections.sort(res);
        for(int i = 0; i < res.size(); i++) {
            if(!dup.contains(res.get(i))) {
                dup.add(res.get(i));
                System.out.println(res.get(i));
            }
        }
    }

    static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder(br.readLine());
        list = new ArrayList<>();
        s = new Stack<>();
        res = new ArrayList<>();
        dup = new HashSet<>();
    }

    /*
    @param r 현재 위치
    @param idx 삭제 할 list 인덱스 문자열로 저장
     */
    static void solve(int r, String idx) {
        if(r== brckNum && idx.equals("")) return;
        if(r == brckNum) {
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i < idx.length(); i++) {
                //조합으로 뽑은 괄호 위치 인덱스를 담은 리스트를 하나씩 꺼내서 삭제
               brckXy point = list.get(idx.charAt(i)-'0');
               set.add(point.l);
               set.add(point.r);
            }
            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < sb.length(); i++) {
                if(!set.contains(i)) {
                    ans.append(sb.charAt(i));
                }
            }
            res.add(ans.toString());
            return;
        }

        solve(r+1, idx+r);
        solve(r+1, idx);
    }

    static void searchBrck() {
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '(') {
                s.push(i);
            }
            if(sb.charAt(i) == ')') {
                int tmp = s.pop();
                list.add(new brckXy(tmp, i));
            }
        }
        brckNum = list.size();
    }
}

class brckXy {
    int l;
    int r;
    brckXy(int l, int r) {
        this.l = l;
        this.r = r;

    }
}
