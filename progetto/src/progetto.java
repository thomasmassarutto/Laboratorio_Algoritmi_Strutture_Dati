
// periodo frazionario
import java.util.Scanner;

/* 
 * 
 * Pseudo raccolta dati
 * 
 * test{
 *  stringa= generastringa()
 *  startCrono()
 *  pariodoNaive(stringa)
 *  endChrono()
 *  logdati()
 *  startCrono()
 *  pariodoSmart(stringa)
 *  endChrono()  
 *  logdati()
 * }
 * 
 * 
*/
public class progetto {
    
    public static void main(String[] args) {

        Logger.initializeFile();

        for (int i = 0; i < 10; i++) {
            System.out.println("Gen Str");
            String s = StringGenerator.generateString();
            System.out.println("start");
            Chrono.startChrono();
            int res = Algos.periodNaive(s);
            Chrono.endChrono();
            long elapsedtime= Chrono.elapsedTime();
            System.out.println("Log");
            Logger.logPerformanceString(i, "naive", s.length(), elapsedtime, 0);
        }


    }
}
