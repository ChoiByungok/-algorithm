package Programmers3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 대충 만든 자판
 */
public class Solution1 {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();
        int[] answer = new int[targets.length];
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (!(map.containsKey(c)) || map.get(c) > i) {
                    map.put(c, key.indexOf(c) + 1);
                }
            }
        }
        int idx = 0;
        for (String target : targets) {
            for (int i = 0; i < target.length(); i++) {
                char c = target.charAt(i);
                if (map.containsKey(c)) {
                    answer[idx] += map.get(c);
                } else {
                    answer[idx] = -1;
                    break;
                }
            }
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] keymap1 = {"ABACD", "BCEFD"};
        String[] targets1 = {"ABCD","AABB"};


        String[] keymap2 = {"AA"};
        String[] targets2 = {"B"};

        String[] keymap3 = {"AGZ", "BSSS"};
        String[] targets3 = {"ASA","BGZ"};


        System.out.println(Arrays.toString(new Solution1().solution(keymap3, targets3)));
    }
}
