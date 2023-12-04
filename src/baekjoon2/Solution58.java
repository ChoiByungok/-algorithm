package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1544 사이클 단어
 * 처음 보는 문자열이 나오면 리스트에 저장하고 중복이면 저장하지 않는다
 * 그러면서 리스트에 저장한 문자열과 새로 입력받은 문자열과 비교를한다.
 * 마찬가지로 하나라도 같은게 있으면 저장하지 않는다 하나라도 같은게 없으면 저장한다.
 * 이 방법을 어떻게 해야할 지 몰라서 한참고민하다 결국 해결도 못하고 남이 푼거보고 풀음
 */
public class Solution58 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String readLine = bufferedReader.readLine();
            boolean compare = false;

            for (String s : list) {
                if (compareString(readLine, s)) {
                    compare = true;
                    break;
                }
            }
            if (!compare) {
                list.add(readLine);
            }
        }
        System.out.println(list);
        System.out.println(list.size());
    }

    private static boolean compareString(String string, String string1) {
        if (string.length() != string1.length()) {
            return false;
        }
        if (string.equals(string1)) {
            return true;
        }
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < string1.length(); i++) {
            deque.add(string1.charAt(i));
        }

        for (int i = 0; i < string1.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            deque.offerLast(deque.pollFirst());
            while (!deque.isEmpty()) {
                stringBuilder.append(deque.pollFirst());
            }
            if (stringBuilder.toString().equals(string)) {
                return true;
            } else {
                for (int j = 0; j < stringBuilder.length(); j++) {
                    deque.offer(stringBuilder.charAt(j));
                }
            }
        }
        return false;
    }

}
