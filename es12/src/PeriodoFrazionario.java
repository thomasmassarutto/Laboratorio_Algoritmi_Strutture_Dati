public class PeriodoFrazionario {

    public static int periodoV1(String s) {
        int p = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i % p)) {
                p += 1;
                i = 0;
            }

        }

        return p;
    }
    
}
