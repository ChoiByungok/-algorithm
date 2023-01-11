package Programmers2;

/**
 * 옹알이 (1)
 */
public class Solution14 {
    public int solution(String[] babbling) {
        int answer = 0;
        for (String s : babbling) {
            String[] split = s.split("aya|ye|woo|ma");
            if (split.length == 0) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] babbling1 = {"aya", "yee", "u", "maa", "wyeoo"};
        String[] babbling2 = {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};

        System.out.println(new Solution14().solution(babbling1));
    }
}
