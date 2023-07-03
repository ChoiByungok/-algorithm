package Programmers3;

/**
 * 문자열이 몇 번 등장하는지 세기
 */
public class Solution14 {
    public int solution(String myString, String pat) {
        int answer = 0;
        for (int i = 0; i < myString.length(); i++) {
            String substring = myString.substring(i);
            if (substring.startsWith(pat)) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String myString1 = "banana";
        String pet1 = "ana";

        String myString2 = "aaaa";
        String pet2 = "aa";
        System.out.println(new Solution14().solution(myString1, pet1));
    }
}
