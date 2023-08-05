package Programmers3;

/**
 * 조건 문자열
 */

public class Solution24 {
    public int solution(String ineq, String eq, int n, int m) {
        if (n > m) {
            if (ineq.equals(">")) {
                return 1;
            }
        } else if (n == m) {
            if (eq.equals("=")) {
                return 1;
            }
        } else {
            if (ineq.equals("<")) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String ineq1 = "<";
        String eq1 = "=";
        int n1 = 20;
        int m1 = 50;

        String ineq2 = ">";
        String eq2 = "!";
        int n2 = 41;
        int m2 = 78;

        System.out.println(new Solution24().solution(ineq1, eq1, 100, 99));
    }
}
