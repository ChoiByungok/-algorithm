package Programmers2;

/**
 * 저주의 숫자 3
 */
public class Solution3 {
    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++){
            answer++;
            while (answer % 3 == 0 || String.valueOf(answer).contains("3")){
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(new Solution3().solution(n));
    }
}
