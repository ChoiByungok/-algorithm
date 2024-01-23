package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 13904 과제 (Gold3)
 * 우선순위 큐를 이용해야 할거같은데 가늠이 잡히지 않음
 * 7
 * 4 60
 * 4 40
 * 1 20
 * 2 50
 * 3 30
 * 4 10
 * 6 5
 * 라고 입력 받았을 때 점수 기준으로 내림차순하고 점수가 같으면 마감일로 오름차순 정렬을 하고 풀어보려고했는데
 * 1일차에 4 60, 2일차에 2 50, 3일차에 4 40 4일차에 6 5 를 골라 155점이 나옴
 * 최댓값은 185가 나와야 함 3일차에 4 40 을 고르지 말고 3 30을 골라야 하는데 이걸 어떻게 해야 할지 모르겠음
 * 결국 구글링을 하여 힌트를 얻음 마감기한 역순으로 정렬하고 마감기한이 같으면 점수를 역순으로 정렬한 뒤
 * 첫줄에 입력받은 값이 7이므로 7일차부터 1일차까지 역순으로 탐색을 함
 * 저 위에 값을 정렬하면
 * 6 5
 * 4 60
 * 4 40
 * 4 10
 * 3 30
 * 2 50
 * 1 20
 * 이 되는데
 * 첫번째 반복문 7일차에는 모든 과제의 마감기한을 넘겼으므로 넘김
 * 두번째 반목문 6일 차에는 6 5 의 5점 짜리 과제를 할 수 있으므로 5점을 더하고 해당 과제는 리스트에서 삭제함
 * 세번째 반복문 5일차에는 제출한 과제 이외의 모든 과제의 마감기한을 넘겼으므로 넘김
 * 네번째 반복문 4일차에는 4 60, 4 40, 4 10 세 가지의 과제를 할 수 있는데 이중 최대 값은 60 점이르모
 * 해당 과제를 리스트에서 삭제하고 60점을 더함 누적 값은 65점이 됨
 * 다섯번째 반복문 3일차에는 제출한 과제를 제외하고 4 40, 4 10, 3 30 세가지의 과제를 제출할 수 있음
 * 이중 점수가 가장 높은 4 40 과제를 리스트에서 삭제하고 40점을 더함 누적값은 105점이 됨
 * 여섯번째 반복문 2일차에는 제출한 과제를 제외하고 4 10, 3 30, 2 50 세가지의 과제를 제출할 수 있음
 * 이중 점수가 가장 높은 2 50 과제를 리스트에서 삭제하고 50점을 더함 누적값은 155점이 됨
 * 마지막 일곱번째 반복문 1일차에는 제출한 과제를 제외하고 4 10, 3 30, 1 20 세가지의 과제를 제출 할 수 있음
 * 이중 점수가 가장 높은 3 30 과제를 리스트에서 삭제하고 30점을 더하여 최종 누적값은 185점이 되는 문제임
 * 결국 우선순위 큐도 필요없었고 역순으로 풀어야 겠다는 생각의 전환과 어떻게 정렬할 것인가 창의력이 필요한 문제였음 아직도 갈길이 멀다고 느낌
 */
public class Solution107 {
    static class Assignment {
        int deadLine;
        int score;
        public Assignment(int deadLine, int score) {
            this.deadLine = deadLine;
            this.score = score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(bufferedReader.readLine());
        List<Assignment> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int deadLine = Integer.parseInt(split[0]);
            int score = Integer.parseInt(split[1]);
            list.add(new Assignment(deadLine, score));
        }

        list.sort((o1, o2) -> {
            if (o1.deadLine == o2.deadLine) {
                return o2.score - o1.score;
            }
            return o2.deadLine - o1.deadLine;
        });

        for (int day = N; day >= 1; day--) {
            int max = 0;
            int submit = -1;
            for (int i = 0; i < list.size(); i++) {
                Assignment assignment = list.get(i);
                if (day > assignment.deadLine) {
                    break;
                }
                if (max < assignment.score) {
                    max = assignment.score;
                    submit = i;
                }
            }
            if (submit == -1) {
                continue;
            }
            list.remove(submit);
            answer += max;
        }

        System.out.println(answer);
    }
}
