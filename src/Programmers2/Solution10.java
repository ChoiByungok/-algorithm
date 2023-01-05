package Programmers2;

/**
 * 다음에 올 숫자
 */
public class Solution10 {
    public int solution(int[] common) {
        if((common[common.length-1] - common[common.length-2]) == (common[1] - common[0])){
            return (common[1] - common[0]) + common[common.length-1];
        }
        if(common[common.length-1] < 0){
            int i = common[common.length - 2] / common[common.length - 1];
            return common[common.length-1] / i;
        }
        int i = common[common.length - 1] / common[common.length - 2];
        return common[common.length-1] * i;
    }

    public static void main(String[] args) {
        int[] common1 = {1, 2, 3, 4};
        int[] common2 = {2, 4, 8};
        int[] common3 = {-8,-4,-2};
        int[] common4 = {25,125,625};

        System.out.println(new Solution10().solution(common4));
    }
}
