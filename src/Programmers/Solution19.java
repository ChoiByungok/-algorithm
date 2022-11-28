package Programmers;

/**
 * 숫자의 표현
 */

public class Solution19 {
    public int solution(int n) {
        int answer = 0;
        int sum;
        for (int i = 1; i <= n ; i++) {
            int j = i;
            sum = 0;
            while (true){
                sum += j;
                j++;
                if(sum >= n){
                    if(sum == n){
                        answer++;
                    }
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution19 solution19 = new Solution19();
        int n = 15;
        System.out.println(solution19.solution(n));
    }
}
