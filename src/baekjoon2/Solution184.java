package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2877 4와 7 (Gold5)
 * 4 와 7로 이루어진 K번째 숫자의 길이를 구하는게 우선
 * 예를 들어 K가 1 ~ 2면 4 7이므로 1자리임
 * K가 3 ~ 6이면 44 47 74 77 이므로 2자리임
 * 자세히보면 이진법이랑 비슷하다는 느낌이 들었음
 * 4 7 44 47 74 77 444 447....
 * 숫자 길이구하는 공식은 1자리일땐 (2^1 - 1 <= K <= 2^2 -2)
 * 2자리일땐 (2^2 - 1 <= K <= 2^3 -2) 임
 * 그렇게 숫자 길이(length)를 구한뒤 그 길이만한 boolean형 배열을 생성함
 * 그다음 K 에서 2^length - 1 값을 뺀 후 그것을 2진수로 변환해야함
 * 예를들어 K가 3이면 length는 2임 그리고 3 에서 2^length - 1 값을 빼면 0이나옴
 * 0을 이진수로 변환하면 0임 length 보다 작으므로 앞에 0을 하나 더 붙여줌
 * 그럼 00이됨 이제 boolean배열에 값을 넣을건데 배열의 길이도 2이므로
 * 00의 값을 배열에 넣음 0은 false 1은 true로 넣으면 됨
 * 그럼 false false 가 될거임
 * 이 배열을 토대로 4와7로 이루어진 K번째 숫자를 출력하면 됨
 * 배열을 돌면서 false가 나오면 4 true가 나오면 7을 출력함
 * 고로 3은 44가 됨
 */
public class Solution184 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(bufferedReader.readLine());
        int length = 0;
        for (int i = 1; i <= 30; i++) {
            int pow = (int) Math.pow(2, i);
            int pow1 = (int) Math.pow(2, i + 1);
            if (pow - 1 <= K && K <= pow1 - 2) {
                length = i;
                break;
            }
        }
        boolean[] booleans = new boolean[length];
        String s = Integer.toBinaryString(K - ((int) Math.pow(2, length) - 1));
        if (s.length() < length) {
            StringBuilder builder = new StringBuilder(s);
            int re = length - s.length();
            for (int i = 0; i < re; i++) {
                builder.insert(0, "0");
            }
            s = builder.toString();
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                booleans[i] = true;
            }
        }

        StringBuilder answer = new StringBuilder();

        for (boolean aBoolean : booleans) {
            if (aBoolean) {
                answer.append("7");
            } else {
                answer.append("4");
            }
        }

        System.out.println(answer);
    }
}
