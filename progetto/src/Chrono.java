/* 
 * Classe Chrono:
 * 
 * Questa classe serve a cronometrare il tempo di eseguzione di in programma in millisecondi
 * 
 * limite massimo: 9,223  secondi, (poco più di 2 ore)
 * tempo di eseguzione media di nanoTime(): circa 25 nanosecondi
 * 
 * void startChrono()   : comincia a cronometrare 
 * void endChrono()     : smette di cronometrare
 * long elapsedTime()   : calcola il tempo di eseguzione
 * 
 * 
 * NB: mancano controlli di sicurezza per verificare la possibile sovrascrittura di dati.
 * usare in maniera consona:
 * startChrono() -> endChrono() -> elapsedTime()
*/


public class Chrono {

    private static long startTime;
    private static long endTime;

    public static void startChrono() {
        startTime= System.nanoTime();
    }

    public static void endChrono() {
        endTime= System.nanoTime();
    }

    public static long elapsedTime(){        

        return endTime - startTime; 
    }

}
