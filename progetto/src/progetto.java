
// periodo frazionario
import java.util.Scanner;

/* 
 * Pseudo:
 *  //inizializzazione ambiente lavoro
 *  Tmin;
 *  inizializzaFile();
 *  //prove
 *  for (i: 0->99){
 *      s= generaStringa();
 * 
 *      //raccolta dati Naive;
 *      start= now();
 *      naivecounter=0;
 *      do{
 *          Naive(s)
 *          end= now();
 *          }while( end-start < Tmin)
 *      elapsed= end-start;
 *      log(naiveRun);
 * 
 *      //raccolta dati Smart;
 *      start= now();
 *      naivecounter=0;
 *      do{
 *          Naive(s)
 *          end= now();
 *          }while( end-start < Tmin)
 *      elapsed= end-start;
 *      log(smartRun);
 * 
 * }
*/
public class Progetto {

    public static void main(String[] args) {

        //calcolo 
        double tmin = Chrono.tMin();

        Logger.initializeFile();
        for (int j = 0; j < 10; j++) {
            String s = StringGenerator.generateString(j);
            int i = 0;

            long start= Chrono.now();
            long end= 0;
            long fractionalperiod= -1;

            do {
                fractionalperiod = Algos.periodNaive(s);
                end= Chrono.now();
                i++;

            } while (end-start < tmin);
            long elapsed= end - start; 
            System.out.println(i);
            Logger.logPerformanceString("naive", s.length(), elapsed/i, fractionalperiod);

        }

    }
}
