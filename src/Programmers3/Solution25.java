package Programmers3;

import java.math.BigInteger;

/**
 * 두 수의 합
 * 자바의 BigInteger 이라는 클래스가 있다는 것을 처음 알게 됨
 */
public class Solution25 {
    public String solution(String a, String b) {
        return new BigInteger(a).add(new BigInteger(b)).toString();
    }

    public static void main(String[] args) {
        String a = "18446744073709551615";
        String b = "287346502836570928366";

        System.out.println(new Solution25().solution(a, b));
    }
}
