package Programmers;

/**
 * 외계어 사전
 */
public class Solution50 {
    public int solution(String[] spell, String[] dic) {
        int chk;
        boolean answer = false;
        for (String dics : dic) {
            chk = 0;
            if(dics.length() == spell.length) {
                for (String spells : spell) {
                    if(dics.contains(spells)){
                        chk++;
                    }
                }
            }
            if(chk == spell.length){
                answer = true;
                break;
            }
        }
        return answer ? 1 : 2;
    }

    public static void main(String[] args) {
        String[] spell1 = {"p", "o", "s"};
        String[] dic1 = {"sod", "eocd", "qixm", "adio", "soo"};

        String[] spell2 = {"z", "d", "x"};
        String[] dic2 = {"def", "dww", "dzx", "loveaw"};

        String[] spell3 = {"s", "o", "m", "d"};
        String[] dic3 = {"moos", "dzx", "smm", "sunmmo", "som"};

        System.out.println(new Solution50().solution(spell2,dic2));
    }
}
