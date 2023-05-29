package BOJ;

import java.io.*;

class Node {
    boolean isWord;
    boolean isHit;
    Node[] child;

    public Node(boolean isWord, boolean isHit) {
        this.isWord = isWord;
        this.isHit = isHit;
        this.child = new Node[26];
    }

    void clearHit() {
        isHit = false;
        for (int i = 0; i < child.length; i++) {
            if(child[i] != null) {
                child[i].clearHit();
            }
        }
    }

    boolean hasChild(char c) {
        return child[c - 'A'] != null;
    }

    Node getChild(char c) {
        return child[c  - 'A'];
    }
}

public class BOJ9202 {
    static final int GRID_SIZE = 4;
    static final int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    static final int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static BufferedReader br;
    static boolean[][] checked;
    static char[][] grid;
    static Node root;
    static int maxScore, findNum, maxLen;
    static String maxWord;
    static StringBuilder sb, ret;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.print(ret);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        ret = new StringBuilder();
        int n = stoi(br.readLine());
        root = new Node(false, false);

        for (int i = 0; i < n; i++) {
            insert(br.readLine());
        }
        grid = new char[GRID_SIZE][GRID_SIZE];
    }

    static void solve() throws IOException {
        br.readLine();
        int b = stoi(br.readLine());

        //보드의 개수
        for (int i = 0; i < b; i++) {
            if(i != 0) {
                br.readLine();
            }
            //방문 배열 매 보드마다 갱신
            checked = new boolean[GRID_SIZE][GRID_SIZE];
            maxScore = 0;
            maxWord = "";
            findNum = 0;
            maxLen = 0;

            //보드 입력 받기
            for (int j = 0; j < GRID_SIZE; j++) {
                String str = br.readLine();
                for (int k = 0; k < GRID_SIZE; k++) {
                    grid[j][k] = str.charAt(k);
                }
            }

            //0,0부터 3,3까지 찾기
            for (int j = 0; j < GRID_SIZE; j++) {
                for (int k = 0; k < GRID_SIZE; k++) {
                    if(root.hasChild(grid[j][k])) {
                        search(j, k, 1, root.getChild(grid[j][k]));
                    }
                }
            }

            //출력값 입력 및 클리어 히트
            ret.append(maxScore + " " + maxWord + " " + findNum + "\n");
            root.clearHit();
        }
    }

    static void insert(String word) {
        int len = word.length();
        Node cur = root;

        //단어의 문자별로 탐색
        for (int i = 0; i < len; i++) {
            int idx = word.charAt(i) - 'A';

            //없다면 자식 생성
            if(cur.child[idx] == null) {
                cur.child[idx] = new Node(false, false);
            }
            cur = cur.child[idx];
        }

        cur.isWord = true;
    }

    static void search(int x, int y, int depth, Node cur) {
        //체크인
        checked[x][y] = true;
        sb.append(grid[x][y]);
        //목적지인가?
        if(cur.isWord && !cur.isHit) {
            cur.isHit = true;
            //답 작업 -> 최대 길이 단어, 최대 점수, 찾은 단어 개수
            String foundWord = sb.toString();
            int wordLen = sb.length();
            findNum++;
            maxScore += score[wordLen];

            //현재 찾은 단어의 길이가 지금까지 찾은 제일 긴 단어의 길이와 같거나 길 때
            if(wordLen >= maxLen) {
                //같다면 사전 순
                if(wordLen == maxLen) {
                    maxWord = foundWord.compareTo(maxWord) < 0 ? foundWord : maxWord;
                } else {
                    maxWord = foundWord;
                }

                maxLen = wordLen;
            }
        }
        //연결된 곳 순회 : 상, 하, 좌, 우, 대각선 총 8방향
        for (int i = 0; i < 8; i++) {
            int xx = dx[i] + x;
            int yy = dy[i] + y;

            //가능한가?
            //1번 조건 : 맵을 벗어나지 않음
            if(xx < 0 || yy < 0 || xx >= GRID_SIZE || yy >= GRID_SIZE) continue;
            //2번 조건 : 방문하지 않았음
            if(checked[xx][yy]) continue;
            //3번 조건 : 자식 노드가 있음
            if(!cur.hasChild(grid[xx][yy])) continue;
            //간다.
            search(xx, yy, depth+1, cur.getChild(grid[xx][yy]));
        }

        //체크아웃
        checked[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    static int stoi(String s) {return Integer.parseInt(s);}
}