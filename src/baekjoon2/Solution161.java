package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 20006 랭킹전 대기열 (Silver2)
 * 나는 M 명의 플레이어가 모이면 바로 시작하면서 출력해주면 되는줄 알았는데
 * 예를 들어
 * 2 2
 * 30 a
 * 10 b
 * 15 c
 * 이렇게 주어지면
 * Started!
 * 10 b
 * 15 c
 * Waiting!
 * 30 a
 * 이렇게 제출 하면 되는줄 알았는데
 * 30 a 가 먼저 입력되어 먼저 방을 만들었으니
 * Waiting!
 * 30 a
 * Started!
 * 10 b
 * 15 c
 * 라고 제출을 해야 했던 거임
 */
public class Solution161 {
    static class Player {
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] pm = bufferedReader.readLine().split(" ");
        int P = Integer.parseInt(pm[0]);
        int M = Integer.parseInt(pm[1]);
        List<List<Player>> list = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int level = Integer.parseInt(split[0]);
            String nickname = split[1];
            Player player = new Player(level, nickname);
            if (list.isEmpty()) {
                List<Player> players = new ArrayList<>();
                players.add(player);
                list.add(players);
            } else {
                boolean attend = false;
                for (List<Player> players : list) {
                    Player manager = players.get(0);
                    if (manager.level - 10 <= player.level && manager.level + 10 >= player.level && players.size() < M) {
                        players.add(player);
                        attend = true;
                        break;
                    }
                }
                if (!attend) {
                    List<Player> players = new ArrayList<>();
                    players.add(player);
                    list.add(players);
                }
            }
        }

        for (List<Player> players : list) {
            players.sort(Comparator.comparing(o -> o.nickname));
            if (players.size() == M) {
                answer.append("Started!").append("\n");
                for (Player player : players) {
                    answer.append(player.level).append(" ").append(player.nickname).append("\n");
                }
            } else {
                answer.append("Waiting!").append("\n");
                for (Player player : players) {
                    answer.append(player.level).append(" ").append(player.nickname).append("\n");
                }
            }
        }

        System.out.println(answer);
    }
}
