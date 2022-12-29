package Programmers2;

/**
 * 직사각형 넓이 구하기
 */
public class Solution4 {
    public int solution(int[][] dots) {

        int maxX = -257;
        int minX = 257;
        int maxY = -257;
        int minY = 257;
        int col = 0;
        for (int i = 0; i < dots.length * dots[0].length; i++) {
            if(i % 2 == 0){
                if(maxX < dots[col][i % dots[0].length]){
                    maxX = dots[col][i % dots[0].length];
                }
                if(minX > dots[col][i% dots[0].length]){
                    minX = dots[col][i % dots[0].length];
                }
            }else {
                if(maxY < dots[col][i % dots[0].length]){
                    maxY = dots[col][i % dots[0].length];
                }
                if(minY > dots[col][i % dots[0].length]){
                    minY = dots[col][i % dots[0].length];
                }
            }
            if(i % dots[0].length == 1){
                col++;
            }
        }

        return (maxX - minX) * (maxY - minY);
    }

    public static void main(String[] args) {
        int[][] dots ={{1,1},{2,1},{2,2},{1,2}};

        System.out.println(new Solution4().solution(dots));
    }
}
