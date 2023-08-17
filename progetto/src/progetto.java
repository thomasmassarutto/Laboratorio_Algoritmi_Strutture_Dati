
// periodo frazionario

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

        double tmin = Chrono.tMin();

        Logger.initializeFile();

        for (int run = 1; run <= 5; run++) {
            for (int i = 0; i < 100; i++) {
                System.out.println("run: "+ run +" ite: " + i);

                String s = StringGenerator.generateString(i);

                int iterations = 0;
                long fractionalperiod = -1;
                long end = -1;
                long elapsed = -1;

                long start = Chrono.now();
                do {
                    fractionalperiod = Algos.periodNaive(s);
                    end = Chrono.now();
                    iterations++;

                } while (end - start < tmin);
                elapsed = end - start;
                Logger.logPerformanceString("naive", s.length(), elapsed / iterations, fractionalperiod, run);

                iterations = 0;
                fractionalperiod = -1;
                end = -1;
                elapsed = -1;

                start = Chrono.now();
                do {
                    fractionalperiod = Algos.periodSmart(s);
                    end = Chrono.now();
                    iterations++;

                } while (end - start < tmin);
                elapsed = end - start;
                Logger.logPerformanceString("smart", s.length(), elapsed / iterations, fractionalperiod,run);
            }
        }

    }

}
