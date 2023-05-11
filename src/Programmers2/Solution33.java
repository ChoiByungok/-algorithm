package Programmers2;

/**
 * 문자열 나누기
 */
public class Solution33 {
    public int solution(String s) {
        int answer = 0;
        while (s.length() != 0) {
            char x = s.charAt(0);
            int sameLetterCount = 1;
            int anotherLetterCount = 0;
            for (int i = 1; i < s.length(); i++) {
                if (x == s.charAt(i)) {
                    sameLetterCount++;
                }else {
                    anotherLetterCount++;
                }
                if (sameLetterCount == anotherLetterCount) {
                    break;
                }
            }
            s = s.substring(sameLetterCount + anotherLetterCount);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        String s1 = "banana";
        String s2 = "abracadabra";
        String s3 = "aaabbaccccabba";
        String s4 = "aabcddnaaaaccbb";

        System.out.println(new Solution33().solution(s2));
    }
}
