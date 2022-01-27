# Forza Quattro Ascii

Forza Quattro


Il gioco Forza 4 consiste in una sfida tra due giocatori secondo le seguenti regole:

    -Due giocatori operano su una griglia, disposta verticalmente e chiusa sul fondo, che ha dimensione di 6x7 (7 in verticale e 6 in orizzontale) caselle;

    -I due giocatori dispongono di pedine diverse tra loro (1 -> Verde; 2 -> Giallo);

    -I due giocatori a turno inseriscono una pedina in una delle colonne verticali della griglia:
        la pedina scende fino al fondo della colonna, oppure, fino ad appoggiarsi all'ultima pedina precedentemente inserita nella stessa colonna.
        In questo modo la pedina va ad occupare la casella più bassa della colonna, se la colonna era vuota, oppure la casella libera immediatamente superiore alle caselle già occupate in precedenza;

    -È vietato inserire pedine in una colonna che sia completamente occupata;

Lo scopo dei giocatori è allineare in orizzontale, in verticale o in diagonale, quattro pedine dello stesso colore:
la partita termina quando uno dei due giocatori raggiunge tale obiettivo o quando la griglia è completamente piena (caso di parità).

PREPARAZIONE:
Il gioco è stato sviluppato con un' interfaccia grafica ASCII che utilizza i codici ANSI per i colori.
Purtroppo ANSI potrebbe dare problemi su terminale Windows, pertanto è consigliato l'uso di terminale Linux, preferibilmente con sfondo nero, per un' esperienza migliore.
Inoltre è consigliato l'uso del terminale a schermo intero.

COME AVVIARE IL GIOCO:
Per far partire il gioco occorrerà recarsi da terminale nella cartella "Gioco" o dove si è salvato il file "ForzaQuattroASCII.jar", e digitare: java -jar ForzaQuattroASCII.jar
Mentre il codice sorgente si trova nella cartella "Codice".

TEST:
Per effetture un test dell' applicazione usare il file save.txt: trascinalo nella cartella Gioco, avvia il file .jar e digita 2 per continuare la partita.
Questo salvataggio può testare tutti i tipi di vittoria:

	-Per la vittoria verticale: mettere la pedina gialla su 6, poi la pedina verde su 4, nuovamente giallo su 6 e infine di nuovo verde su 4;

	-Per il tris orizzontale: mettere la pedina gialla su 6, poi la pedina verde su 5, nuovamente giallo su 6 e infine di nuovo verde su 5;
	
	-Per la vittoria diagonale destra: mettere la pedina gialla su 6 e poi mettere verde su 1;
	
	-Per la vittoria diagonale sinistra: mettere la pedina gialla su 5;

Per testare il pareggio, invece, occorrerà copiare il file savePar.txt nella cartella Gioco e rinominarla in save.txt

