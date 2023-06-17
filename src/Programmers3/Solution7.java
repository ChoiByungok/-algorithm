package Programmers3;

/**
 * 코드 처리하기
 */
public class Solution7 {
    public String solution(String code) {
        StringBuilder answer = new StringBuilder();
        int mode = 0;
        for (int i = 0; i < code.length(); i++) {
            if (mode == 0) {
                if (code.charAt(i) != '1') {
                    if (i % 2 == 0) {
                        answer.append(code.charAt(i));
                    }
                } else {
                    mode = 1;
                }
            } else {
                if (code.charAt(i) != '1') {
                    if (i % 2 == 1) {
                        answer.append(code.charAt(i));
                    }
                } else {
                    mode = 0;
                }
            }
        }

        return answer.length() != 0 ? answer.toString() : "EMPTY";
    }

    public static void main(String[] args) {
        String code = "abc1abc1abc";
        System.out.println(new Solution7().solution(code));
    }
}
