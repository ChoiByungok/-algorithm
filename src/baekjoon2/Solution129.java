package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2636 치즈 (Gold4)
 * 치즈는 외부공기와 맞닿아 있는 부분이 1시간 후에 녹는다.
 * 치즈를 둘러 쌓고있는 공기는 외부공기이고 치즈안에 둘러쌓인 공기도 존재하는데 해당 공기(치즈 속 공기)와 맞닿는 치즈는 녹지 않지만
 * 외부가 녹아서 외부공기와 치즈안에 둘러쌓인 공기가 닿게 된다면 치즈안에 둘러쌓인 공기도 외부공기가 되버린다.
 * 입력을 받았을 때 치즈가 전부 녹는 시간과 치즈가 전부 녹기 1시간 전에 치즈의 면적을 구하라
 * 이게 외부 공기와 내부공기를 구분하지 않았다면 그냥 풀었을 문제였을 텐데
 * 어떻게 해야 공기가 외부공기인지 내부공기인지 구분할수 있을까
 * 한참 고민하다가 구글링하여 힌트를 보았는데 외부공기 내부공기를 구분할 필요가 없었다.
 * 문제에서 테두리는 항상 치즈가 놓여있지 않기 때문에 0,0 좌표부터 넓이우선탐색하여
 * 치즈를 만나게 되면 해당 치즈를 녹이고 continue 하면 된다.
 * 근데 여기서 중요한건 치즈를 만나자마자 녹이게 되면 다음 탐색에서 그 위치에 가서 그 내부에 있는 치즈를 녹일수도 있으니
 * 이 치즈는 곧 녹을 치즈이다 표시를 한 후
 * 넓이 우선탐색을 끝낸 뒤 표시한 부분을 녹이면 된다.
 * 내부공기는 구분할 필요가 없는 이유는 0,0부터 탐색을 하기때문에 내부공기가 존재하여도 그 좌표에는 도달할 수 없기 때문이다.
 * 나같은 경우는 치즈라는 객체를 만들고 해당 객체가 치즈인지 아닌지 구분을 하는 boolean 타입의 isCheese 변수와
 * 치즈일시 해당 치즈가 몇시간 후에 녹는지 체크하는 int 타입의 meltingTime 이라는 변수를 두었다.
 * 1씩 증가하는 변수를 반복문 내부에 두어서 bfs탐색을 하면 1시간 후에 녹아야 하는 치즈들의 meltingTime 은 1이될 것이다.
 * 그 후 다시 2차원 배열을 순회하여 meltingTime 이 1인 치즈들을 false 로 바꾸어주면된다.
 * 초기 반복문의 탈출 조건은 2차원 배열을 순회하여 치즈가 없을때까지이다.
 * 그렇게 반복하다보면 더이상 맵에는 치즈가 남지 않게 되고 치즈가 다 녹을때까지 몇시간이 걸렸는지 나오게 된다.
 * 그 후 다시한번 2차원 배열을 순회하여 나온시간과 같은 값을가진 meltingTime 이 몇개 있는지 체크하면 된다.
 * 왜나하면 치즈가 다 녹기 1시간전에 치즈의 면적을 출력해야 하기 때문이다.
 */
public class Solution129 {
    static class Cheese {
        boolean isCheese;
        int meltingTime;

        public Cheese(boolean isCheese, int meltingTime) {
            this.isCheese = isCheese;
            this.meltingTime = meltingTime;
        }
    }
    static boolean[][] visited;
    static Cheese[][] map;
    static int row;
    static int col;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        row = Integer.parseInt(tokenizer.nextToken());
        col = Integer.parseInt(tokenizer.nextToken());
        map = new Cheese[row][col];
        for (int i = 0; i < row; i++) {
            tokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = tokenizer.nextToken().equals("1") ?
                        new Cheese(true, - 1) :
                        new Cheese(false, 0);
            }
        }
        int hour = 0;
        while (cheeseCount() != 0) {
            hour++;
            bfs(hour);
            melt(hour);
        }
        answer.append(hour).append("\n").append(oneHourBefore(hour));
        System.out.println(answer);
    }

    private static void bfs(int hour) {
        visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            if (visited[x][y]) {
                continue;
            } else {
                visited[x][y] = true;
            }

            if (map[x][y].isCheese) {
                map[x][y].meltingTime = hour;
                continue;
            }

            if (x + 1 < row && !visited[x + 1][y]) {
                queue.add(new int[] {x + 1, y});
            }
            if (x - 1 >= 0 && !visited[x - 1][y]) {
                queue.add(new int[] {x - 1, y});
            }
            if (y + 1 < col && !visited[x][y + 1]) {
                queue.add(new int[] {x, y + 1});
            }
            if (y - 1 >= 0 && !visited[x][y - 1]) {
                queue.add(new int[] {x, y - 1});
            }
        }
    }
    static int cheeseCount() {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j].isCheese) {
                    count++;
                }
            }
        }
        return count;
    }
    static void melt(int hour) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j].meltingTime == hour) {
                    map[i][j].isCheese = false;
                }
            }
        }
    }
    static int oneHourBefore (int hour) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j].meltingTime == hour) {
                    count++;
                }
            }
        }
        return count;
    }
}
