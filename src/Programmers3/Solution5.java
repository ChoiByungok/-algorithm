package Programmers3;

/**
 * qr code
 */
public class Solution5 {
    public String solution(int q, int r, String code) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            int i1 = i % q;
            if (i1 == r) {
                answer.append(code.charAt(i));
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int q1 = 3;
        int r1 = 1;
        String code1 = "qjnwezgrpirldywt";

        int q2 = 1;
        int r2 = 0;
        String code2 = "programmers";

        System.out.println(new Solution5().solution(q1, r1, code1));
    }
}
