package BOJ;

import java.io.*;
import java.util.*;

class Element {
    int prior;
    char c;
    Element(int prior, char c) {
        this.prior = prior;
        this.c = c;
    }
}

public class BOJ1918 {
    static BufferedReader br;
    static StringTokenizer st;
    static char[] arr;
    static StringBuilder sb;
    static Stack<Element> s;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.println(sb.toString());
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        sb = new StringBuilder();
        s = new Stack<>();
    }

    static void solve() {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(') {
                s.push(new Element(0, '('));
            }
            else if(arr[i] == ')') {
                while(s.peek().c != '(') {
                    Element tmp = s.pop();
                    sb.append(tmp.c);
                }
                s.pop();
            }
            else if(arr[i] >= 'A' && arr[i] <= 'Z') {
                sb.append(arr[i]);
            }
            else {
                if(arr[i] == '*' || arr[i] == '/') {
                    if(s.isEmpty()) s.push(new Element(2, arr[i]));
                    else if(s.peek().prior < 2) {
                        s.push(new Element(2, arr[i]));
                    }
                    else {
                        while(!s.isEmpty() && s.peek().prior == 2) {
                            Element tmp = s.pop();
                            sb.append(tmp.c);
                        }
                        s.push(new Element(2, arr[i]));
                    }
                }
                else {
                    if(s.isEmpty()) s.push(new Element(1, arr[i]));
                    else if(s.peek().prior < 1) {
                        s.push(new Element(1, arr[i]));
                    }
                    else {
                        while(!s.isEmpty() && s.peek().prior >= 1) {
                            Element tmp = s.pop();
                            sb.append(tmp.c);
                        }
                        s.push(new Element(1, arr[i]));
                    }
                }
            }
        }
        if(!s.isEmpty()) {
            while(!s.isEmpty()) {
                Element tmp = s.pop();
                sb.append(tmp.c);
            }
        }
    }
}