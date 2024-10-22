package Programmers3;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 이중우선순위큐 Lv.3
 * 우선순위 큐 2개를 사용하여 뭐 어떻게 값을 비교하고 요렇게 저렇게 머리굴리면 해결되는 문제인줄 알았는데
 * 심지어 큐는 한쪽에서만 삽입 삭제가 가능한 자료구조라 되게 머리를 굴렸지만
 * 자바에서 제공하는 queue 에는 remove 라는 메서드가 존재했다.
 * 결국 중간에 있는 원소를 제거하는 메서드라 이게 과연 맞는건지는 의문이지만 어쨋든
 * 이 메서드를 이용하여 쉽게풀었다.
 */
public class Solution39 {
    public int[] solution(String[] operations) {
        Queue<Integer> min = new PriorityQueue<>();
        Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        for (String operation : operations) {
            String[] split = operation.split(" ");
            String com = split[0];
            String num = split[1];
            if (com.equals("I")) {
                int parseInt = Integer.parseInt(num);
                max.add(parseInt);
                min.add(parseInt);
            } else {
                if (num.equals("1") && !min.isEmpty()) {
                    min.remove(max.poll());
                } else if (num.equals("-1") && !max.isEmpty()){
                    max.remove(min.poll());
                }
            }
        }

        int[] answer = new int[2];
        if (!max.isEmpty()) {
            answer[0] = max.poll();
        }

        if (!min.isEmpty()) {
            answer[1] = min.poll();
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        System.out.println(Arrays.toString(solution39.solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println(Arrays.toString(solution39.solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }
}
