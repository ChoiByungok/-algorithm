package Programmers2;

/**
 * 콜라문제
 * 콜라를 받기 위해 마트에 주어야 하는 병 수 a,
 * 빈 병 a개를 가져다 주면 마트가 주는 콜라 병 수 b,
 * 상빈이가 가지고 있는 빈 병의 개수 n이 매개변수로 주어집니다.
 * 상빈이가 받을 수 있는 콜라의 병 수를 return 하도록 solution 함수를 작성해주세요.
 * 1 ≤ b < a ≤ n ≤ 1,000,000
 * 정답은 항상 int 범위를 넘지 않게 주어집니다.
 */
public class Solution32 {
    public int solution(int a, int b, int n) {
        int answer = 0;
        while (n >= a) {
            answer += (n / a) * b;
            n = ((n / a) * b) + (n % a);
        }
        return answer;
    }

    public static void main(String[] args) {
       int a1 = 2;
       int b1 = 1;
       int n1 = 20;

       int a2 = 3;
       int b2 = 1;
       int n2 = 20;

        System.out.println(new Solution32().solution(a1, b1, n1));
    }
}
