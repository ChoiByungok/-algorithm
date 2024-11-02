package Programmers3;

import java.util.Arrays;

/**
 * 최고의 집합 Lv.3
 * 이 문제를 처음 접했을 때는 n중 반복문을 사용하여 해결하려고하였다
 * 하지만 n값이 10000이 된다면 그 시간안에 절대 해결할 수 없다는 사실을 알게되었고
 * 결국 이 문제는 수학적으로 접근해야 한다고 생각했다.
 * 예제의 정답을 보니 결국 배열안에 들어있는 값들은 전부 최대값들의 집합이다.
 * 이 문제를 어떻게 접근해야 할까 고민하다가 결국 다른사람의 풀이를 보았다.
 * 우선 n이 s 보다 크다면 조합을 만들 수 없기에 배열에 -1 을 담아서 반환한다.
 * 그 외에는 계산을 해야 하는데 우선 n의 길이를 가진 배열을 하나 선언한다.
 * 그리고 s값을 n으로 나눈다. 그 값을 전부 배열에 채운다.
 * 그리고 s값을 n으로 나눈 나머지를 구한다 그리고 그 나머지의 숫자만큼
 * 배열에 뒤쪽부터 하나씩 접근하여 값을 증가시킨다. 오름차순으로 반환해야 하기 때문이다.
 * 예를 든다면 s가 10이고 n이 3일 경우
 * s를 n으로 나누면 값은 몫은 3이나온다 그 값을 배열에 채운다 [3, 3, 3]
 * 그리고 s를 n으로 나눈 나머지가 1이므로 배열의 가장 마지막 값을 1증가시킨다. [3, 3, 4] 즉 이 배열이 최고의 조합이 된다.
 * 어떻게 해야 이렇게 수학적으로 이 문제에 접근할 수 있을까 정말 아직 멀었다는 생각이들게 만드는 문제였다.
 */
public class Solution49 {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[] {-1};
        }
        int[] answer = new int[n];
        int div = s / n;
        for (int i = 0; i < n; i++) {
            answer[i] = div;
        }
        int mod = s % n;
        int index = n - 1;
        while (mod -- >0) {
            answer[index--]++;
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution49 solution49 = new Solution49();
        System.out.println(Arrays.toString(solution49.solution(2, 9)));
        System.out.println(Arrays.toString(solution49.solution(2, 1)));
        System.out.println(Arrays.toString(solution49.solution(2, 8)));
    }
}
