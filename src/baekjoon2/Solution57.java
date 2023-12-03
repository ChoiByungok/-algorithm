package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 13414 수강신청
 * 처음에 리스트를 이용해서 풀어보았다가 입력받은 학생번호가 리스트에 포함 되어있으면 해당 코드를 맨 뒤로 옮겨가면서 풀었는데 시간 초과가 떴다.
 * 그래서 해시맵을 이용해서 풀어보려고 했는데 IndexOutOfBoundsException 이 발생함 해시맵으로 인해 줄어들 길이보다 수강인원의 숫자가 더 크기때문에
 * 발생하는듯 싶다. 수강인원의 숫자가 리스트의 길이보다 클때 작을때 구분을 해서 예외처리를 하니깐 통과되었다.
 */
public class Solution57 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] kl = bufferedReader.readLine().split(" ");
        StringBuilder answer = new StringBuilder();
        int K = Integer.parseInt(kl[0]);
        int L = Integer.parseInt(kl[1]);
        Map<String, Integer> map = new HashMap<>();

        for (int i = 1; i <= L; i++) {
            String code = bufferedReader.readLine();
            if (map.containsKey(code)) {
                map.put(code, i);
            } else {
                map.put(code, i);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        if (K > list.size()) {
            for (Map.Entry<String, Integer> stringIntegerEntry : list) {
                answer.append(stringIntegerEntry.getKey()).append("\n");
            }
        } else {
            for (int i = 0; i < K; i++) {
                answer.append(list.get(i).getKey()).append("\n");
            }
        }
        System.out.println(answer);
    }
}
