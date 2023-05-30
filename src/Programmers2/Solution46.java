package Programmers2;

/**
 * 스킬트리
 */
public class Solution46 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skill_tree : skill_trees) {
            StringBuilder stringBuilder = new StringBuilder(skill_tree);
            for (int i = 0; i < stringBuilder.length(); i++) {
                if (skill.contains(String.valueOf(stringBuilder.charAt(i)))) {
                    stringBuilder.replace(i, i + 1, String.valueOf(skill.indexOf(stringBuilder.charAt(i))));
                }
            }
            String s = stringBuilder.toString().replaceAll("[^0-9]", "");
            if (s.length() == 0) {
                answer++;
            }

            if (!s.startsWith("0")) {
                continue;
            }
            int x = s.charAt(0) - 48;
            boolean check = true;
            for (int i = 1; i < s.length(); i++) {
                if (++x != s.charAt(i) - 48) {
                    check = false;
                    break;
                }
            }
            if (check) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String skill1 = "CBD";
        String[] skill_trees1 = {"BACDE", "CBADF", "AECB", "BDA" , "HCDB", "HCOD", "B"};

        String skill2 = "CBD";
        String[] skill_trees2 = {"CAD"};

        String[] skill_tress3 = {"AEF", "ZJW"};

        String skill3 = "CBDK";
        String[] skill_trees4 = {"CB", "CXYB", "BD", "AECD", "ABC", "AEX", "CBD", "CBKD", "IJCB", "LMDK"};
        System.out.println(new Solution46().solution(skill3, skill_trees4));
    }
}
