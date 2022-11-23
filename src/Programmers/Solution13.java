package Programmers;

/**
 * 소수 찾기
 */

public class Solution13 {
    public int solution(int n) {
        int answer = 0;

        for (int i = 2; i <= n; i++) {
            boolean prime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if(i%j == 0){
                    prime = false;
                    break;
                }
            }
            if(prime){
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        System.out.println(solution13.solution(999999));

    }
}
