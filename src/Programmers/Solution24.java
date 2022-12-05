package Programmers;

/**
 * 예상 대진표
 */
public class Solution24 {
    public int solution(int n, int a, int b) {
        int answer = 0;
        for (int i = 1; i <= 20; i++) {
            if(n == (int)Math.pow(2,i)){
                answer = i;
                break;
            }
        }

        for (int i = 1; i <= answer ; i++) {
            n = n/2;
//            if(a % 2 == 0){
//                a = a/2;
//            } else if (a % 2 == 1) {
//                a = (a+1)/2;
//            }
//
//            if(b % 2 == 0){
//                b = b/2;
//            } else if (b % 2 == 1) {
//                b = (b+1)/2;
//            }
            a = a/2 + a%2;
            b = b/2 + b%2;

            if(a == b){
                answer = i;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 16;
        int a = 1;
        int b = 16;

        System.out.println(new Solution24().solution(n,a,b));

    }
}
