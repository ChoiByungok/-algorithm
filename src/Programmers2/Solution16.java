package Programmers2;

import java.util.*;

/**
 * 등수매기기
 */
public class Solution16 {
    static class Rank{
        public int index;
        public double average;
        public int rank;

        public Rank(int index, double average) {
            this.index = index;
            this.average = average;
        }

        public int getIndex() {
            return index;
        }

        public double getAverage() {
            return average;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Rank{" +
                    "index=" + index +
                    ", average=" + average +
                    ", rank=" + rank +
                    '}';
        }
    }

    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];
        List<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            double average = (double) (score[i][0] + score[i][1]) / 2;
            Rank rank = new Rank(i, average);
            ranks.add(rank);
        }
        for (int i = 0; i < ranks.size(); i++) {
            int rank = 1;
            for (Rank value : ranks) {
                if (ranks.get(i).getAverage() < value.getAverage()) {
                    rank++;
                }
            }
            ranks.get(i).setRank(rank);
        }
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ranks.get(i).getRank();
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] score1 = {{80,70},{90,50},{40,70},{50,80}};
        int[][] score2 = {{80,70},{70,80},{30,50},{90,100},{100,90},{100,100},{10,30}};

        System.out.println(Arrays.toString(new Solution16().solution(score1)));
    }
}
