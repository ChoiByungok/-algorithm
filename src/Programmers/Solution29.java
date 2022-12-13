package Programmers;

import java.util.Arrays;

/**
 * N개의 최소공배수
 */
public class Solution29 {
    public int solution(int[] arr) {
        if(arr.length == 1){
            return arr[0];
        }
        int gcd = gcd(arr[0], arr[1]);
        if(arr.length == 2){
            return gcd;
        }
        for (int i = 2; i < arr.length; i++) {
            gcd = gcd(gcd,arr[i]);
        }
        return gcd;
    }
    public int gcd(int num1, int num2){
        int mul = num1 * num2;
        int tmp = num2 % num1;

        while (tmp != 0){
            num2 = num1;
            num1 = tmp;
            tmp = num2 % num1;
        }
        return mul/num1;
    }

    public static void main(String[] args) {

        int[] arr = {2,6,8,14,15};
        System.out.println(new Solution29().solution(arr));
    }
}
