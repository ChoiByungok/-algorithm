package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 9519 졸려 (Gold5)
 * 눈 깜빡인 횟수와 눈을 X번 깜빡인 후 보인 단어가 주어졌을 때 원래 단어가 뭐였는지 출력하는 문제
 * 규칙은 이러하다 한번깜빡일 때마다 k번째 글자는 앞에서부터 k번째와 k+1번째 글자 사이로 이동한다.
 * 그런데 이 문제는 원래 단어를 찾아야 하기 때문에 역순으로 행동해야 한다.
 * 규칙을 잘 살펴보면 문자열의 홀수번째 인덱스 문자를 뺀 뒤 그것을 역순으로 문자열 뒤에 붙이면 된다.
 * 이게 무슨 말이냐면 문제의 첫번째 예제 acefdb 라는 문자열이 있을 때 4번 깜빡이기 전 문자열은 abcdef 이다.
 * acefdb의 홀수번째 인덱스를 따로 뺀다 그러면 cfb이고 나머지는 aed가 된다.
 * aed 뒤에 cfb의 역순 bfc를 붙이면 aedbfc가 된다 이것이 한번 깜빡이기 전의 문자열이다.
 * 두번 깜빡이기 전에는 adfcbe 세번 깜빡이기 전에는 afbecd 네번 깜빡이기 전에는 abcdef가 되는것이다.
 * 근데 이 문제의 조건을 보면 최대 10억번 넘게 깜빡인다고 한다. 근데 그것을 1초안에 제출하라고 하니 저대로 풀면 당연히 시간초과가 발생한다.
 * 하지만 다시 첫번째 예제를 살펴보면 4번깜빡이기 전에 abcdef 에서 한번더 깜빡이기 전으로 돌아가면
 * acefdb 가 된다 이것은 예제의 문자열과 같다. 즉 이 문자열들은 계속 깜빡인다면 언젠가는 다시 원래 문자열로 돌아와서 반복한다는 것이다.
 * 한마디로 10억번 반복할 필요가 없다는 것이다. 나는 그래서 set 자료구조를 만들어
 * 내가 집어넣었던 문자열과 같은 문자열이 들어온다면 반복문을 탈출시켰고
 * 그것을 리스트로 변환하여 처음 주어진 깜빡임 에서 리스트 사이즈를 나머지 연산을 하여 문제를 제출 하였다.
 * 근데 여기서 주의해야 할점은 주어진 깜빡임 숫자가 최종 리스트의 사이즈와 같거나 리스트의 사이즈가 1이라면
 * 리스트의 첫번째 요소를 출력시키고 아니면 X % list.size -1 번째 요소를 출력시키면 된다.
 */
public class Solution165 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(bufferedReader.readLine());
        Set<String> set = new LinkedHashSet<>();
        StringBuilder answer = new StringBuilder(bufferedReader.readLine());
        while (true) {
            StringBuilder temp  = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < answer.length(); i++) {
                if (i % 2 != 0) {
                    stack.push(answer.charAt(i));
                } else {
                    temp.append(answer.charAt(i));
                }
            }
            while (!stack.isEmpty()) {
                temp.append(stack.pop());
            }
            String key = temp.toString();
            if (set.contains(key)) {
                break;
            } else {
                set.add(key);
            }
            answer = temp;
        }
        List<String> list = new ArrayList<>(set);
        if (list.size() == 1 || X == list.size()) {
            System.out.println(list.get(0));
        } else {
            System.out.println(list.get(X % list.size() - 1));
        }
    }
}
