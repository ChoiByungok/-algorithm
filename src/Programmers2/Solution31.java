package Programmers2;
/**
 * 달리기 경주
 */

import java.util.*;

public class Solution31 {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rank = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }

        for (String player : callings) {
            Integer currentRank = rank.get(player);
            String beforePlayer = players[currentRank - 1];

            players[currentRank - 1] = player;
            players[currentRank] = beforePlayer;

            rank.put(player, currentRank - 1);
            rank.put(beforePlayer, currentRank);
        }

        return players;
    }

    public static void main(String[] args) {
        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        System.out.println(Arrays.toString(new Solution31().solution(players, callings)));
    }
}
