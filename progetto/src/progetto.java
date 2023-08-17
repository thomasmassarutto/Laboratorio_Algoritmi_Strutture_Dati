/*PERIODO FRAZIONARIO DI UNA STRINGA*/
/* Questa classe contiene il main del progetto.
* 
*
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
