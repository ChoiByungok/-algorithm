package Programmers2;

/**
 * 기사단원의 무기
 */
public class Solution22 {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for (int i = 1; i <= number; i++) {
            int count = primeCount(i, limit, power);
            answer += count;
        }
        return answer;
    }

    private int primeCount(int i, int limit, int power) {
        int count = 0;
        for (int j = 1; j * j <= i; j++) {
            if (j * j == i) {
                count++;
            } else if (i % j == 0) {
                count += 2;
            }
            if (count > limit) {
                return power;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int number = 10;
        int limit = 3;
        int power = 2;
        System.out.println(new Solution22().solution(number, limit, power));
    }
}
