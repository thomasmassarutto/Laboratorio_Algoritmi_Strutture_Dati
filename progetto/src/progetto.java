
// periodo frazionario
import java.util.Scanner;

public class progetto {
    public static void main(String[] args) {
        Chrono.startChrono();
        System.out.println("Hello, World progetto ");
        Chrono.endChrono();

        System.out.println(Chrono.elapsedTime());

        Chrono.startChrono();
        for (int i = 0; i < 9999; i++) {
            System.out.println("Hello, World progetto ");
        }
        Chrono.endChrono();

        System.out.println(Chrono.elapsedTime());

    }
}
