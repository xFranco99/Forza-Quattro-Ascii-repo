import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

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
     * */
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
     * Torna il colore testuale dal colore numerico dato
     *
     * @param p1 Oggetto Player 1
     * @param p2 Oggetto Player 2
     * @param colore int Colore del giocatore di cui si vuole capire il nome
     *
     * @return String
     * */
    public static String getNameFromColor(Player p1, Player p2, int colore){

        if ( p1.getColore() == colore){
            return p1.getNome();
        }else{
            return p2.getNome();
        }

    }

    /**
     * Menu che si attiva alla fine di una partita in cui l'utente può decidere di chiudere l'applicazione o cominciare
     * una nuova partita, in cui viene stampato anche il nome del vincitore e come è stata vinta la partita oppure, in
     * caso di pareggio, comunica che non ci sono vincitori.
     *
     * @param p1 Oggetto Player 1
     * @param p2 Oggetto Player 2
     * @param winner int Colore giocatore che ha vinto la partita
     * @param tab Ogetto Tabella
     *
     * @return boolean
     * */
    public static boolean gameEnded(Player p1, Player p2, int winner, Tabella tab){

        //"Pulisce" il terminale
        System.out.print(String.format("\033[2J\033[1;1H"));

        System.out.println(ANSI_GREEN + Title + "\n\n" + ANSI_RESET);

        tab.showTabella();

        System.out.println("(1) - Inizia una nuova partita");
        System.out.println("(2) - Esci");

        System.out.print(ANSI_YELLOW + "Scegli un' opzione: " + ANSI_RESET);
        Scanner in = new Scanner(System.in);
        try {

            int opt = in.nextInt();

            if (opt > 2 || opt < 1){
                throw new IllegalArgumentException("Input non valido");
            }

            if (opt == 1){                                              //Inizia una nuova partita
                System.out.print(String.format("\033[2J\033[1;1H"));
                Start();
            }else {                                                     //Esci
                System.out.print(String.format("\033[2J\033[1;1H"));
                return true;
            }

        }catch (Exception e){
            System.err.println(ANSI_RED + "\nOpzione inesistente" + ANSI_RESET);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();

            gameEnded(p1, p2, winner, tab);
        }

        return true;
    }

    /**
     * Menu di gioco in cui si possono scegliere le azioni da intraprendere: <br><p></p>
     * -Inserire una moneta <br>
     * -Annullare ultima mossa compiuta <br>
     * -Salva la partita e uscire dall' applicazione<br>
     * -Esci dall' applicazione senza salvare<br>
     * <p></p>
     * Chiama i metodi: showTabella, getNameFromColor, insertCoin, isUndoEmpty, getUndoStack, saveGame.
     *
     * @param p1 Oggetto Player 1;
     * @param p2 Oggetto Player 2;
     * @param tab Oggetto Tabella;
     * @param colorNextTurn int che rappresenta il colore del Player a cui spetta il turno;
     * */
    public static void gameOptions(Player p1, Player p2, Tabella tab, int colorNextTurn){

        boolean quit = false;

        do{
            System.out.print(String.format("\033[2J\033[1;1H"));

            System.out.println(ANSI_GREEN + Title + "\n\n" + ANSI_RESET);

            tab.showTabella();

            System.out.println("(1) - Giocatore " + getNameFromColor(p1, p2, colorNextTurn) + " pronto!");
            System.out.println("(2) - Annulla ultima mossa");
            System.out.println("(3) - Salva ed esci");
            System.out.println("(4) - Esci senza salvare");

            System.out.print(ANSI_YELLOW + "Scegli un' opzione: " + ANSI_RESET);
            Scanner in = new Scanner(System.in);
            try {

                    int opt = in.nextInt();

                    if (opt > 4 || opt < 1){
                        throw new IllegalArgumentException("Input non valido");
                    }

                    if (opt == 1){      //Conferma che il giocatore corrente è pronto

                        if(tab.insertCoin(colorNextTurn, getNameFromColor(p1, p2, colorNextTurn))){
                            quit = gameEnded(p1,p2, colorNextTurn, tab);
                        }

                        //passa il turno al giocatore successivo
                        if (colorNextTurn == 1){
                            colorNextTurn = 2;
                        }else {
                            colorNextTurn = 1;
                        }

                    }else if (opt == 2){    //Annulla ultima mossa

                        if (!tab.isUndoEmpty()){
                            int y = tab.getUndoStack();
                            int x = tab.getUndoStack();
                            tab.setTabella(x, y, 0);

                            if (colorNextTurn == 1){
                                colorNextTurn = 2;
                            }else {
                                colorNextTurn = 1;
                            }

                        }else{
                            throw new IllegalArgumentException("Non ci sono mosse da annullare");
                        }

                    }else if(opt == 3){     //Salva ed esci

                        saveGame(p1, p2,tab, colorNextTurn);
                        quit = true;

                    }else if (opt == 4){    //Esci senza salvare

                        System.out.print(String.format("\033[2J\033[1;1H"));
                        break;

                    }

            }catch (Exception e){

                System.err.println(ANSI_RED + "\nErrore: " + e + ANSI_RESET);

                System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
                Scanner perssKeyIn = new Scanner(System.in);
                String perssKey = perssKeyIn.nextLine();
                continue;

            }

            System.out.print(String.format("\033[2J\033[1;1H"));

        }while (!quit);

    }

    /**
     * Metodo inocato nel caso si debba cominciare una nuova partita. <br>
     * Questo metodo permetter di scegliere casualmente il primo giocatore ad iniziare la partita,
     * crea gli oggetti Player 1 e Player 2 ed assegna i nomi specificati dagli utenti e crea una GameBoard. <br><p></p>
     *
     * Invoca i metodi setColore e gameOptions.
     * */
    public static void Start(){

        try{
            System.out.print(String.format("\033[2J\033[1;1H"));

            System.out.println(ANSI_GREEN + Title + "\n\n" + ANSI_RESET);

            System.out.print(ANSI_YELLOW + "Inserisci il nome del player 1: " + ANSI_RESET);
            Scanner in = new Scanner(System.in);
            String nom1 = in.nextLine();

            System.out.print(ANSI_YELLOW + "\nInserisci il nome del player 2: " + ANSI_RESET);
            Scanner in2 = new Scanner(System.in);
            String nom2 = in.nextLine();

            if (nom1.equals(nom2)){
                throw new Exception("Illegal argument");
            }

            Player p1 = new Player(nom1);
            Player p2 = new Player(nom2);

            Tabella tab = new Tabella();

            Random P1 = new Random();
            int r = P1.nextInt(2);

            if (r == 0){
                p1.setColore(2);
            }else{
                p2.setColore(2);
            }

            int colorNextTurn = Math.min(p1.getColore(), p2.getColore());

            gameOptions(p1, p2, tab, colorNextTurn);
        }catch (Exception e){

            System.err.println(ANSI_RED + "\nI nomi non possono essere uguali" + ANSI_RESET);

            System.out.println(ANSI_YELLOW + "Premi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
            Start();

        }

    }

    /**
     * Questo metodo permette di salvare i progressi di gioco in un file di testo nel seguente formato:<br>
     * <p></p>
     * Player 1: <br>
     * -colore p1<br>
     * -nome p1<br>
     * -coloreTestuale<br>
     * Player 2: <br>
     * -colore p2<br>
     * -nome p2<br>
     * -coloreTestuale<br>
     * Tabella: <br>
     * -Tabella sottoforma di riga di testo<br>
     * -undoStack sottoforma di riga di testo<br>
     * colorNextTurn: <br>
     * -colore del giocatore a cui spetta il turno<br>
     * <p></p>
     *
     * Invoca i metodi: getColore, getNome, getColorName, getFullTabella, getFullUndoStack.
     *
     * @param p1 Oggetto Player 1;
     * @param p2 Oggetto Player 2;
     * @param tab Oggetto Tabella;
     * @param colorNextTurn int che rappresenta il colore del Player a cui spetta il turno;
     * */
    public static void saveGame(Player p1, Player p2, Tabella tab, int colorNextTurn){

        try {

            File f = new File("save.txt");

            System.out.print(String.format("\033[2J\033[1;1H"));
            System.out.println("Creo punto di salvataggio");

            f.createNewFile();

            PrintWriter scrivi = new PrintWriter(f);

            scrivi.println("Player 1: ");
            scrivi.println(p1.getColore());
            scrivi.println(p1.getNome());
            scrivi.println(p1.getColorName());

            scrivi.println("Player 2: ");
            scrivi.println(p2.getColore());
            scrivi.println(p2.getNome());
            scrivi.println(p2.getColorName());

            scrivi.println("Tabella: ");
            scrivi.println(tab.getFullTabella());
            scrivi.println(tab.getFullUndoStack());

            scrivi.println("colorNextTurn: ");
            scrivi.println(colorNextTurn);

            scrivi.close();

            System.out.println("Punto di salvataggio creato, esco dal gioco");

        }catch (Exception e) {
            System.err.println(ANSI_RED + "\n" + e + ANSI_RESET);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
        }

    }

    /**
     * Questo metodo permette di caricare i progressi di gioco da un file di testo nel seguente formato:<br>
     * <p></p>
     * Player 1: <br>
     * -colore p1<br>
     * -nome p1<br>
     * -coloreTestuale<br>
     * Player 2: <br>
     * -colore p2<br>
     * -nome p2<br>
     * -coloreTestuale<br>
     * Tabella: <br>
     * -Tabella sottoforma di riga di testo<br>
     * -undoStack sottoforma di riga di testo<br>
     * colorNextTurn: <br>
     * -colore del giocatore a cui spetta il turno<br>
     * <p></p>
     *
     * Invoca i metodi: setColore, setNome, setColorName, setTabella, setUndoStack, gameOptions.
     *
     * */
    public static void loadGame(){

        Player p1 = new Player("");
        Player p2 = new Player("");

        Tabella tab = new Tabella();

        int colorNextTurn = 0;

        try {
            FileReader f = new FileReader("save.txt");

            BufferedReader b;
            b=new BufferedReader(f);

            String s = "";

            s = b.readLine();
            s = b.readLine();
            p1.setColore(Integer.parseInt(s));
            s = b.readLine();
            p1.setNome(s);
            s = b.readLine();
            p1.setColorName(s);

            s = b.readLine();
            s = b.readLine();
            p2.setColore(Integer.parseInt(s));
            s = b.readLine();
            p2.setNome(s);
            s = b.readLine();
            p2.setColorName(s);

            s = b.readLine();
            s = b.readLine();
            for (int y = 0; y < 7; y++){
                for (int x = 0; x < 6; x++){

                    tab.setTabella(x, y, Integer.parseInt(String.valueOf(s.charAt((x+(6*y))))));

                }
            }
            s = b.readLine();
            for (int i = s.length()-1; i > -1; i --){
                tab.setUndoStack(Integer.parseInt(String.valueOf(s.charAt(i))));
            }
            s = b.readLine();
            s = b.readLine();
            colorNextTurn = Integer.parseInt(s);

            gameOptions(p1, p2, tab, colorNextTurn);

        } catch (Exception e) {

            System.err.println(ANSI_RED + "\n" + e + ANSI_RESET);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per iniziare una nuova partita..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
            Start();
        }


    }

}