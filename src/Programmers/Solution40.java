package Programmers;

/**
 * k의 개수
 */
public class Solution40 {
    public int solution(int i, int j, int k) {
        int answer = 0;
        int x = 0;
        for (; i <= j ; i++) {
            x = i;
            while (x !=0){
                if((x % 10) == k){
                    answer++;
                }
                x /= 10;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int i = 3;
        int j = 10;
        int k = 2;
        System.out.println(new Solution40().solution(i,j,k));
    }
}
