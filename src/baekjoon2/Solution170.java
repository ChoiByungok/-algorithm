package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1769 3의 배수 (Silver5)
 * 내가 풀이한게 정답이라고 생각했는데 20퍼센트에서 막히는 문제 발생
 * 35784 라는 문자열이 주어지고 문제설명에 맞게 변환하면 27이됨 27은 3의 배수이니
 * 1번 변환했고 답은 1 YES 가 나오는게 맞다고 생각하는데 다른사람이 푼거 보니
 * 27에서 한번 더 변환하고 9로 만든다음 그게 3의 배수이니 2 YES 답을 뱉음
 * 왜 그런가 문제를 다시 읽어보니
 * 큰 수 X가 주어졌을 때, 앞에서 설명한 문제 변환의 과정을 몇 번 거쳐야 Y가 (한 자리 수가 되어),
 * X가 3의 배수인지 아닌지를 알 수 있게 될지를 구하는 프로그램을 작성하시오.
 * 라는 설명이 마지막에 있었음... 문제를 꼼꼼하게 읽어야 한다는것을 다시한번 상기시키는 문제
 */
public class Solution170 {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String x = bufferedReader.readLine();
        int count = 0;
        while (true) {
            long sum = 0;
            if (x.length() == 1) {
                break;
            }

            for (int i = 0; i < x.length(); i++) {
                sum += x.charAt(i) - '0';
            }

            count++;
            x = String.valueOf(sum);
        }

        answer.append(count).append("\n").append(Integer.parseInt(x) % 3 == 0 ? "YES" : "NO");
        System.out.println(answer);
    }
}
