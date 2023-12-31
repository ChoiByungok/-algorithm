package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 22233 가희와 키워드
 * 우선 N만큼 반복하여 HashSet 에 문자열을 집어넣는다
 * 그 다음 M만큼 반복하여 입력받은 문자열을 "," 기준으로 파싱하여 String[] 형으로 바꿔준 뒤
 * String[] 을 반복돌린 뒤 HashSet 에서 해당 문자를 remove 시켜준다
 * 그리고 HashSet 의 길이를 출력 시켜주면 되는 문제이다.
 */
public class Solution84 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        StringBuilder answer = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(bufferedReader.readLine());
        }
        for (int i = 0; i < M; i++) {
            String[] split = bufferedReader.readLine().split(",");
            for (String s : split) {
                set.remove(s);
            }
            answer.append(set.size()).append("\n");
        }
        System.out.println(answer);
    }
}
