/**
 * <h1>Classe Player</h1>
 * <p></p>
 * Classe che contiene il costruttore Player e metodi che lo riguardano.
 * Player si occupa della gestione delle info del giocatore come, ad esempio, il nome e la moneta che possiede (che indica anche il turno).
 * */
public class Player {

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

    private String Nome;
    private int Colore;
    private String ColorName;

    /**
     * Costruttore Player.
     *
     * @param n String che contiene il nome del giocatore.
     * */
    public Player(String n){

        this.Nome = ANSI_GREEN + n + ANSI_RESET;
        this.Colore = 1;
        this.ColorName = "Verde";

    }

    /**
     * Restituisce il colore del giocatore (ovvero il colore della moneta del giocatore).
     *
     * @return int */
    public int getColore() {
        return Colore;
    }
    /**
     * Restituisce il nome del giocatore
     *
     * @return String
     * */
    public String getNome() {
        return Nome;
    }

    /**
     * Imposta il colore del giocatore. <br>
     * Il giocatore 1 avra il colore verde, mentre giocatore 2 avrà il colore giallo.
     *
     * @param colore int che contiene il colore da cambiare.
     * */
    public void setColore(int colore) {
        Colore = colore;

        if (colore == 1){
            ColorName = "Verde";
        }else{
            ColorName = "Giallo";
            Nome = Nome.replace(ANSI_GREEN, ANSI_YELLOW);   //Cambia il colore visualizzato nella console
        }

    }

    /**
     * Imposta il nome del giocatore.
     *
     * @param nome String che contiene il nome da impostare.
     * */
    public void setNome(String nome) {
        Nome = nome;
    }

    /**
     * Restituisce i colori in String.
     *
     * @return String
     * */
    public String getColorName() {
        return ColorName;
    }

    /**
     * Imposta i nomi dei colori in String.
     *
     * @param colorName String che contiene i nomi testuali dei colori.
     * */
    public void setColorName(String colorName) {
        ColorName = colorName;
    }
}