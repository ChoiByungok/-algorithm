package Programmers;

/**
 * 최소직사각형
 */

public class Solution10 {
    public int solution(int[][] sizes) {
        int answer = 0;

        for (int i = 0; i < sizes.length; i++) {
            if(sizes[i][0] < sizes[i][1]){
                int tmp = sizes[i][1];
                sizes[i][1] = sizes[i][0];
                sizes[i][0] = tmp;
            }
        }
        int max1 = sizes[0][0];
        int max2 = sizes[0][1];
        for (int i = 0; i < sizes.length; i++) {
            if(max1 < sizes[i][0]) {
                max1 = sizes[i][0];
            }
            if(max2 < sizes[i][1]){
                max2 = sizes[i][1];
            }
        }
        answer = max1*max2;
        return answer;
    }

    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        int[][] sizes = {{60,50},{30,70},{60,30},{80,40}};
        System.out.println("answer = " + solution10.solution(sizes));
    }
}
