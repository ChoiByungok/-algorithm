package Programmers3;

/**
 * 문자열 바꿔서 찾기
 */
public class Solution20 {
    public int solution(String myString, String pat) {
        StringBuilder stringBuilder = new StringBuilder(myString);
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == 'A') {
                stringBuilder.replace(i, i + 1, "B");
            } else if (stringBuilder.charAt(i) == 'B') {
                stringBuilder.replace(i, i + 1, "A");
            }
        }
        return stringBuilder.toString().contains(pat) ? 1 : 0;
    }

    public static void main(String[] args) {
        String myString1 = "ABBAA";
        String pat1 = "AABB";

        String myString2 = "ABAB";
        String pat2 = "ABAB";

        System.out.println(new Solution20().solution(myString2, pat2));
    }
}
