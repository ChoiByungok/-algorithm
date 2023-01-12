package Programmers2;

/**
 * 겹치는 선분의 길이
 */
public class Solution15 {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] arr = new int[200];
        for (int[] line : lines) {
            for (int j = line[0] + 100; j < line[1] + 100; j++) {
                arr[j]++;
            }
        }
        for (int i = 0; i < 200; i++) {
            if(arr[i] > 1){
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] lines1 = {{0,1},{2,5},{3,9}};
        int[][] lines2 = {{-1,1},{1,3},{3,9}};
        int[][] lines3 = {{0,5},{3,9},{1,10}};

        System.out.println(new Solution15().solution(lines1));
    }
}
