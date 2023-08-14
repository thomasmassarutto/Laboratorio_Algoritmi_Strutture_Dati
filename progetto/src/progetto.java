
// periodo frazionario
import java.util.Scanner;

public class progetto {
    public static void main(String[] args) {

        String s = StringGenerator.generateString();

        System.out.println(Chrono.tMin());
        
        Chrono.startChrono();
        int res = Algos.periodNaive(s);
        Chrono.endChrono();

        System.out.println(Chrono.elapsedTime());

    }
}
