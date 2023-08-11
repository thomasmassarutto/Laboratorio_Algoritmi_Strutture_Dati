import java.util.Scanner;

public class es12 {
    public static void main(String[] args) {
        Scanner stringa = new Scanner(System.in);
        String s = stringa.nextLine();
        int periodo= PeriodoFrazionario.periodoV1(s);
        System.out.println(periodo);
    }
}
