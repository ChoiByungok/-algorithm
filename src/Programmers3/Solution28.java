package Programmers3;

import java.time.LocalDate;

/**
 * 날짜 비교하기
 */
public class Solution28 {
    //내가 푼 방법
    public int solution1(int[] date1, int[] date2) {
        int answer = 0;
        if (date1[0] < date2[0]) {
            return 1;
        }
        if (date1[0] == date2[0]) {
            if (date1[1] < date2[1]) {
                return 1;
            }
            if (date1[1] == date2[1]) {
                if (date1[2] < date2[2]) {
                    return 1;
                }
            }
        }
        return answer;
    }
    // 다른사람의 풀이중 라이브러리를 잘 활용한 예시
    public int solution2(int[] date1, int[] date2) {
        LocalDate dateA = LocalDate.of(date1[0], date1[1], date1[2]);
        LocalDate dateB = LocalDate.of(date2[0], date2[1], date2[2]);

        if (dateA.isBefore(dateB)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] date1 = {2021, 12, 28};
        int[] date2 = {2021, 12, 29};

        System.out.println(new Solution28().solution1(date1, date2));
    }
}
