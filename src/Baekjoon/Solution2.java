package Baekjoon;
/**
 *  4153번 직각삼각형
 */

import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[3];
        double[] pow = new double[3];
        while (true){

            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
                pow[i] = Math.pow(arr[i],2);
            }
            if (arr[0] == 0 && arr[1] == 0 && arr[2] ==0){
                break;
            }
            if((pow[0] + pow[1]) == pow[2]){
                System.out.println("right");
            } else if ((pow[0] + pow[2]) == pow[1]) {
                System.out.println("right");
            } else if ((pow[1] + pow[2]) == pow[0]) {
                System.out.println("right");
            }else {
                System.out.println("wrong");
            }
        }
    }
}
