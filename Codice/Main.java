import java.util.Scanner;

/**
 * <h1>Forza Quattro</h1>
 * Il gioco Forza 4 consiste in una sfida tra due giocatori secondo le seguenti regole:
 * <p></p>
 * -Due giocatori operano su una griglia, disposta verticalmente e chiusa sul fondo,
 * che ha dimensione di 6x7 (7 in verticale e 6 in orizzontale) caselle
 * <p></p>
 * -I due giocatori dispongono di pedine diverse tra loro
 * <p></p>
 * -I due giocatori a turno inseriscono una pedina in una delle colonne verticali della griglia:
 * la pedina scende fino al fondo della colonna, oppure,
 * fino ad appoggiarsi all'ultima pedina precedentemente inserita nella stessa colonna.
 * In questo modo la pedina va ad occupare la casella più bassa della colonna,
 * se la colonna era vuota, oppure la casella libera immediatamente superiore alle caselle già occupate in precedenza.
 * <p></p>
 * -È vietato inserire pedine in una colonna che sia completamente occupata.
 * <p></p><br><p></p>
 * Lo scopo dei giocatori è allineare in orizzontale, in verticale o in diagonale,
 * quattro pedine dello stesso colore:
 * la partita termina quando uno dei due giocatori raggiunge tale obiettivo o quando la griglia è completamente piena.
 *
 *
 * @author  Francesco Gigliotti
 * @version 1.0
 * @since   2021-05-31
 */
public class Main {

    /**
     * Valore ANSI per il reset
     * */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Valore ANSI per il verde
     * */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Valore ANSI per il giallo
     * */
    public static final String ANSI_YELLOW = "\u001B[93m";

    /**
     * Valore ANSI per il bianco
     * */
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Valore ANSI per il rosso
     * */
    public static final String ANSI_RED = "\u001B[31m";

    /**
    * Titolo in ASCII
    */
    public static final String Title =
                    " /$$$$$$$$                                             /$$$$$$                        /$$     /$$                        \n" +
                    "| $$_____/                                            /$$__  $$                      | $$    | $$                        \n" +
                    "| $$     /$$$$$$   /$$$$$$  /$$$$$$$$  /$$$$$$       | $$  \\ $$ /$$   /$$  /$$$$$$  /$$$$$$ /$$$$$$    /$$$$$$   /$$$$$$ \n" +
                    "| $$$$$ /$$__  $$ /$$__  $$|____ /$$/ |____  $$      | $$  | $$| $$  | $$ |____  $$|_  $$_/|_  $$_/   /$$__  $$ /$$__  $$\n" +
                    "| $$__/| $$  \\ $$| $$  \\__/   /$$$$/   /$$$$$$$      | $$  | $$| $$  | $$  /$$$$$$$  | $$    | $$    | $$  \\__/| $$  \\ $$\n" +
                    "| $$   | $$  | $$| $$        /$$__/   /$$__  $$      | $$/$$ $$| $$  | $$ /$$__  $$  | $$ /$$| $$ /$$| $$      | $$  | $$\n" +
                    "| $$   |  $$$$$$/| $$       /$$$$$$$$|  $$$$$$$      |  $$$$$$/|  $$$$$$/|  $$$$$$$  |  $$$$/|  $$$$/| $$      |  $$$$$$/\n" +
                    "|__/    \\______/ |__/      |________/ \\_______/       \\____ $$$ \\______/  \\_______/   \\___/   \\___/  |__/       \\______/ ";

    /**
     * Il metodo main fa partire l'applicazione "<b>Forza Quattro</b>" <br><p></p>
     * main fa scegliere all' utente se vuole iniziare una nuova partita oppure continuarne una lasciata in sospeso.<br>
     * Tramite un menu ASCII l' utente sarà in grado di scegliere l'opzione desiderata.
     * In caso di opzione inesistente il menu verrà lanciato nuovamente, finchè non verrà data un opzione valida.
     * <p></p>
     * Chiama i seguenti metodi: Start, loadGame;
     * */
    public static void main(String[] args) throws InterruptedException {

        boolean stop = false;

        do{

            //visualizzazione menu
            System.out.print(String.format("\033[2J\033[1;1H"));
            System.out.println("\n" + ANSI_GREEN + Title + ANSI_RESET + "\n");

            System.out.println("(1) - Nuova Partita");
            System.out.println("(2) - Continua");

            System.out.print(ANSI_YELLOW + "Scegli un' opzione: " + ANSI_RESET);
            Scanner in = new Scanner(System.in);

            //controllo se l' opzione è valida
            try {
                int option = in.nextInt();

                if(option > 2 || option < 1){
                    throw new IllegalArgumentException("Input non valido");
                }

                if (option == 1){                                           //Nuova Partita

                    System.out.print(String.format("\033[2J\033[1;1H"));
                    Gameplay.Start();
                    stop = true;

                }else if (option == 2){                                     //Continua

                    System.out.print(String.format("\033[2J\033[1;1H"));
                    Gameplay.loadGame();
                    stop = true;

                }

            }catch (Exception e){

                //Visualizzazione Errori
                System.err.println(ANSI_RED + "\nCarattere non valido" + ANSI_RESET);
                System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);

                //Conferma lettura dell' errore da parte dell' utente
                Scanner perssKeyIn = new Scanner(System.in);
                String perssKey = perssKeyIn.nextLine();

                continue;
            }


        }while (!stop);

    }
}