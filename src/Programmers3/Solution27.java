package Programmers3;

/**
 * 등차수열의 특정한 항만 더하기
 */
public class Solution27 {
    public int solution(int a, int d, boolean[] included) {
        int[] sequence = new int[included.length];
        sequence[0] = a;
        int answer = 0;
        for (int i = 1; i < sequence.length; i++) {
            sequence[i] = sequence[i - 1] + d;
        }
        for (int i = 0; i < included.length; i++) {
            if (included[i]) {
                answer += sequence[i];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int a = 3;
        int d = 4;
        boolean[] included = {true, false, false, true, true};
        System.out.println(new Solution27().solution(a, d, included));
    }
}
