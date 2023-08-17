/* 
 * Classe Chrono:
 * 
 * Questa classe serve a cronometrare il tempo di eseguzione di in programma in millisecondi
 * 
 * limite massimo: 9,223  secondi, (poco più di 2 ore)
 * tempo di eseguzione media di nanoTime(): circa 25 nanosecondi
 * 
 * PUBBLICO:
 * long now()                   : tempo dall'inizio del programma
 * double getResolution()   	: stimare la risoluzione del clock di sistema
 * double tMin()                : tempo minimo misurabile in funzione della Risoluzione e dellerrore relativo massimo
 * 
 * 
*/

public class Chrono {

    // tempo dall'inizio del programma
    public static long now() {
        return  System.nanoTime();
    }

    // stima la risoluzione del clock di sistema
    private static double getResolution() {
        double start = System.nanoTime();
        double end;
        do {
            end = System.nanoTime();
        } while (start == end);
        return end - start;
    }
    
    // tempo minimo misurabile 
    public static double tMin(){
        return getResolution()*((1/0.001) +1);
    }

}
