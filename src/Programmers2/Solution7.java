package Programmers2;

/**
 * 로그인 성공?
 */
public class Solution7 {
    public String solution(String[] id_pw, String[][] db) {

        for (int i = 0; i < db.length; i++) {
            for (int j = 0; j < db[i].length; j++) {
                if(id_pw[0].equals(db[i][0])){
                    if(id_pw[1].equals(db[i][1])){
                        return "login";
                    }else {
                        return "wrong pw";
                    }
                }
            }
        }
        return "fail";
    }

    public static void main(String[] args) {
        String[] id_pw1 = {"meosseugi","1234"};
        String[][] db1 ={{"rardss","123"},{"yyoom","1234"},{"meosseugi","1234"}};

        String[] id_pw2 = {"programmer01","15789"};
        String[][] db2 ={{"programmer02","111111"},{"programmer00","134"},{"programmer01","1145"}};

        String[] id_pw3 = {"rabbit04","98761"};
        String[][] db3 ={{"jaja11","98761"},{"krong0313","29440"},{"rabbit00","111333"}};

        System.out.println(new Solution7().solution(id_pw3,db3));

    }
}
