/* 
 * Classe Algos:
 * 
 * Questa classe contiene gli algoritmi inerenti al progetto: PeriodNaive e PeriodSmart
 * 
 * PUBBLICO:
 * long periodNaive(...): periodo stringa in tempo quadratico 
 *              String stringa: 
 * long periodSmart(...):
 *              String stringa:
 * 
*/

public class Algos {

    public static int periodNaive(String stringa) {
        
        int n= stringa.length();

        for (int p=1; p<=n;p++){
            String head=stringa.substring(0, n-p);
            String tail=stringa.substring(p, n);

            if (head.equals(tail)){
                return p;
            }

        }

        return 1;
    }

    public static long periodSmart(String stringa) {
        long periodo=0;

        return periodo;
    }
}
