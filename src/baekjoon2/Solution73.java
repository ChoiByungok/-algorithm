package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 16139 인간-컴퓨터 상호작용
 * 서브테스크 1번만 통과해서 50점밖에 못받음
 * 서브테스크 2번까지 통과하려면 누적합을 적용시켜야 함
 */
public class Solution73 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int q = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        int[][] sum = new int[s.length()][26];

        /**
         * 누적합을 이용한 100점짜리 풀이
         */
        sum[0][s.charAt(0) - 'a']++; // 문자열의 첫번째 알파벳이 어디위치인지 알아냄
        for(int i = 1; i < s.length(); i++) {
            int tmp = s.charAt(i) - 'a';
            for (int j = 0; j < 26; j++) { //두번째 알파벳 위치가 어디인지 알아내기 전 이전의 값을 복사함
                sum[i][j] = sum[i - 1][j];
            }
            sum[i][tmp]++; //두번째 알파벳 위치를 알아냄
        }

        for (int i = 0; i < q; i++) {
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split(" ");
            int alphabet = split[0].charAt(0) - 'a';
            int start = Integer.parseInt(split[1]);
            int end = Integer.parseInt(split[2]);

            if (start == 0) {
                answer.append(sum[end][alphabet]).append("\n");
            } else {
                answer.append(sum[end][alphabet] - sum[start - 1][alphabet]).append("\n");
            }
        }

        /**
         * 그냥 깡으로 풀은 50점짜리 문제
         */
       /* for (int i = 0; i < q; i++) {
            int count = 0;
            String[] split = bufferedReader.readLine().split(" ");
            String alphabet = split[0];
            int start = Integer.parseInt(split[1]);
            int end = Integer.parseInt(split[2]);
            String substring = s.substring(start, end + 1);
            for (int j = 0; j < substring.length(); j++) {
                if (alphabet.equals(String.valueOf(substring.charAt(j)))) {
                    count++;
                }
            }
            answer.append(count).append("\n");
        }*/
        System.out.println(answer);
    }
}
