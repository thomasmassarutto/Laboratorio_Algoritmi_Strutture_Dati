/* 
 * Classe Algos:
 * 
 * Questa classe contiene gli algoritmi inerenti al progetto: PeriodNaive e PeriodSmart
 * Gli algoritmi servono a calcolare il periodo frazionario di una stringa
 * 
 * PUBBLICO:
 * long periodNaive(...): periodo stringa in tempo quadratico 
 *              String stringa: 
 * long periodSmart(...):
 *              String stringa:
 * 
*/

public class Algos {
    public static int ERR = -1;

    public static int periodNaive(String stringa) {
        
        int n= stringa.length();

        for (int p=1; p<=n;p++){
            String head=stringa.substring(0, n-p);
            String tail=stringa.substring(p, n);

            if (head.equals(tail)){
                return p;
            }

        }
        
        
        return ERR;
    }

    public static int periodSmart(String s) {
        int n = s.length();
        int[] r = new int[n + 1];
        r[1] = 0;

        for (int i = 2; i <= n; i++) {
            int j = r[i - 1];
            while (j > 0 && s.charAt(i - 1) != s.charAt(j)) {
                j = r[j];
            }
            if (s.charAt(i - 1) == s.charAt(j)) {
                r[i] = j + 1;
            } else {
                r[i] = 0;
            }
        }

        int maxBordo = r[n];
        int periodoFrazionario = n - maxBordo;

        return periodoFrazionario;
    }
}
