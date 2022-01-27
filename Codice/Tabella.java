import java.util.Scanner;
import java.util.Stack;

/**
 * <h1>Classe Tabella</h1>
 * <p></p>
 * La classe tabella Crea e gestisce la game board, ovvero la griglia ove si inseriscono le monete per giocare.<br>
 * La classe tabella gestisce la visualizzazione, la gestione e il controllo delle vincite sulla game board.<br>
 * */
public class Tabella {

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

    private Stack <Integer> undoStack;                          //Stack per il comando "Annulla ultima mossa"

    private Integer[][] tabella;                                //Matrice per la Game Board

    /**
     * Costruttore Tabella.<br>
     * Crea la GameBoard su cui poi i giocatori andranno a inserire le monete.
     * */
    public Tabella(){

        this.undoStack = new Stack<Integer>();
        this.tabella = new Integer[6][7];

        //riempie la matrice di 0
        for (int row = 0; row < 7; row ++){
            for (int column = 0; column < 6; column++){

                this.tabella[column][row] = 0;

            }
        }

    }

    /**
     * Inserisce nello stack la x o la y dell' ultima mossa effettuata
     *
     * @param c int coordinata da inserire
     * */
    public void setUndoStack(int c) {
        undoStack.push(c);
    }

    /**
     * Fa una pop sullo stack e torna il risultato.
     *
     * @return int
     * */
    public int getUndoStack() {
        return undoStack.pop();
    }

    /**
     * Dice se lo stack è vuoto o meno.
     *
     * @return boolean*/
    public boolean isUndoEmpty(){
        return undoStack.empty();
    }

    /**
     * Torna sotto forma di riga String l' intero contenuto dello stack.
     *
     * @return String
     * */
    public String getFullUndoStack(){

        String stack = "";

        while (!isUndoEmpty()){
            stack += String.format("%d", getUndoStack());
        }

        return stack;
    }

    /**
     * Cambia un numero nella tabella alle coordinate indicate.
     *
     * @param x int Coordinata X.
     * @param y int Coordinata Y.
     * @param c int Colore da assegnare.
     * */
    public void setTabella(int x, int y, int c) {
        this.tabella[x][y] = c;
    }

    /**
     * Torna il valore in una specifica posizione della matrice Tabella.
     *
     * @param x int Coordinata X.
     * @param y int Coordinata Y.
     * @return int
     * */
    public int getTabella(int x, int y) {
        return tabella[x][y];
    }

    /**
     * Torna i valori della matrice sotto forma di riga String.
     *
     * @return String*/
    public String getFullTabella(){

        String tab = "";

        for (int y = 0; y < 7; y ++){
            for (int x = 0; x < 6; x++){
                tab += String.format("%d", getTabella(x, y));
            }
        }

        return tab;

    }

    /**
     * Dice se la tabella è piena oppure no. Se è piena torna true, altrimenti false.
     *
     * @return boolena*/
    public boolean isFull(){

        //se l'ultima riga della matrice è piena, è piena anche la matrice
        for (int i = 0; i < 6; i ++){
            if (getTabella(i, 0) == 0){
                return false;
            }
        }

        System.out.print(String.format("\033[2J\033[1;1H"));
        showTabella();
        System.out.println("Nessun vincitore, è un pareggio!");
        System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
        Scanner perssKeyIn = new Scanner(System.in);
        String perssKey = perssKeyIn.nextLine();

        return true;
    }

    /**
     * Permette la visualizzazione ASCII della tabella.
     * */
    public void showTabella(){

        for (int i = 0; i < 7; i ++){
            for (int j = 0; j < 6; j++){

                if (j == 0){
                    System.out.print("|");
                }

                if (getTabella(j, i) == 1){
                    System.out.print(" " + ANSI_GREEN + getTabella(j, i) + ANSI_RESET + " |");  //visualizza la moneta 1 verde
                }else if (getTabella(j, i) == 0){
                    System.out.print(" " + ANSI_WHITE + getTabella(j, i) + ANSI_RESET + " |");  //visualizza la moneta 0 bianca
                }else if (getTabella(j, i) == 8){
                    System.out.print(" " + ANSI_RED + "X" + ANSI_RESET + " |");                 //visualizza dove è stato fatto tris con X rossa
                } else {
                    System.out.print(" " + ANSI_YELLOW + getTabella(j, i) + ANSI_RESET + " |"); //se la moneta è 2 va stampanta di giallo
                }

            }

            if ( i == 6 ){
                System.out.print("\n-------------------------\n");                              //fine tabella
            }else{
                System.out.print("\n-...-...-...-...-...-...-\n");                              //separatore righe
            }

        }

        System.out.println("  1   2   3   4   5   6   " + "\n");                                //identificatori colonne

    }

    /**
     * Permette di inserire una moneta nella GameBoard. Torna false se il gioco non è stato vinto da nessuno, true altrimenti
     *
     * @return boolean
     * */
    public boolean insertCoin(int color, String nome) throws InterruptedException {

        System.out.print(String.format("\033[2J\033[1;1H"));

        System.out.println(ANSI_GREEN + Title + "\n\n" + ANSI_RESET);

        showTabella();

        Boolean findInsert = true;

        System.out.print("Inserisci il numero della colonna: ");
        Scanner in = new Scanner(System.in);

        //assicura che l' opzione specificata sia valida
        try{
            int col = in.nextInt();

            if ( col < 1 || col > 6){
                throw new IllegalArgumentException("Input non valido");
            }

            col -= 1;
            int y = 3;

            while (findInsert){

                //se l'ultima casella della colonna è piena non si può inserire la moneta li
                if (getTabella(col, 0) != 0){
                    System.out.println("Colonna piena");
                    insertCoin(color, nome);
                    findInsert = false;
                }else {

                    if (getTabella(col, y) == 0){

                        if (y+1 == 7){

                            //stoccano nello stack le coordinate dell' ultima mossa effettuata
                            setUndoStack(col);
                            setUndoStack(y);

                            setTabella(col, y, color); //mette la pedina nell'ultima posizione

                            //dato che la pedina è stata messa nella posizione richiesta,
                            //se il metodo seguente darà esito negativo uscirà comunque dal loop
                            findInsert = false;

                            return checkWin(col, y, color, nome);
                        }else if (getTabella(col, y+1) == 0){   //se il successivo è 0 allara è la posizione giusta
                            y += 1;
                        }else{
                            setUndoStack(col);
                            setUndoStack(y);
                            setTabella(col, y, color);
                            findInsert = false;
                            return checkWin(col, y, color, nome);

                        }
                    }else{
                        y -= 1;
                    }
                }

            }

        }catch (Exception e){

            System.err.println(ANSI_RED + "\nInput non valido" + "\n" + e + ANSI_RESET);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();

            insertCoin(color, nome);
        }

        return false;

    }

    /**
     * Verifica se c'è stato un tris Orizzontale, se si torna true. <br>
     * Es:<br>
     * <p></p>
     *
     * | X | X | X | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * ------------------------- <br>
     *   <p></p>
     *
     * @return boolean
     * */
    public boolean checkHorizontal(int y, int color, String nome){

        //stack per cambiare il simbolo dov' è stato fatto tris
        Stack <Integer> winCheck = new Stack<Integer>();

        boolean res = false; //diventa true se c'è tris

        int count = 0;
        int X = 0;

        while(count < 4 && X < 6){

            if ( X == 3 && count == 0){
                break;
            }else if (getTabella(X, y) == color){

                //stoccaggio coordinate
                winCheck.push(X);
                winCheck.push(y);

                count++;
                X++;
            }else {
                count = 0;

                //svuoto la pila se non c'è il tris
                while (!winCheck.empty()){
                    winCheck.pop();
                }

                X++;
            }

        }

        if (count == 4){

            while (!winCheck.empty()){

                //cambio contenuto delle monete che hanno fatto tris
                y = winCheck.pop();
                int x = winCheck.pop();
                setTabella(x, y, 8);

            }

            res = true;
        }


        if(res){
            System.out.print(String.format("\033[2J\033[1;1H"));
            showTabella();
            System.out.println("\n(Orizzontale) Ha vinto " + nome);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
        }

        return res;

    }

    /**
     * Verifica se c'è stato un tris Verticale, se si torna true.<br>
     * Es:<br>
     * <p></p>
     *
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * ------------------------- <br>
     *   <p></p>
     *
     * @return boolean
     * */
    public boolean checkVertical(int x, int color, String nome){

        Stack <Integer> winCheck = new Stack<Integer>();
        boolean res = false;

        int count = 0;
        int Y = 6;
        while(count != 4 && Y > -1){

            if ( Y == 2 && count == 0){
                break;
            }else if (getTabella(x, Y) == color){
                winCheck.push(x);
                winCheck.push(Y);
                count++;
                Y--;
            }else {
                count = 0;
                while (!winCheck.empty()){

                    winCheck.pop();

                }
                Y--;
            }

        }

        if (count == 4){

            while (!winCheck.empty()){

                int y = winCheck.pop();
                x = winCheck.pop();
                setTabella(x, y, 8);

            }

            res = true;
        }

        if (res){
            System.out.print(String.format("\033[2J\033[1;1H"));
            showTabella();
            System.out.println("\n(Verticale) Ha vinto " + nome);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
        }


        return res;

    }

    /**
     * Verifica se c'è stato un tris diagonale sinistro, se si torna true.<br>
     * Es:<br>
     * <p></p>
     *
     * | X | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | X | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | X | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * ------------------------- <br>
     *   <p></p>
     *
     * @return boolean
     * */
    public Boolean checkDiagonalLeft(int color, String nome){

        Stack <Integer> winCheck = new Stack<Integer>();
        boolean res = false;

        int Y = 3;
        int x = 0;
        int y = 0;
        int count = 0;

        while (Y > 0 && count < 4){
            for (y = Y; y < 7; y++){

                if (getTabella(x, y) == color){
                    winCheck.push(x);
                    winCheck.push(y);
                    count++;
                    if (count == 4){
                        break;
                    }
                }else{
                    count = 0;
                    while (!winCheck.empty()){

                        winCheck.pop();

                    }
                }

                x++;

            }

            if (count >= 4){
                break;
            }else{
                count = 0;
            }
            x = 0;
            Y--;

        }

        if (count == 4){

            while (!winCheck.empty()){

                y = winCheck.pop();
                x = winCheck.pop();
                setTabella(x, y, 8);

            }

            res = true;
        }else{

            count = 0;
            x = 0;
            Y = 0;

            while (x < 3 && count < 4){
                for (int X = x; X < 6; X++){

                    if (getTabella(X, Y) == color){
                        winCheck.push(X);
                        winCheck.push(Y);
                        count++;
                        if (count == 4){
                            break;
                        }
                    }else{
                        count = 0;
                        while (!winCheck.empty()){

                            winCheck.pop();

                        }
                    }

                    Y++;

                }

                if (count >= 4){
                    break;
                }else{
                    count = 0;
                }
                Y = 0;
                x++;

            }

        }

        if (count == 4){

            while (!winCheck.empty()){

                y = winCheck.pop();
                x = winCheck.pop();
                setTabella(x, y, 8);

            }

            res = true;
        }

        if(res){
            System.out.print(String.format("\033[2J\033[1;1H"));
            showTabella();
            System.out.println("(Diagonale Sx) Ha vinto " + nome);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
        }

        return res;

    }

    /**
     * Verifica se c'è stato un tris diagonale sinistro, se si torna true. <br>
     * Es:<br>
     * <p></p>
     *
     * | 0 | 0 | 0 | 0 | 0 | X | <br>
     * | 0 | 0 | 0 | 0 | X | 0 | <br>
     * | 0 | 0 | 0 | X | 0 | 0 | <br>
     * | 0 | 0 | X | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * | 0 | 0 | 0 | 0 | 0 | 0 | <br>
     * ------------------------- <br>
     *   <p></p>
     *
     * @return boolean
     * */
    public boolean checkDiagonalRight(int color, String nome){

        boolean res = false;
        Stack <Integer> winCheck = new Stack<Integer>();

        int Y = 3;
        int X  = 0;
        int y = Y;
        int x = X;
        int count = 0;

        while (Y < 7){

            y = Y;
            x = X;

            while(y > -1 && x < 6 && count < 4){

                if (getTabella(x, y) == color){
                    winCheck.push(x);
                    winCheck.push(y);
                    count++;
                    if (count == 4){
                        break;
                    }
                }else {
                    count = 0;
                    while (!winCheck.empty()){

                        winCheck.pop();

                    }
                }

                y--;
                x++;
            }

            if (count >= 4){
                break;
            }else{
                count = 0;
            }
            Y++;
            X = 0;
        }

        if (count == 4){

            while (!winCheck.empty()){

                y = winCheck.pop();
                x = winCheck.pop();
                setTabella(x, y, 8);

            }

            res = true;
        }else{

            X = 1;
            Y = 6;
            count = 0;

            while (X < 3){

                y = Y;
                x = X;

                while (x < 6 && count < 4){

                    if (getTabella(x, y) == color){
                        count++;
                        winCheck.push(x);
                        winCheck.push(y);
                        if (count == 4){
                            break;
                        }
                    }else {
                        count = 0;
                        while (!winCheck.empty()){

                            winCheck.pop();

                        }
                    }

                    x++;
                    y--;
                }

                if (count >= 4){
                    break;
                }else{
                    count = 0;
                }
                X++;

            }

            if (count == 4){

                while (!winCheck.empty()){

                    y = winCheck.pop();
                    x = winCheck.pop();
                    setTabella(x, y, 8);

                }

                res = true;
            }

        }

        if(res){
            System.out.print(String.format("\033[2J\033[1;1H"));
            showTabella();
            System.out.println("(Diagonale Dx) Ha vinto " + nome);

            System.err.println(ANSI_YELLOW + "\nPremi ENTER per continuare..." + ANSI_RESET);
            Scanner perssKeyIn = new Scanner(System.in);
            String perssKey = perssKeyIn.nextLine();
        }

        return res;

    }

    /**
     * Controlla se la partita è stata vinta da qualcuno.
     * Invoca i metodi checkHorizontal, checkVertical, checkDiagonalLeft e checkDiagonalRight per controllare se la partira va terminata.
     *
     * @return boolean
     * */
    public boolean checkWin(int x, int y, int color, String nome){

        if(checkHorizontal(y, color, nome)){ return true; }

        if(checkVertical(x, color, nome)){   return true; }

        if(checkDiagonalLeft(color, nome)){  return true; }

        if(checkDiagonalRight(color, nome)){ return true; }

        if(isFull()){                        return true; }

        return false;

    }

}