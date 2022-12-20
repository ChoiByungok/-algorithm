package Programmers;

import java.util.HashMap;
import java.util.Map;

/**
 * 모스부호(1)
 */
public class Solution36 {
    public String solution(String letter) {
        Map<String,String> map = new HashMap<>();
        StringBuilder answer = new StringBuilder();

        String[] split = letter.split(" ");
        String morse = "'.-':'a','-...':'b','-.-.':'c','-..':'d','.':'e','..-.':'f',\n" +
                "    '--.':'g','....':'h','..':'i','.---':'j','-.-':'k','.-..':'l',\n" +
                "    '--':'m','-.':'n','---':'o','.--.':'p','--.-':'q','.-.':'r',\n" +
                "    '...':'s','-':'t','..-':'u','...-':'v','.--':'w','-..-':'x',\n" +
                "    '-.--':'y','--..':'z'";

        String s = morse.replaceAll("['\n]", "");

        String[] split1 = s.split(",");
        for (String value : split1) {
            String[] split2 = value.split(":");
            map.put(split2[0].trim(), split2[1].trim());
        }
        for (String value : split) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (value.equals(entry.getKey())) {
                    answer.append(entry.getValue());
                }
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String hello = ".... . .-.. .-.. ---";
        String python = ".--. -.-- - .... --- -.";
        System.out.println(new Solution36().solution(hello));
    }
}
