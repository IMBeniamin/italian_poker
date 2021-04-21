Il progetto contiene varie cartelle:
.idea    -- File progetto per la suite JetBrains
doc      -- Documentazione inclusi i pdf con le istruzioni
HtmlDemo -- Demo della pagina html con valori stabili staticamente
src      -- Sorgenti dei file Java
out      -- Versione precompilata delle sorgenti e del pacchetto

*Nota
Il progetto e' stato compilato e testato con jdk-1.8.0
Il progetto supporta nativamente anche altri IDE, per esempio Eclipse, che utilizzano il file .classpath.
E' inoltre possibile importarlo in altri ambienti di sviluppo attraverso la loro opzione di import

Il progetto e' strutturato nel seguente modo:
	EntryPoint.java  -- punto di lancio del programma
	Pacchetto Poker  -- Package di java contenente altri sub-packages
		Components   --  Package contenente i componenti fondamentali del gioco
		IO           --  Package contenente tutte le utilita' necessarie per l'input/output
		Logic        --  Package contenente la logica necessaria per il funzionamento del gioco
		Res          --  Package speciale contenente il file cards.json che e' utilizzato come mazzo di gioco e la cartella cards contenente le immagini delle carte
		Util         --  Package contenente varie utilita' per il gioco