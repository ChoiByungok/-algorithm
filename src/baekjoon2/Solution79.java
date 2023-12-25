package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 17952 과제는 끝나지 않아!
 * 스택을 이용해 풀었는데 내 풀이는 코드가 많이 난잡하고 가독성이 떨어짐
 * 그냥 문제를 해결하는데 중점을 둔 코드 같음 심지어 문제가 잘 풀리지 않아
 * 질문게시판에 다른 사람이 올린 테스트 데이터로 확인하며 예외를 찾아냄
 */
public class Solution79 {
    static class Assignment {
        int score;
        int time;

        public Assignment(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Stack<Assignment> stack = new Stack<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            String token = tokenizer.nextToken();
            Assignment pop = null;
            if (!stack.isEmpty()) {
                pop = stack.pop();
            }
            if (token.equals("1")) {
                Assignment assignment = new Assignment(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                assignment.time--;
                if (assignment.time == 0) {
                    answer += assignment.score;
                    stack.push(pop);
                } else {
                    if (pop != null) {
                        stack.push(pop);
                    }
                    stack.push(assignment);
                }
            } else {
                if (pop == null) {
                    continue;
                }
                pop.time--;
                if (pop.time == 0) {
                    answer += pop.score;
                } else {
                    stack.push(pop);
                }
            }
        }
        System.out.println(answer);
    }
}
