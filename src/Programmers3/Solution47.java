package Programmers3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 카드뭉치 Lv.1
 * 문제를 보고 큐를 이용해서 풀어봐야겠다고 생각했다.
 * 카드1과 카드2 각각 다른 큐에 넣어놓고
 * 목표 배열을 순회하여 각각의 큐에 앞에있는 문자열과 같으면 큐에서 요소를 제거하고 continue를 시킨다.
 * 만약 두 조건 전부 맞지 않는다면 목표 문자열을 만들 수 없다는 것이고
 * break 를 시켜 상태를 false로 바꿔주었다.
 * 다른사람의 코드를 보니 굳이 큐를 사용하지 않고
 * 그냥 각각 카드 배열의 인덱스 상태만 변화시키고 순회를 하는 모습을 보았다.
 * 이제보니 저 방법이 더 효율적인거같다.
 */
public class Solution47 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        boolean possible = true;
        Queue<String> queue1 = new LinkedList<>(Arrays.asList(cards1));
        Queue<String> queue2 = new LinkedList<>(Arrays.asList(cards2));
        for (String s : goal) {
            if (s.equals(queue1.peek())) {
                queue1.poll();
                continue;
            }
            if (s.equals(queue2.peek())) {
                queue2.poll();
                continue;
            }
            possible = false;
            break;
        }
        return possible ? "Yes" : "No";
    }

    public static void main(String[] args) {
        Solution47 solution47 = new Solution47();
        System.out.println(solution47.solution(
                new String[] {"i", "drink", "water"},
                new String[] {"want", "to"},
                new String[] {"i", "want", "to", "drink", "water"}));

        System.out.println(solution47.solution(
                new String[] {"i", "water", "drink"},
                new String[] {"want", "to"},
                new String[] {"i", "want", "to", "drink", "water"}
        ));
    }
}
