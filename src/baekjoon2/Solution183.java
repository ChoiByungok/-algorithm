package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1235 학생 번호 (Silver4)
 * 학생 번호를 뒤에서 k자리만 남겨놓았을때 구분 할 수 있는 최소의 k를 구하는문제
 * 1212345
 * 1212356
 * 0033445
 * 의 번호들이 주어졌을때
 * 뒤에서 1자리로 자르면 5 6 5 첫번째와 세번째가 중복된다.
 * 뒤에서 2자리로 자르면 45 56 45 마찬가지로 첫번쨰와 세번째가 중복된다.
 * 뒤에서 3자리로 자르면 345 356 445 로 중복이 되지않으므로 k는 3이된다.
 * 나는 뒤에서 자른다는 말을 듣고 문자열을 뒤집어서 앞에서부터 비교를 하였다.
 * 그렇게 비교를 하여 한번도 중복되지 않는다면 그 반복 횟수를 답안으로 제출하였다.
 */
public class Solution183 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int answer = 1;
        String[] numbers = new String[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = new StringBuilder(bufferedReader.readLine()).reverse().toString();
        }

        while (true) {
            boolean exit = true;
            label:
            for (int i = 0; i < numbers.length; i++) {
                String substring = numbers[i].substring(0, answer);
                for (int j = i + 1; j < numbers.length; j++) {
                    if (numbers[j].startsWith(substring)) {
                        exit = false;
                        break label;
                    }
                }
            }
            if (exit) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }
}
