import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {
    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        String[] words = new String[N];
        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        /*
        * 1. 첫번 째로 주어진 단어를 StringBuilder에 저장
        * 2. 다른 단어들과 한글자씩 비교
        * 3. 비교하다가 sb에 있는 글자와 비교 당하는 단어의 글자가 다르고 sb가 현재 ?가 아니라면 sb에 글자를 ?로 바꿈
        */

       sb.append(words[0]);
       for(int i = 1; i < words.length; i++){
           for(int j = 0; j < words[0].length(); j++) {
               if(sb.charAt(j) != words[i].charAt(j) && sb.charAt(j) != '?') {
                   sb.replace(j, j+1, "?");
               }
           }
       }

        System.out.println(sb.toString());
    }
}
