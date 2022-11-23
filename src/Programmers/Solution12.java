package Programmers;
/**
 * 2016년
 */

import java.time.*;

public class Solution12 {
    public String solution(int a, int b) {
        String[] answer = {"","MON","TUE","WED","THU","FRI","SAT","SUN"};
        LocalDate date = LocalDate.of(2016,a,b);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int day = dayOfWeek.getValue();

        return answer[day];
    }
    public static void main(String[] args) {
        Solution12 solution12 = new Solution12();

        System.out.println(solution12.solution(5,24));
    }
}
