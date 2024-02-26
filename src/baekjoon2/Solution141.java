package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1244 스위치 켜고 끄기 (Silver4)
 * 등급은 낮은데 정답비율이 낮아서 풀어본 문제
 * 문제 자체는 어렵지 않은데 신경써야 할것들이 굉장히 많은 문제였음
 * 인덱스는 0부터 시작하고 문제는 1부터 시작하니 여기서부터 헷갈릴 요소가 많음
 * 출력도 20개 이후부터는 개행을 해야 하니 많이 틀리게 되는거였다.
 * 결국 나도 5번 제출만에 통과를 했으므로 정답률 20퍼센트에 공헌하게 되었다.
 */
public class Solution141 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());
        boolean[] switches = new boolean[N];
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < switches.length; i++) {
            switches[i] = tokenizer.nextToken().equals("1");
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            String student = tokenizer.nextToken();
            int num = Integer.parseInt(tokenizer.nextToken());
            int copy = num;
            int multiple = 1;
            if (student.equals("1")) {
                while (copy <= switches.length) {
                    switches[copy - 1] = !switches[copy - 1];
                    copy = num * ++multiple;
                }
            } else {
                int start = num;
                int end = num;
                while (start - 1 >= 0 && end - 1 < N && switches[start - 1] == switches[end - 1]) {
                    start--;
                    end++;
                }
                for (int j = start + 1; j <= end - 1; j++) {
                    switches[j - 1] = !switches[j - 1];
                }
            }
        }
        for (int i = 0; i < switches.length; i++) {
            if (i != 0 && i % 20 == 0) {
                answer.append("\n");
            }
            answer.append(switches[i] ? "1 " : "0 ");
        }

        System.out.println(answer);
    }
}
