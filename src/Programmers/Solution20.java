package Programmers;

/**
 * 다음 큰 숫자
 */
public class Solution20 {
    public int solution(int n) {
        int answer = 0;
        int chk = 0;
        int chk2;
        String s = Integer.toBinaryString(n);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                chk++;
            }
        }
        while (true){
            n++;
            String s1 = Integer.toBinaryString(n);
            chk2 = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) == '1') {
                    chk2++;
                }
            }
            if(chk == chk2){
                answer = n;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        int n = 15;
        System.out.println(solution20.solution(n));
    }
}
