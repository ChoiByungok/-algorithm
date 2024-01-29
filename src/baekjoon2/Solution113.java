package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 9536 여우는 어떻게 울지? (Silver3)
 * 전체 울음소리에서 동물별 소리들을 제외하면 그것이 여우의 소리이다.
 * 내가 처음에 생각한 풀이방법은 toot woof wa ow ow ow pa blub blub pa toot pa blub pa pa ow pow toot 라고 입력받으면
 * 맵에 넣고 카운트를 센다 그러면 toot=3, woof=1, wa=1, ow=4, pa=5, blub=3, pow=1 로 정리될 것이고
 * dog goes woof
 * fish goes blub
 * elephant goes toot
 * seal goes ow
 * 라고 동물별 소리를 입력받으면
 * wa=1, pa=5, pow=1 가 맵에 남게 되고
 * 그 뒤에 wa 1번 반복 pa 5번 반복 pow 1번반복 하면 답이 나올줄 알았다. 근데 16퍼센트에서 빠꾸를 먹었다.
 * 처음 입력받은 전체소리의 순서를 무시했기 때문이라고 생각했다.
 * 입력받은 동물별 소리를 제외했을때 toot=3, blub=3 이 남았다 가정해보자
 * 이때 정답은 toot blub blub toot blub toot 가 나와야 하는데
 * 내 풀이대로 풀면 toot toot toot blub blub blub 가나오게 된다.
 * 그래서 나는 맵의 키 밸류값을 String, ArrayList 로 두고 각 키값이 어떤 인덱스에 위치해있는지 저장했다.
 * 그러면 {toot=[0, 10, 17], woof=[1], wa=[2], ow=[3, 4, 5, 15], pa=[6, 9, 11, 13, 14], blub=[7, 8, 12], pow=[16]}
 * 형태로 저장된다. 여기서 여우의 소리만 걸러내면 {wa=[2], pa=[6, 9, 11, 13, 14], pow=[16]} 가 남게된다.
 * 각 키값별 리스트의 인덱스 요소들은 오름차순 정렬되어 있기 때문에 맨 첫번째 요소만 비교하여 가장 최솟값을 찾아낼 수 있다.
 * 키별 리스트의 첫번째 인덱스들을 추출하면 wa=2 pa=6 pow=16 이고 이중 최솟값은 wa의 2이다.
 * wa를 출력시키고 리스트가 비었으니 키 자체를 삭제시켜준다.
 * 그러면 {pa=[6, 9, 11, 13, 14], pow=[16]} 가 남게된다.
 * 여기서 다시 반복하면 pa=6 pow=16 최솟값은 pa의 6 이므로 pa를 출력시켜주고
 * 리스트에서 지워준다. 그러면 {pa=[9, 11, 13, 14], pow=[16]} 가 남게 된다.
 * 이것을 맵이 빌때까지 반복해주면 된다.
 * 쉬울줄 알았는데 생각보다 복병이었던 문제였다.
 */
public class Solution113 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            String[] sounds = bufferedReader.readLine().split(" ");
            Map<String, ArrayList<Integer>> map = new LinkedHashMap<>();
            for (int j = 0; j < sounds.length; j++) {
                if (map.containsKey(sounds[j])) {
                    map.get(sounds[j]).add(j);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(j);
                    map.put(sounds[j], list);
                }
            }
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine.equals("what does the fox say?")) {
                    break;
                }
                String[] split = readLine.split(" ");
                for (int j = 2; j < split.length; j++) {
                    map.remove(split[j]);
                }
            }
            List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<>(map.entrySet());
            /*int repeat = 0;
            for (Map.Entry<String, ArrayList<Integer>> stringArrayListEntry : list) {
                repeat += stringArrayListEntry.getValue().size();
            }
            for (int j = 0; j < repeat; j++) {
                int min = Integer.MAX_VALUE;
                int index = 0;
                for (int k = 0; k < list.size(); k++) {
                    Integer first = list.get(k).getValue().get(0);
                    if (first < min) {
                        min = first;
                        index = k;
                    }
                }

                if (list.get(index).getValue().size() == 1) {
                    answer.append(list.get(index).getKey()).append(" ");
                    list.remove(index);
                } else {
                    answer.append(list.get(index).getKey()).append(" ");
                    list.get(index).getValue().remove(0);
                }
            }*/
            while (!map.isEmpty()) {
                int min = Integer.MAX_VALUE;
                int index = 0;
                for (int k = 0; k < list.size(); k++) {
                    Integer first = list.get(k).getValue().get(0);
                    if (first < min) {
                        min = first;
                        index = k;
                    }
                }
                if (list.get(index).getValue().size() == 1) {
                    answer.append(list.get(index).getKey()).append(" ");
                    map.remove(list.get(index).getKey());
                    list.remove(index);
                } else {
                    answer.append(list.get(index).getKey()).append(" ");
                    list.get(index).getValue().remove(0);
                }
            }
        }
        System.out.println(answer);
    }
}
