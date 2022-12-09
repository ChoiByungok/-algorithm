package Programmers;
//피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.

/**
 * 피보나치 수
 */
public class Solution27 {
    public int solution(int n) {
        int f_2 = 0;
        int f_1 = 1;
        int fn = f_1 + f_2;
        for (int i = 2; i <=n ; i++) {
            fn = f_1 + f_2;
            f_2 = f_1 % 1234567;
            f_1 = fn % 1234567;
        }
        return fn % 1234567;
    }

    public static void main(String[] args) {
        System.out.println(new Solution27().solution(1500));
    }
}
