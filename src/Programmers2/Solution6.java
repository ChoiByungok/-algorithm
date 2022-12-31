package Programmers2;

/**
 * 삼각형의 완성조건 (2)
 */
public class Solution6 {
    public int solution(int[] sides) {
        int answer = 0;
        int max = Math.max(sides[0], sides[1]);
        int min = max == sides[0]? sides[1] : sides[0];
        int sum = sides[0] + sides[1];
        for (int i = 1; i <= max ; i++) {
            if((i + min) > max){
                answer++;
            }
        }
        for (int i = max+1; i < sum ; i++) {
            answer++;
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] sides1 = {1,2};
        int[] sides2 = {3,6};
        int[] sides3 = {11,7};
        System.out.println(new Solution6().solution(sides3));
    }
}
