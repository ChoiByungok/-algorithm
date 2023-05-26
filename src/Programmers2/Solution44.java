package Programmers2;

import java.util.HashMap;
import java.util.Map;

/**
 * 폰켓몬
 */
public class Solution44 {

    public int solution(int[] nums) {
        int N = nums.length / 2 ;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return Math.min(map.size(), N);
    }
    public static void main(String[] args) {
        int[] nums1 = {3,1,2,3};
        int[] nums2 = {3,3,3,2,2,4};
        int[] nums3 = {3,3,3,2,2,2};

        System.out.println(new Solution44().solution(nums1));
    }
}
