import java.util.Scanner;
import static java.lang.Math.max;

public class es25 {
    public static int editing(String stringa1, String stringa2){
        int n = stringa1.length();
        int m = stringa2.length();

        int[][] matrice = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    matrice[i][j] = j;
                } else if (j == 0) {
                    matrice[i][j] = i;
                } else if (stringa1.charAt(i - 1) == stringa2.charAt(j - 1)) {
                    matrice[i][j] = matrice[i - 1][j - 1];
                } else {
                    matrice[i][j] = 1 + Math.min(Math.min(matrice[i - 1][j], matrice[i][j - 1]), matrice[i - 1][j - 1]);
                }
            }
        }
        return matrice[n][m];
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String stringa1 = input.nextLine();
        String stringa2 = input.nextLine();
        System.out.print(editing(stringa1, stringa2));
    }
}
