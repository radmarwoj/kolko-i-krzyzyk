import java.util.Random;
import java.util.Scanner;

public class kolkoKrzyzyk {
    private int[][] plansza = new int[3][3]; // Plansza 3x3
    private Random random = new Random();
    private int gracz; // 1 - gracz (O), 2 - komputer (X)

    public void rozpocznijGre() {
        System.out.println("Gra w kółko i krzyżyk!");
        gracz = random.nextInt(2) + 1;
        if (gracz == 1) System.out.println("Losowanie: zaczyna " + "gracz (O)");
        else System.out.println("Losowanie: zaczyna " + "komputer (X)");

        while (true) {
            rysujPlansze();
            if (gracz == 1) {
                turaGracza(); // Ruch gracza
            } else {
                turaKomputera(); // Ruch komputera
            }

            int zwyciezca = sprawdzZwyciezce();
            if (zwyciezca == 1) {
                rysujPlansze();
                System.out.println("Gracz (O) wygrywa!");
                break;
            } else if (zwyciezca == 2) {
                rysujPlansze();
                System.out.println("Komputer (X) wygrywa!");
                break;
            } else if (czyPlanszaPelna()) {
                rysujPlansze();
                System.out.println("Remis!");
                break;
            }

            if (gracz == 1) gracz = 2;
            else gracz = 1;
        }
    }

    private void turaGracza() {
        Scanner scanner = new Scanner(System.in);
        int wiersz;
        int kolumna;
        while (true) {
            System.out.print("Podaj wiersz (0-2): ");
            wiersz = scanner.nextInt();
            System.out.print("Podaj kolumnę (0-2): ");
            kolumna = scanner.nextInt();

            if (wiersz >= 0 && wiersz < 3 && kolumna >= 0 && kolumna < 3 && plansza[wiersz][kolumna] == 0) {
                plansza[wiersz][kolumna] = 1; // Gracz stawia "O"
                break;
            } else {
                System.out.println("Nieprawidłowy ruch, spróbuj ponownie.");
            }
        }
    }

    private void turaKomputera() {
        int wiersz;
        int kolumna;
        do {
            wiersz = random.nextInt(3);
            kolumna = random.nextInt(3);
        } while (plansza[wiersz][kolumna] != 0);

        plansza[wiersz][kolumna] = 2; // Komputer stawia "X"
        System.out.println("Komputer wykonał ruch: (" + wiersz + ", " + kolumna + ")");
    }

    private void rysujPlansze() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plansza[i][j] == 0) {
                    System.out.print("   "); // Puste pole
                } else if (plansza[i][j] == 1) {
                    System.out.print(" O "); // Kółko
                } else if (plansza[i][j] == 2) {
                    System.out.print(" X "); // Krzyżyk
                }
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    private int sprawdzZwyciezce() {
        for (int i = 0; i < 3; i++) {
            if (plansza[i][0] == plansza[i][1] && plansza[i][1] == plansza[i][2] && plansza[i][0] != 0) {
                return plansza[i][0]; // Wygrana w wierszu
            }
            if (plansza[0][i] == plansza[1][i] && plansza[1][i] == plansza[2][i] && plansza[0][i] != 0) {
                return plansza[0][i]; // Wygrana w kolumnie
            }
        }
        if (plansza[0][0] == plansza[1][1] && plansza[1][1] == plansza[2][2] && plansza[0][0] != 0) {
            return plansza[0][0]; // Wygrana na głównej przekątnej
        }
        if (plansza[0][2] == plansza[1][1] && plansza[1][1] == plansza[2][0] && plansza[0][2] != 0) {
            return plansza[0][2]; // Wygrana na drugiej przekątnej
        }
        return 0; // Brak zwycięzcy
    }

    private boolean czyPlanszaPelna() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plansza[i][j] == 0) {
                    return false; // Znaleziono puste pole
                }
            }
        }
        return true; // Brak pustych pól
    }

    public static void main(String[] args) {
        kolkoKrzyzyk gra = new kolkoKrzyzyk();
        gra.rozpocznijGre();
    }
}
