package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 7662 이중 우선순위 큐
 * 1.우선 들어오는 입력값이 I 인지 D 인지 구분한다.
 * 2.I가 들어오면 큐에 집어넣는다.
 * 3.D가 들어오면 D뒤에 있는 정수가 양수인지 음수인지 구분한다.
 * 4.양수 일시 큐에서 가장 큰 수를 제거한다. 음수 일시 큐에서 가장 작은 수를 제거한다.
 * 5.만약 큐가 비어있는데 D가 들어오면 이 연산은 무시 가능하다.
 * 6.모든 연산을 처리한 후 큐에 남아있는 최댓값과 최솟값을 출력하면 된다.
 * 7.만약 모든 연산을 처리한 후 큐에 요소가 남아있지 않더라면 EMPTY 를 출력하면 된다.
 * 자바의 우선순위큐는 최대힙으로 구현하던 최소힙으로 구현하던 맨 앞에 요소만 삭제가능함
 * 해당 문제의 큐는 최댓값과 최솟값을 동시에 삭제하면서 계속 정럴되어있는 상태를 필요로함
 * 1시간 넘게 고민하다 구글 검색해서 풀음
 * 결론은 트리맵이라는 자료구조를 이용하면 편하게 풀 수 있었다.
 * 트리맵은 키값으로 정렬되는 말 그대로 이진트리 모양을 한 자료구조이다.
 * 새로 들어온 값이 루트노드보다 값이 크면 루트노드의 오른쪽 자식노드로 배정이 되고
 * 작으면 왼쪽 자식노드로 배정이 된다고 생각하면된다.
 * 트리셋도 사용할수 있었을 텐데 트리맵을 사용한 이유는 중복값을 표현하기 위해 사용했다고 보면된다.
 * 값을 집어넣을땐 해당 키값이 중복인지 아닌지 구하여 중복이면 value 값에 1을 더해가며 집어넣었고
 * 값을 뺄땐 해당 키의 value 값이 2이상인지 판단하여 2이상이면 value 값을 1줄여주고 아닐시 맵에서 삭제 시키는
 * 방식으로 구현을 하였다. 출력은 간단하게 맵이 비어있으면 EMPTY 라고 출력하고
 * 그렇지 않으면 최대값과 최소값을 출력해주는 식으로 출력하였다.
 * 키 값으로 정렬되는 해시 자료구조 트리맵 트리셋 기억해둬야 할거같다.
 */
public class Solution105 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
            int N = Integer.parseInt(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String[] split = bufferedReader.readLine().split(" ");
                int num = Integer.parseInt(split[1]);
                if (split[0].equals("I")) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.size() == 0) {
                        continue;
                    }
                    if (num == 1) {
                        Integer max = map.firstKey();
                        Integer count = map.get(max);
                        if (count > 1) {
                            map.put(max, count - 1);
                        } else if (count == 1) {
                            map.remove(max);
                        }
                    } else {
                        Integer min = map.lastKey();
                        Integer count = map.get(min);
                        if (count > 1) {
                            map.put(min, count - 1);
                        } else if (count == 1) {
                            map.remove(min);
                        }
                    }
                }
            }
            if (map.isEmpty()) {
                answer.append("EMPTY").append("\n");
            } else {
                answer.append(map.firstKey()).append(" ").append(map.lastKey()).append("\n");
            }
        }
        System.out.println(answer);
    }
}
