package Programmers3;

import java.util.Stack;

/**
 * 햄버거만들기 Lv.1
 * 생각나는대로 직관적으로 풀었더니 가독성은 똥망이 되어버린 코드
 * 1은 빵 2는 야채 3은 고기다
 * 햄버거는 무조건 1231 순으로 되어있어야 하므로
 * 스택을 이용하여 풀었다. 재료가 1이 들어왔을때 스택의 가장 위에있는 재료가 3이면
 * 반복문을돌려 나머지 2 1 이 나올때까지 돌린다. 아니면 다시 재료를 덮는다.
 * 스택이 비어있을수도 있으니 그것을 계속 체크하느라 코드의 뎁스가 깊어졌다. 매우 아쉬운 부분
 */
public class Solution51 {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i : ingredient) {
            if (i == 1) {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (stack.peek() == 3) {
                        Stack<Integer> temp = new Stack<>();
                        int num = 3;
                        boolean check = false;
                        while (true) {
                            if (stack.isEmpty()) {
                                check = true;
                                break;
                            } else {
                                if (num != stack.peek()) {
                                    check = true;
                                    break;
                                }
                                temp.push(stack.pop());
                                if (num == 1) {
                                    break;
                                }
                                num--;
                            }
                        }
                        if (check) {
                            while (!temp.isEmpty()) {
                                stack.push(temp.pop());
                            }
                            stack.push(i);
                        } else {
                            answer++;
                        }
                    } else {
                        stack.push(i);
                    }
                }
            } else {
                stack.push(i);
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(new Solution51().solution(new int[] {2, 1, 1, 2, 3, 1, 2, 3, 1}));
        System.out.println(new Solution51().solution(new int[] {1, 3, 2, 1, 2, 1, 3, 1, 2}));
    }
}
