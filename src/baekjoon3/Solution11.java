package baekjoon3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1342 행운의 문자열 (Silver1)
 * 입력받은 문자열의 길이가 1일때도 해당 문자열은 행운의 문자열로 답이 1이 나와야 하지만
 * 그것을 판단하지 못해 99퍼에서 틀렸음
 * 중복문자를 맵에 담아서 카운트를 세서 그걸 리스트로 바꾸고 리스트를 순회하며
 * 문자의 카운트가 1이상일때 StringBuilder에 넣어주고 그 StringBuilder을 매개변수로 넘겨주며
 * 길이가 2이상일때 해당 문자열에 중복이 있는지 없는지 판단해서 경우의 수를 카운트 해줬는데
 * 통과는 되었지만 효율이 좋지 않았다. 다른 사람의 풀이를 보니
 * 알파벳을 의미하는 길이가 26인 인트형 배열을 만들어 해당 알파벳 순서에 해당되는
 * 인덱스의 값을 1씩 증가시켜 카운트를 표시하였고
 * 나처럼 재귀호출을 하고 난 뒤 문자열의 중복을 판단하는 것이 아니라
 * 재귀를 보내기전에 먼저 확인을 하고 보내는 풀이를 하였다.
 */
public class Solution11 {
    static int length;
    static Map<Character, Integer> map = new HashMap<>();
    static List<Map.Entry<Character, Integer>> list;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine = bufferedReader.readLine();
        length = readLine.length();
        for (int i = 0; i < readLine.length(); i++) {
            char c = readLine.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        list = new ArrayList<>(map.entrySet());
        dfs(0, new StringBuilder());
        if (length == 1) {
            answer++;
        }
        System.out.println(answer);
    }

    static void dfs(int depth, StringBuilder builder) {
        if (depth > 1) { //문자열의 길이가 2이상일때
            if (!compare(builder)) { //이미 인접 문자가 서로 동일하면 그 이후는 볼 필요 없이 리턴
                return;
            }
            if (depth == length) { //저 위에 조건을 통과했다는건 인접한 문자가 없다는 뜻
                answer++; //이때 이제 입력받은 문자열과 길이가 같다는건 모든 문자를 다 사용했다는 것 이 경우의 수를 체크한다.
                return;
            }
        }

        for (Map.Entry<Character, Integer> entry : list) {//문자의 갯수를 카운트한 리스트를 순회하여
            if (entry.getValue() > 0) {//문자가 1개이상 있을때
                map.put(entry.getKey(), entry.getValue() - 1); //맵에 카운트를 1감소시키고
                dfs(depth + 1, new StringBuilder(builder).append(entry.getKey())); //매개변수로 넘어온 문자열에 해당 문자를 추가하여 재귀호출을 한다.
                map.put(entry.getKey(), entry.getValue() + 1); //재귀를 마치고 왔으면 해당 문자의 카운트를 다시 올려준다.
            }
        }
    }

    static boolean compare(StringBuilder builder) { //인접한 문자열이 중복인지 아닌지 구분하는 메서드
        for (int i = 0; i < builder.length() - 1; i++) {
            char c1 = builder.charAt(i);
            char c2 = builder.charAt(i + 1);
            if (c1 == c2) {
                return false;
            }
        }
        return true;
    }
}
