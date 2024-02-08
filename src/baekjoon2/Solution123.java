package baekjoon2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 15685 드래곤 커브 (Gold3)
 * 시작점이 0,0이고 방향이 오른쪽이라면 시작점이 0,0 끝점이 0,1 이되는게 0세대 드래곤 커브이다.
 * 여기서 1세대로 진화하려면 해당 직선을 90도 시계방향으로 회전시킨뒤 끝점에 붙여야 한다.
 * 그러면 시작점이 0,0 끝점이 1,-1이 되는 1세대 드래곤 커브가 탄생한다.
 * 이러한 드래곤 커브가 여러개 있을때 드래곤 커브로 둘러싸인 1X1 크기의 정사각형의 갯수를 출력하는것이 이 문제의 목표이다.
 * 드래곤커브는 서로 겹칠 수 있다.
 * 문제 출력은 101*101 사이즈의 2차원 boolean 형 배열을 만들고 드래곤 커브가 지나간 좌표를 true 로 바꾼 뒤
 * 2중반복을 돌려 true 가 나온 좌표 기준으로 해당 좌표가 (x,y) 라고 했을 때
 * (x+1,y), (x, y+1), (x+1, y+1) 좌표가 모두 true 이면 정사각형 카운트를 1증가시키면 된다.
 * (여기서 주의 할 점은 현재좌표에서 +1 뒤의 좌표를 확인하므로 반복문을 맵 사이즈의 -1 까지만 반복시켜야 한다. 이거때문에 3번틀림)
 * 출력까지는 어떻게 해야할지 감을 잡았으나 문제는 드래곤 커브를 어떻게 진화시켜야 할지 이게 어려웠다.
 * 계속 좌표를 그려보면서 새로 생길 좌표들은 기존의 좌표들과 어떤상관관계가 있는지 끝없이 찾아내다가
 * 결국 상관관계를 발견하고 말았다.
 * 예를들어 1세대 드래곤커브의 좌표리스트가 (2,4) (1,4) (1,3) 이라고 했을때 여기서 끝점은 마지막 요소인 (1,3)이다
 * 그리고 여기서 역순으로 탐색하면서 새로운 좌표를 추가시키면된다.
 * 기준점 (1,3)은 (1,4) 보다 y가 1만큼작다
 * 그러면 다음에 올 좌표는 기준점(1,3) 에서 x 좌표를 1만큼 증가시킨다. 그러면 다음좌표는 (2,3)이 된다.
 * 그리고 계속 반복하여 기준점(1,3)과 (2,4) 를 비교한다. x가 1만큼 작고 y는 1만큼 작다,
 * 기준점 (1,3)에서 x가 1만큼 작으므로 y를 1만큼 줄이고 y가 1만큼 작으므로 x를 1만큼 늘린다.
 * 그러므로 그다음 좌표는 (2,2)가 된다.
 * 이렇게 (2,4) (1,4) (1,3) (2,3) (2,2) 의 좌표리스트를 가진 2세대 드래곤커브가 완성되는것이다.
 * 3세대 드래곤 커브도 마찬가지로 끝점(2,2) 부터 역순으로 좌표들을 비교해가며 새로 들어올 좌표를 추가하면 된다.
 * 정리하면
 * 기준점의 x가 비교할 좌표의 x보다 n만큼 작으면 새로운 좌표의 y는 기준점의 y보다 n만큼 작다.
 * 기준점의 x가 비교할 좌표의 x보다 n만큼 크다면 새로운 좌표의 y는 기준점의 y보다 n만큼 크다.
 * 기준점의 y가 비교할 좌표의 y보다 n만큼 작으면 새로운 좌표의 x는 기준점의 x보다 n만큼 크다.
 * 기준점의 y가 비교할 좌표의 y보다 n만큼 크다면 새로운 좌표의 x는 기준점의 x보다 n만큼 작다.
 * 의 상관관계를 갖게 된다.
 * 다른 사람의 풀이를 보았는데 나는 좌표값에 집중한 반면 다른사람들은 방향에 집중해서 문제를 해결하는 모습이었다.
 * 만약 0세대 방향이 → 라면 1세대는 → ↑ 고 2세대는 → ↑ ← ↑ 가 되며 3세대는 → ↑ ← ↑ ← ↓ ← ↑ 가된다. 이런식으로
 * 방향값을 먼저 계산한다음 시작지점부터 미리 계산한 방향값대로 움직이면서 칸을 채워나가는 방식이었다.
 * 확실히 내가 해결한 방법보다 간단하며 더 직관적이다. 역시 문제에서 괜히 방향값을 준게 아닌가보다.
 * 하지만 나만의 방식으로 문제를 해결하였으므로 성취감을 얻을 수 있는 문제였다.
 */
public class Solution123 {
    static boolean[][] map = new boolean[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
            List<int[]> dragonCurve = new ArrayList<>();
            int y = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            int dir = Integer.parseInt(tokenizer.nextToken());
            int gen = Integer.parseInt(tokenizer.nextToken());
            dragonCurve.add(new int[]{x, y});
            switch (dir) {
                case 0:
                    dragonCurve.add(new int[]{x, y + 1});
                    break;
                case 1:
                    dragonCurve.add(new int[]{x - 1, y});
                    break;
                case 2:
                    dragonCurve.add(new int[]{x, y - 1});
                    break;
                case 3:
                    dragonCurve.add(new int[]{x + 1, y});
                    break;
            }
            for (int j = 0; j < gen; j++) {
                int[] benchmark = dragonCurve.get(dragonCurve.size() - 1);
                int benchmarkX = benchmark[0];
                int benchmarkY = benchmark[1];
                int start = dragonCurve.size() - 2;
                for (int k = start; k >= 0; k--) {
                    int newX = benchmarkX;
                    int newY = benchmarkY;
                    int[] ints = dragonCurve.get(k);
                    int diffX = benchmarkX - ints[0];
                    int diffY = benchmarkY - ints[1];
                    if (diffX > 0) {
                        newY += Math.abs(diffX);
                    } else if (diffX < 0){
                        newY -= Math.abs(diffX);
                    }
                    if (diffY > 0) {
                        newX -= Math.abs(diffY);
                    } else if (diffY < 0) {
                        newX += Math.abs(diffY);
                    }
                    dragonCurve.add(new int[]{newX, newY});
                }
            }
            for (int[] ints : dragonCurve) {
                map[ints[0]][ints[1]] = true;
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j]
                && map[i][j + 1]
                && map[i + 1][j]
                && map[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
