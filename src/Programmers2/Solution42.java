package Programmers2;

/**
 * k진수에서 소수 개수 구하기
 * 0P0처럼 소수 양쪽에 0이 있는 경우
 * P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
 * 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
 * P처럼 소수 양쪽에 아무것도 없는 경우
 */
public class Solution42 {
    public int solution(int n, int k) {
        int answer = 0;


//         k진법 무식한 방법
        StringBuilder s = new StringBuilder();
        while (n != 0) {
            int i = n % k;
            s.append(i);
            n = n / k;
        }
        String string = s.reverse().toString();

//        k진법 유식한 방법
//        String string = Integer.toString(n, k);

        String[] split = string.split("0");
        for (String s1 : split) {
            if (!s1.equals("1") && !(s1.length() == 0)) {
                if (primeCheck(s1)) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public boolean primeCheck(String s) {
        long aLong = Long.parseLong(s);
        for (int i = 2; (long) i * i <= aLong; i++) {
            if (aLong % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n1 = 437674;
        int k1 = 3;

        int n2 = 110011;
        int k2 = 10;
        System.out.println(new Solution42().solution(n1, k1));
    }
}
