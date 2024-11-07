package Programmers3;

import java.util.Arrays;

/**
 * 공원산책 Lv.1
 * 구현의 정석, 문제에서 시키는대로 그대로 구현함
 * 내가 원하는대로 구현했고 그대로 실행이 되었고 그대로 정답이되버린 아주 기분이가 좋은 문제다.
 */
public class Solution53 {

    public int[] solution(String[] park, String[] routes) {
        int N = park.length;
        int M = park[0].length();
        int nowX = 0;
        int nowY = 0;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                char c = park[i].charAt(j);
                if (c == 'S') {
                    nowX = i;
                    nowY = j;
                }
            }
        }
        for (String route : routes) {
            String[] split = route.split(" ");
            String dir = split[0];
            int dis = Integer.parseInt(split[1]);
            if (rangeCheck(dir, dis, nowX, nowY, N, M) && isObstacle(dir, dis, nowX, nowY, park)) {
                switch (dir) {
                    case "N" :
                        nowX -= dis;
                        break;
                    case "E" :
                        nowY += dis;
                        break;
                    case "S" :
                        nowX += dis;
                        break;
                    case "W" :
                        nowY -= dis;
                        break;
                }
            }
        }

        return new int[] {nowX, nowY};
    }

    static boolean rangeCheck(String dir, int dis, int x, int y, int N, int M) {
        boolean check = false;
        switch (dir) {
            case "N" :
                if (x - dis >= 0) {
                    check = true;
                }
                break;
            case "E" :
                if (y + dis < M) {
                    check = true;
                }
                break;
            case "S" :
                if (x + dis < N) {
                    check = true;
                }
                break;
            case "W" :
                if (y - dis >= 0) {
                    check = true;
                }
                break;
        }
        return check;
    }

    static boolean isObstacle(String dir, int dis, int x, int y, String[] park) {
        boolean check = true;
        switch (dir) {
            case "N" :
                for (int i = 1; i <= dis; i++) {
                    if (park[x - i].charAt(y) == 'X') {
                        check = false;
                        break;
                    }
                }
                break;
            case "E" :
                for (int i = 1; i <= dis; i++) {
                    if (park[x].charAt(y + i) == 'X') {
                        check = false;
                        break;
                    }
                }
                break;
            case "S" :
                for (int i = 1; i <= dis; i++) {
                    if (park[x + i].charAt(y) == 'X') {
                        check = false;
                        break;
                    }
                }
                break;
            case "W" :
                for (int i = 1; i <= dis; i++) {
                    if (park[x].charAt(y - i) == 'X') {
                        check = false;
                        break;
                    }
                }
                break;
        }
        return check;
    }

    public static void main(String[] args) {
        Solution53 solution53 = new Solution53();
        System.out.println(Arrays.toString(solution53.solution(new String[] {"SOO","OOO","OOO"}, new String[] {"E 2","S 2","W 1"})));
        System.out.println(Arrays.toString(solution53.solution(new String[] {"SOO","OXX","OOO"}, new String[] {"E 2","S 2","W 1"})));
        System.out.println(Arrays.toString(solution53.solution(new String[] {"OSO","OOO","OXO","OOO"}, new String[] {"E 2","S 3","W 1"})));
    }
}
