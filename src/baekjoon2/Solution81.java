package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 1972 놀라운 문자열
 * 처음에는 substring 메서드를 사용해서 풀라고 했는데 2글자씩만 잘라내는 문제라 substring 을 쓸 수 없었음
 * 예를들어 abcd 라는 문자열이 있을때
 * 첫번째 반복문때는 ab bc cd 이렇게 3개의 문자가 나오고
 * 두번째 반복문때는 ac bd 이렇게 2개의 문자가 나오고
 * 세번째 반복문때는 ad 하나만 나옴
 * 이중반복문을 사용해서 풀었는데 보다시피 바깥쪽 반복문은 문자열 길이의 -1 까지만 반복하게 되어있다는것을 알아냄
 * 그리하여 바깥쪽 반복문의 조건은 while (d < readLine.length()) 라고 정의 함  d라는 변수는 문자와의 간격이며 1부터 시작하고
 * 한 사이클을 반복할때마다 1씩 증가하게 되어있음 그리고 바깥쪽 반복문이 한번 실행 될때마다 문자열의 시작위치가 맨 처음으로 고정됨
 * 그리하여 바깥쪽 반복문에 int start = 0 이라는 변수를 두어 매 사이클 마다 0으로 초기화 되게 선언해놓음
 * 해당 start 변수는 내부 반복문에서 값이 바뀜 내부 반복문의 조건은 (start + d < readLine.length()) 이라고 정의 해놓음
 * 이렇게 두면 문자열의 범위를 벗어날 일이 없게 됨 이제 start 변수와 문자와의 간격 변수인 d 를 이용해 문자열을 추출 해 낸 뒤
 * 해당 문자열을 HashSet 에 저장하고 그 값에 중복이 발생하면 반복문을 빠져나가 is NOT surprising. 이라고 출력을 하고
 * 문제없이 반복문을 수행하게 되면 is surprising. 이라고 출력을 하여 문제를 풀었음
 */
public class Solution81 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            boolean surprising = true;
            if (readLine.equals("*")) {
                break;
            }
            if (readLine.length() == 1 || readLine.length() == 2) {
                answer.append(readLine).append(" is surprising.").append("\n");
            } else {
                char[] chars = readLine.toCharArray();
                int d = 1;
                while (d < readLine.length()) {
                    int start = 0;
                    Set<String> set = new HashSet<>();
                    while (start + d < readLine.length()) {
                        String substring = String.valueOf(chars[start]) + chars[start + d];
                        if (set.isEmpty()) {
                            set.add(substring);
                        } else {
                            if (set.contains(substring)) {
                                surprising = false;
                                break;
                            } else {
                                set.add(substring);
                            }
                        }
                        start++;
                    }
                    if (!surprising) {
                        break;
                    }
                    d++;
                }
                if (surprising) {
                    answer.append(readLine).append(" is surprising.").append("\n");
                } else {
                    answer.append(readLine).append(" is NOT surprising.").append("\n");
                }
            }
        }
        System.out.println(answer);
    }
}
