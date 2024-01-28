package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 16165 걸그룹 마스터 준석이 (Silver3)
 * 그룹명을 키 그리고 해당 그룹의 구성원들을 리스트로 만들어 이름별 오름차순으로 정렬 한 뒤 해시맵에 넣어준다.
 * 그 이후 1이 들어오면 구성원의 그룹명을 출력해야 하고 0이 들어오면 그룹의 구성원들을 사전순으로 출력해야 한다.
 * 사전순 출력을 위해 정렬을 하였다.
 * 구성원 출력은 입력받은 키 값으로 리스트를 꺼내와 리스트를 순회하여 출력하면 되지만
 * 구성원 이름으로 해당 그룹명을 출력하는것은 매끄럽게 수행하기가 어렵다.
 * 나같은 경우는 해시맵의 키셋을 받아와 키셋을 순회하고
 * 그 키셋에서 나온 키값으로 또 리스트를 가져와 리스트를 순회하여 입력받은 값이 해당 리스트 안에 있으면 
 * 거기서 반복문을 탈출 시키고 그룹명을 출력하는 식으로 해결하였다.
 */
public class Solution112 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        Map<String, List<String>> girGroup = new HashMap<>();
        String[] nm = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        for (int i = 0; i < N; i++) {
            String groupName = bufferedReader.readLine();
            int population = Integer.parseInt(bufferedReader.readLine());
            List<String> names = new ArrayList<>();
            for (int j = 0; j < population; j++) {
                names.add(bufferedReader.readLine());
            }
            Collections.sort(names);
            girGroup.put(groupName, names);
        }

        for (int i = 0; i < M; i++) {
            String name = bufferedReader.readLine();
            String type = bufferedReader.readLine();
            boolean exit = false;
            if (type.equals("1")) {
                for (String group : girGroup.keySet()) {
                    for (String member : girGroup.get(group)) {
                        if (member.equals(name)) {
                            answer.append(group).append("\n");
                            exit = true;
                            break;
                        }
                    }
                    if (exit) {
                        break;
                    }
                }
            } else {
                for (String s : girGroup.get(name)) {
                    answer.append(s).append("\n");
                }
            }
        }
        System.out.println(answer);
    }
}
