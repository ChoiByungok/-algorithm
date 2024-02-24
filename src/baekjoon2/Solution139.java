package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1063 킹 (Silver3)
 * R : 한 칸 오른쪽으로
 * L : 한 칸 왼쪽으로
 * B : 한 칸 아래로
 * T : 한 칸 위로
 * RT : 오른쪽 위 대각선으로
 * LT : 왼쪽 위 대각선으로
 * RB : 오른쪽 아래 대각선으로
 * LB : 왼쪽 아래 대각선으로
 * 이 문제는 명령어가 들어왔을 때 해당 명령어에 의해서 킹이나 돌이 체스판을 벗어나는 경우만 잘 걸러주면 해결할수 있는문제
 */
public class Solution139 {
    static class Piece {
        char x;
        int y;

        public Piece(char x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(String com, Piece stone) {
            if (com.equals("R")) {
                if (x == 'H') {
                    return;
                }
                if (x + 1 == stone.x && y == stone.y) {
                    if (stone.x == 'H') {
                        return;
                    }
                    x++;
                    stone.x++;
                } else {
                    x++;
                }
                return;
            }

            if (com.equals("L")) {
                if (x == 'A') {
                    return;
                }
                if (x - 1 == stone.x && y == stone.y) {
                    if (stone.x == 'A') {
                        return;
                    }
                    x--;
                    stone.x--;
                } else {
                    x--;
                }
                return;
            }

            if (com.equals("B")) {
                if (y == 1) {
                    return;
                }
                if (x == stone.x && y - 1 == stone.y) {
                    if (stone.y == 1) {
                        return;
                    }
                    y--;
                    stone.y--;
                } else {
                    y--;
                }
                return;
            }

            if (com.equals("T")) {
                if (y == 8) {
                    return;
                }
                if (x == stone.x && y + 1 == stone.y) {
                    if (stone.y == 8) {
                        return;
                    }
                    y++;
                    stone.y++;
                } else {
                    y++;
                }
                return;
            }

            if (com.equals("RT")) {
                if (x == 'H' || y == 8) {
                    return;
                }
                if (x + 1 == stone.x && y + 1 == stone.y) {
                    if (stone.x == 'H' || stone.y == 8) {
                        return;
                    }
                    x++;
                    y++;
                    stone.x++;
                    stone.y++;
                } else {
                    x++;
                    y++;
                }
                return;
            }

            if (com.equals("LT")) {
                if (x == 'A' || y == 8) {
                    return;
                }
                if (x - 1 == stone.x && y + 1 == stone.y) {
                    if (stone.x == 'A' || stone.y == 8) {
                        return;
                    }
                    x--;
                    y++;
                    stone.x--;
                    stone.y++;
                } else {
                    x--;
                    y++;
                }
                return;
            }

            if (com.equals("RB")) {
                if (x == 'H' || y == 1) {
                    return;
                }
                if (x + 1 == stone.x && y - 1 == stone.y) {
                    if (stone.x == 'H' || stone.y == 1) {
                        return;
                    }
                    x++;
                    y--;
                    stone.x++;
                    stone.y--;
                } else {
                    x++;
                    y--;
                }
                return;
            }

            if (com.equals("LB")) {
                if (x == 'A' || y == 1) {
                    return;
                }
                if (x - 1 == stone.x && y - 1 == stone.y) {
                    if (stone.x == 'A' || stone.y == 1) {
                        return;
                    }
                    x--;
                    y--;
                    stone.x--;
                    stone.y--;
                } else {
                    x--;
                    y--;
                }
            }
        }
    }
    static Piece king;
    static Piece stone;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String[] split = bufferedReader.readLine().split(" ");
        king = new Piece(split[0].charAt(0), split[0].charAt(1) - '0');
        stone = new Piece(split[1].charAt(0), split[1].charAt(1) - '0');
        int N = Integer.parseInt(split[2]);
        for (int i = 0; i < N; i++) {
            king.move(bufferedReader.readLine(), stone);
        }
        answer.append(king.x).append(king.y).append("\n").append(stone.x).append(stone.y);
        System.out.println(answer);
    }
}
