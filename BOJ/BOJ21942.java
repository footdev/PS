import java.io.*;
import java.util.*;

class RentInfo {
    Map<String, String> bookList;
    RentInfo(String date, String parts) {
        bookList = new HashMap<>();
        this.bookList.put(parts, date);
    }
}

public class BOJ21942 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, F;
    static String bookInfo;
    static Map<String, RentInfo> rentList;
    static Map<String, Long> penaltyList;
    static int[] month = {-1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws IOException {
        init();
        solve();
        if(penaltyList.isEmpty()) System.out.println(-1);
        else {
            penaltyList.forEach((key, value) -> {
                sb.append(key+" "+value+"\n");
            });
            System.out.print(sb);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        bookInfo = st.nextToken();
        F = Integer.parseInt(st.nextToken());
        rentList = new HashMap<>();
        penaltyList = new TreeMap<>();
    }

    static void solve() throws IOException {
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String date = st.nextToken();
            date = date + " " + st.nextToken();
            String partsName = st.nextToken();
            String borrower = st.nextToken();

            //빌린 목록에 해당 사람이 있는지 판별
            if(!rentList.containsKey(borrower)) {
                // 없다면 대여한다는 뜻이니 생성 후 빌린 목록에 부품 이름 저장
                rentList.put(borrower, new RentInfo(date, partsName));
            } else {
                //있다면 빌린 목록에 같은 이름이 있는지 판별
                if(rentList.get(borrower).bookList.containsKey(partsName)) {
                    //같은 이름이 있다면 반납이니 정보를 꺼낸다.
                    String start = rentList.get(borrower).bookList.get(partsName);
                    //꺼낸 정보로 벌금을 부과해야 하는지 판단한다.
                    long penalty = getPenalty(start, date);
                    //부과해야 한다면 리스트에 값을 갱신하고 반납처리를 한다.
                    if(penalty != -1) {
                        penaltyList.put(borrower, penaltyList.getOrDefault(borrower, (long)0)+penalty);
                        rentList.get(borrower).bookList.remove(partsName);
                    } else {
                        //부과하지 않아도 된다면 반납 처리를 한다.
                        rentList.get(borrower).bookList.remove(partsName);
                    }
                } else {
                    //같은 이름이 없다면 대여한다는 뜻이니 대여 정보 저장
                    rentList.get(borrower).bookList.put(partsName, date);
                }
            }
        }
    }

    static long getPenalty(String start, String end) {
        /*
        yyyy-MM-dd HH:mm
        ddd/HH:mm
         */
        int[] startInfo = new int[4];
        int[] endInfo = new int[4];

        for(int i = 0; i < 4; i++) {
            if(i == 0) {
                startInfo[i] = Integer.parseInt(start.substring(5, 7));
                endInfo[i] = Integer.parseInt(end.substring(5, 7));
            }
            if(i == 1) {
                startInfo[i] = Integer.parseInt(start.substring(8, 10));
                endInfo[i] = Integer.parseInt(end.substring(8, 10));
            }
            if(i == 2) {
                startInfo[i] = Integer.parseInt(start.substring(11, 13));
                endInfo[i] = Integer.parseInt(end.substring(11, 13));
            }
            if(i == 3) {
                startInfo[i] = Integer.parseInt(start.substring(14, 16));
                endInfo[i] = Integer.parseInt(end.substring(14, 16));
            }
        }
        long bookDay = Integer.parseInt(bookInfo.substring(0, 3));
        long bookHour = Integer.parseInt(bookInfo.substring(4, 6));
        long bookMinute = Integer.parseInt(bookInfo.substring(7, 9));

        long sumMinute1 = (getSumOfArr(1, startInfo[0])+startInfo[1]) * 1440 + startInfo[2] * 60 + startInfo[3];
        long sumMinute2 = (getSumOfArr(1, endInfo[0])+endInfo[1]) * 1440 + endInfo[2] * 60 + endInfo[3];
        long sumMinute3 = bookDay * 1440 + bookHour * 60 + bookMinute;

        if(sumMinute1 + sumMinute3 < sumMinute2) {
            return (sumMinute2 - (sumMinute1 + sumMinute3)) * F;
        }

        return -1;
    }

    static long getSumOfArr(int s, int e) {
        long sum = 0;
        for(int i = s; i < e; i++) {
            sum += month[i];
        }
        return sum;
    }
}