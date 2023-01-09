package Programmers2;

import java.util.ArrayList;
import java.util.List;

/**
 * 유한소수 판별하기
 */
public class Solution13 {
    public int solution(int a, int b) {
        boolean finiteDecimal = true;
        List<Integer> prime = new ArrayList<>();
        List<Integer> factorization = new ArrayList<>();
        int gcd = gcd(a, b);
        a = a / gcd;
        b = b / gcd;
        if(a % b == 0){
            return 1;
        }
        for (int i = 2; i <= b ; i++) {
            if(isPrime(i)){
                prime.add(i);
            }
        }
        int index = 0;
        while (b != 1){
            if(b % prime.get(index) == 0){
                factorization.add(prime.get(index));
                b = b / prime.get(index);
                index--;
            }
            index++;
        }
        for (Integer integer : factorization) {
            if (!(integer == 2 || integer == 5)) {
                finiteDecimal = false;
                break;
            }
        }

        return finiteDecimal ? 1 : 2;
    }
    public int gcd(int num1, int num2){
        if(num1 > num2){
            int tmp = num1 % num2;
            while (tmp != 0){
                num1 = num2;
                num2 = tmp;
                tmp = num1 % num2;
            }
            return num2;
        }else if(num1 < num2){
            int tmp = num2 % num1;
            while (tmp != 0){
                num2 = num1;
                num1 = tmp;
                tmp = num2 % num1;
            }
            return num1;
        }else {
            return num1;
        }
    }
    public boolean isPrime(int num){
        int count = 0;
        for (int i = 1; i <= num ; i++) {
            if(num % i == 0){
                count++;
            }
            if (count > 2){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1 = 7;
        int b1 = 20;

        int a2 = 11;
        int b2 = 22;

        int a3 = 12;
        int b3 = 21;

        int a4 = 100;
        int b4 = 20;

        System.out.println(new Solution13().solution(a4,b4));
    }
}
