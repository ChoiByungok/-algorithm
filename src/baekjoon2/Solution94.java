package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 27497 알파벳 블록
 * 1. 입력받은 문자열중 공백을 기준으로 첫번째 숫자가 1인지 2인지 3인지 구분해야 한다.
 * 2. 1일때는 블록을 문자열의 맨 뒤에 추가시켜준다.
 * 3. 2일때는 블록을 문자열의 맨 앞에 추가시켜준다.
 * 4. 3일때는 문자열을 구성하는 블록중 가장 나중에 추가된 블록을 제거 시켜준다.
 * 5. 3일때 빈 문자열이라면 아무런 동작도 하지않는다.
 * 6. 출력은 완성된 문자열을 시켜주면 되고 빈 문자열이라면 0을 출력시켜준다.
 * 기존에는 문자열을 스택에 담아서 스택에서 꺼낸 뒤 최근 블록이 어떤건지 구분하려고 했는데
 * String pop = stack.pop();
 * if (pop.equals(deque.peekFirst())) {
 *      deque.pollFirst();
 *    } else if (pop.equals(deque.peekLast())) {
 *      deque.pollLast();
 *    }
 * 이런식으로 하니깐 출력초과가 나오게 되었다.
 * 확실하지는 않지만 내 예상으로는
 * 5
 * 1 a
 * 1 b
 * 1 a
 * 3
 * 3
 * 라고 입력받는다면
 * 3을 만나기 전까지 스택에는 aba가 들어가 있을테고 덱에도 aba가 들어가 있을 것이다.
 * 이때 3을 만나게 되서 스택의 가장 위에 있는 a가 나오게 되고 이제 덱의 블록과 구분을 해야 하는데
 * 이 a 는 덱의 가장 마지막에 있는 a 이지만 저 위의 로직대로 라면 맨 앞에 a가 나가게 된다.
 * 이래서 틀리지 않았나 싶다.
 */
public class Solution94 {
    static class Block {
        String string;
        int index;

        public Block(String string, int index) {
            this.string = string;
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Deque<Block> deque = new ArrayDeque<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String token = tokenizer.nextToken();
            switch (token) {
                case "1": {
                    deque.offerLast(new Block(tokenizer.nextToken(), i));
                    break;
                }
                case "2": {
                    deque.offerFirst(new Block(tokenizer.nextToken(), i));
                    break;
                }
                case "3":
                    if (deque.isEmpty()) {
                        continue;
                    }
                    if (deque.peekFirst().index > deque.peekLast().index) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                    break;
            }
        }

        if (deque.isEmpty()) {
            System.out.println("0");
        } else {
            while (!deque.isEmpty()) {
                answer.append(deque.pollFirst().string);
            }
            System.out.println(answer);
        }
    }
}
