/*PERIODO FRAZIONARIO DI UNA STRINGA*/
/* Questa classe contiene il main del progetto.
* 
* Testa l'implementazione dei due algoritmi PeriodNaive e Periodsmart e prende nota dei tempi medi d'esecuzione
*  
* Esegue MAX_RUNS run durante le quali genera 100 strighe (da 1000 a 500000 caratteri, sempre piu' lunghe) e le
* fornisce in input agli algoritmi.
* I dati sono salvati nel file "results.csv" 
*/
public class Progetto {

    public static final int MAX_RUNS= 6;

    public static void main(String[] args) {

        double tmin = Chrono.tMin();

        Logger.initializeFile();

        for (int run = 1; run <= MAX_RUNS; run++) {
            for (int test = 0; test <= 99; test++) {
                System.out.println("run: "+ run +" test: " + test);

                String s = StringGenerator.generateString(test);

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
