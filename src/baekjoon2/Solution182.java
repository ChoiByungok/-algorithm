package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2852 NBA 농구 (Silver3)
 * 그냥 생각나는대로 풀었던문제 은근히 신경쓸게 많아서 힘들었다.
 * 다른 사람의 풀이를 보니 모든 시간을 초로 바꾼다음
 * 마지막에 초를 분 초로 구분하는 풀이방법이 인상깊었다.
 */
public class Solution182 {
    static class Team {
        int num;
        int score;
        String scoringTime;
        String winningTime;

        public Team(int num, int score,String scoringTime, String winningTime) {
            this.num = num;
            this.score = score;
            this.scoringTime = scoringTime;
            this.winningTime = winningTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        Team team1 = new Team(1, 0, "00:00", "00:00");
        Team team2 = new Team(2, 0, "00:00", "00:00");

        for (int i = 0; i < N; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int team = Integer.parseInt(split[0]);
            String time = split[1];
            if (team == 1) {
                team1.score++;
                if (team1.score == team2.score) {
                    String[] t2MMSS = team2.scoringTime.split(":");
                    int t2M = Integer.parseInt(t2MMSS[0]);
                    int t2S = Integer.parseInt(t2MMSS[1]);
                    String[] MMSS = time.split(":");
                    int M = Integer.parseInt(MMSS[0]);
                    int S = Integer.parseInt(MMSS[1]);
                    if (t2S > S) {
                        M--;
                        S += 60;
                    }
                    calc(team2, t2M, t2S, M, S);
                }
                if (team1.score - team2.score > 1) {
                    continue;
                }
                team1.scoringTime = time;
            } else {
                team2.score++;
                if (team2.score == team1.score) {
                    String[] t1MMSS = team1.scoringTime.split(":");
                    int t1M = Integer.parseInt(t1MMSS[0]);
                    int t1S = Integer.parseInt(t1MMSS[1]);
                    String[] MMSS = time.split(":");
                    int M = Integer.parseInt(MMSS[0]);
                    int S = Integer.parseInt(MMSS[1]);
                    if (t1S > S) {
                        M--;
                        S += 60;
                    }
                    calc(team1, t1M, t1S, M, S);
                }
                if (team2.score - team1.score > 1) {
                    continue;
                }
                team2.scoringTime = time;
            }
        }

        if (team1.score > team2.score) {
            String[] split = team1.scoringTime.split(":");
            int M = Integer.parseInt(split[0]);
            int S = Integer.parseInt(split[1]);
            calc(team1, M, S, 47, 60);
        } else if (team2.score > team1.score) {
            String[] split = team2.scoringTime.split(":");
            int M = Integer.parseInt(split[0]);
            int S = Integer.parseInt(split[1]);
            calc(team2, M, S, 47, 60);
        }
        System.out.println(team1.winningTime);
        System.out.println(team2.winningTime);
    }

    private static void calc(Team team, int tM, int tS, int M, int S) {
        int newS = S - tS;
        int newM = M - tM;
        String[] split1 = team.winningTime.split(":");
        int WM = Integer.parseInt(split1[0]);
        int WS = Integer.parseInt(split1[1]);
        if (WS + newS >= 60) {
            newS = (WS + newS) % 60;
            newM = newM + WM + 1;
        } else {
            newS = WS + newS;
            newM = newM + WM;
        }
        team.winningTime = timeBuilder(newS, newM);
    }

    private static String timeBuilder(int newS, int newM) {
        StringBuilder stringBuilder = new StringBuilder();
        if (newM < 10) {
            stringBuilder.append(0).append(newM).append(":");
        } else {
            stringBuilder.append(newM).append(":");
        }

        if (newS == 0) {
            stringBuilder.append("00");
        } else if (newS < 10) {
            stringBuilder.append("0").append(newS);
        } else {
            stringBuilder.append(newS);
        }

        return stringBuilder.toString();
    }
}
