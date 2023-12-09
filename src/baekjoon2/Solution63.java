package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1043 거짓말
 * 입력받은 값을 돌면서 뒤에파티에서 진실을 아는자가 나온다면 해당 파티 구성원들은 진실을 아는자가 되어
 * 이미 앞에서 진행한 파티에서 진실을 몰랐더라도 거짓말을 하면 안되는 파티로 구성해야 함
 * 내 짧은 생각으로는 그냥 boolean 배열을 만들어서 진실을 아는자는 true로 바꾸고
 * 입력받은 값에 진실을 아는자가 있으면 카운트를 세면 된다고 생각했는데 앞에서 입력 받은 값에서는 false였던 사람이
 * 뒤에서 true로 바뀌어 버리면 이미 올라가버린 카운터를 어떻게 줄일지 도저히 감이 잡히지 않아서 1시간동안 짱구 굴리다가
 * 답지봤는데 답지를 봐도 이해가 가지않음 대충 진실을 모르는자와 진실을 아는자가 같은 파티를 진행했을 때 진실을 모르는자의 부모노드를
 * 진실을 아는자의 번호로 매기고 입력값을 저장해 놓은 뒤 입력값을 한번더 반복하여 카운트를 세며 진행하는 문제인거같음
 */
public class Solution63 {
    static int[] peoples;
    static List<Integer> truthPeople = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int answer = 0;
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);
        List<List<Integer>> memories = new ArrayList<>();
        peoples = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            peoples[i] = i;
        }
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        int truth = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < truth; i++) {
            truthPeople.add(Integer.parseInt(tokenizer.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            int repeat = Integer.parseInt(tokenizer.nextToken());
            List<Integer> list = new ArrayList<>();
            int a = Integer.parseInt(tokenizer.nextToken());
            list.add(a);
            for (int j = 1; j < repeat; j++) {
                int b = Integer.parseInt(tokenizer.nextToken());
                union(a, b);
                list.add(b);
            }
            memories.add(list);
        }

        for (int i = 0; i < M; i++) {
            boolean flag = false;
            for (Integer integer : memories.get(i)) {
                if (truthPeople.contains(find(integer))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer++;
            }
        }
        System.out.println(answer);
    }
    static int find(int a) {
        if (a == peoples[a]) {
            return a;
        } else {
            return find(peoples[a]);
        }
    }

    static void union(int a, int b) {
        int newA = find(a);
        int newB = find(b);
        if (truthPeople.contains(newB)) {
            int tmp = newA;
            newA = newB;
            newB = tmp;
        }
        peoples[newB] = newA;
    }
}
