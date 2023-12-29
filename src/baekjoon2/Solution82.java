package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 19583 싸이버개강총회
 * 처음에는 시간이 주어지길래 DATE 형으로 변환하여 풀어야 하나 고민을 함 근데 생각해보니 단순 문자열 비교만 하면 될거같아서
 * 그냥 주어지는 문자열로 비교를 하게 됨 사실 DATE 형으로 풀었다가 시간초과 남
 * HashMap 자료구조에 이름을 key 값으로 두고 시간을 value 값으로 두고
 * 출석은 맵에 값이 없으면 출석으로 간주함 단 개강총회 시간 이전에 들어온 값만 저장했음
 * 퇴장은 맵에 키 값이 있는 사람만 즉 출석을 한 사람만 대상으로 퇴장시간을 계산했음
 * 그 중에서 개강총회종료시간과 같거나 크고 스트리밍 종료시간 보다는 작거나 같은 사람만 맵에 저장함
 * 그러면 맵에는 제 시간에 출석은 했지만 퇴장을 못한 사람과 제 시간에 출석을 하고 제 시간에 퇴장을 한사람만 남게됨
 * 이제 맵을 순회하여 퇴장을 못한 사람만 걸러내면 답을 도출해 낼 수 있음
 */
public class Solution82 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] seq = bufferedReader.readLine().split(" ");
        String S = seq[0];
        String E = seq[1];
        String Q = seq[2];
        Map<String, String> map = new HashMap<>();
        int count = 0;

        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.equals("")) {
                break;
            }
            String[] split = readLine.split(" ");
            String time = split[0];
            String name = split[1];
            if (map.containsKey(name)) {
                if ((time.equals(E) || time.compareTo(E) > 0) && (time.compareTo(Q) < 0 || time.equals(Q))) {
                    map.put(name, time);
                }
            } else {
                if (time.equals(S) || time.compareTo(S) < 0) {
                    map.put(name, time);
                }
            }
        }

        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, String> entry : list) {
            String log = entry.getValue();
            if ((log.equals(E) || log.compareTo(E) > 0) && (log.compareTo(Q) < 0 || log.equals(Q))) {
                count++;
            }
        }
        System.out.println(count);
    }
}
