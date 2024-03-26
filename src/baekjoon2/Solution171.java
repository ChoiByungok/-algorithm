package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 16120 PPAP (Gold4)
 * P랑 A로만 구성되어있는 문자열에서 P 는 PPAP로 치환이 된다.
 * 예를들어 PPPAPAP라는 문자열이 있으면 P(PPAP)AP 이므로 이것은 PPAP가 되고 PPAP 문자열이다.
 * 한마디로 문자들을 비교하다가 중간에 PPAP가 존재한다 그러면 P로 바꿔주면 된다.
 * 해당 문자열이 PPAP 문자열이라면 맨 마지막에 P 하나만 남게된다.
 * PPPAPAP가 PPAP가 되고 이게 P가 된다고 생각하면 된다.
 * 나는 이것을 스택을 이용하여 풀었다.
 * PPPAPAP라는 문자열의 문자들을 차례대로 스택에 넣는다 스택의 사이즈가 4가 넘어가는순간
 * 스택에서 4개의 문자를 꺼내 조합한뒤 그 문자가 PPAP인지 비교한다.
 * 로직대로 라면 처음에 PPPA를 PPAP랑 비교하게 될것이다. 하지만 이 문자는 PPAP가 아니므로 다시 스택에 넣어준다.
 * 그 다음엔 스택에 PPPAP가 들어가게 되고 다시 4개를 꺼내어 비교하게 된다.
 * 그러면 PPAP 이므로 스택에는 P만 집어넣는다 그러면 스택에는 PP가 들어가게 되는것이다.
 * 그대로 반복하다보면 PPA PPAP 순으로 스택에 쌓이게 되고 다시 비교를 들어가 결국 스택에는 P만 남게 되는것이다.
 */
public class Solution171 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String ppap = bufferedReader.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < ppap.length(); i++) {
            char c = ppap.charAt(i);
            stack.push(c);
            if (stack.size() >= 4) {
                StringBuilder ppapCheck = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    ppapCheck.append(stack.pop());
                }
                if (ppapCheck.reverse().toString().equals("PPAP")) {
                    stack.push('P');
                } else {
                    for (int j = 0; j < ppapCheck.length(); j++) {
                        stack.push(ppapCheck.charAt(j));
                    }
                }
            }
        }
        System.out.println(stack.size() == 1 && stack.peek() == 'P' ? "PPAP" : "NP");
    }
}
