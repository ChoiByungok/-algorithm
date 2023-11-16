package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1946 신입 사원
 * 2중 반복문으로 해결하려고 하니깐 시간초과가 남
 * 그래서 평균값을 구해서 평균값보다 큰 사람을 제거하고 2중반복문으로 하려니 그래도 시간 초과 남
 * 서류 전형으로 오름차순 한 뒤 첫번째 사원은 합격이라 놓고
 * 첫번째 사원의 인터뷰 점수를 최소값으로 놓은 뒤
 * 두번째 사원부터 비교를 해가면서 현재 최소값보다 작으면 합격으로 하고 최소값을 갱신해 나감
 */
public class Solution40 {
    static class Employee {
        int document;
        int interview;

        public Employee(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            List<Employee> employees = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                String[] split = bufferedReader.readLine().split(" ");
                int document = Integer.parseInt(split[0]);
                int interview = Integer.parseInt(split[1]);
                employees.add(new Employee(document, interview));
            }
            employees.sort(Comparator.comparingInt(o -> o.document));

            int pass = 1;
            int min = employees.get(0).interview;
            for (int j = 1; j < employees.size(); j++) {
                if (min > employees.get(j).interview) {
                    pass++;
                    min = employees.get(j).interview;
                }
            }
            answer.append(pass).append("\n");
        }
        System.out.println(answer);
    }
}
