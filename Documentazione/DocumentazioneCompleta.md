1. [Introduzione](#introduzione)

  - [Informazioni sul progetto](#informazioni-sul-progetto)

  - [Abstract](#abstract)

  - [Scopo](#scopo)

1. [Analisi](#analisi)

  - [Analisi del dominio](#analisi-del-dominio)

  - [Analisi dei mezzi](#analisi-dei-mezzi)

  - [Analisi e specifica dei requisiti](#analisi-e-specifica-dei-requisiti)

  - [Use case](#use-case)

  - [Pianificazione](#pianificazione)

1. [Progettazione](#progettazione)

  - [Design dell’architettura del sistema](#design-dell’architettura-del-sistema)

  - [Design dei dati e database](#design-dei-dati-e-database)

1. [Implementazione](#implementazione)

1. [Test](#test)

  - [Protocollo di test](#protocollo-di-test)

  - [Risultati test](#risultati-test)

  - [Mancanze/limitazioni conosciute](#mancanze/limitazioni-conosciute)

1. [Consuntivo](#consuntivo)

1. [Conclusioni](#conclusioni)

  - [Sviluppi futuri](#sviluppi-futuri)

  - [Considerazioni personali](#considerazioni-personali)

1. [Sitografia](#sitografia)

1. [Allegati](#allegati)


## Introduzione

### Informazioni sul progetto

  In questo progetto è stato coinvolto lo studente Georgiy Farina facente parte della classe I3AA della Scuola Arti e Mestieri di Trevano, sezione informatica. Il progetto è stato affidato dai docenti incaricati Luca Muggiasca e Geo Petrini, quest'ultimo anche supervisore, ed ha come data d'inizio il 07/09/19 e il termine previsto il 20/12/2019.

### Abstract

  Con l'utilizzo di questo strumento ora è possibile creare dei fiocchi in poco tempo semplicemente ritagliando un triangolo avendo così modo di scatenare all'infinito la propria fantasia facendo e rifacendo quanti fiocchi si vuole senza doverli buttare nel cestino nel caso non dovessero piacere.

### Scopo

  Lo scopo del progetto è quello di generare un fiocco di neve utilizzando gli scarti di un triangolo precedentemente ritagliato dall'utente e infine salvare il risultato come un immagine raster o vettoriale con le dimensioni desiderate dall'utente.

## Analisi

### Analisi del dominio

  Di solito siamo abituati a creare dei fiocchi di neve partendo da un foglio di carta, delle forbici e un po di fantasia mista a creatività. Utilizzando questo software sviluppato interamente in Java versione 12.0.1 avrete la possibilità di fare tutto ciò con un paio di click sul computer.

### Analisi e specifica dei requisiti

  |**ID**	|**Nome**|**Categoria**| **Priorità**|**Vers**|**Note**  |
  |----|------------|-------|--------|----|------|
  |01|Sito con descrizione software|Sito|4|1.0|...|
  |02|Possibilità di scaricare il software dal sito|Sito|3|1.0|...|
  |03|Lista di requisiti di sistema per l'utilizzo |Sito|1|1.0|...|
  |04|Presenza di interfaccia grafica|Software|1|1.0|...|
  |05|Il triangolo si deve trovare in centro del panel|Software|1|1.0|...|
  |06|Si deve poter resettare i punti di ritaglio|Software|1|1.0|...|
  |07|Presenza di un tasto "genera fiocco"|Software|1|1.0|...|
  |08|La generazione può avvenire in tempo reale|Software|1|1.0|...|
  |09|I punti possono essere spostati o rimossi|Software|1|1.0|...|
  |10|Si deve poter salvare il fiocco|Salvataggio fiocco|1|1.0|...|
  |11|Il salvataggio avviene in formato PNG o SVG|Salvataggio fiocco|1|1.0|...|
  |12|Le dimensioni dell'immagine sono definite dall'utente|Salvataggio fiocco|1|1.0|...|
  |13|Si può salvare i punti di ritaglio|Sito|1|1.0|...|
  |14|Finestra ridimensionabile con dimensioni default 1024x768|Interfaccia programma|1|1.0|...|


### Use case

VEDERE L'ESEMPIO DI BASKET 

### Pianificazione

<img src="Gantt_Iniziale.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

### Analisi dei mezzi
 #### Software
 

 #### Hardware
 Il progetto è stato realizzato interamente su un computer portatile Acer A717-71G con sistema operativo Windows 10 Home. Il link del sito dove risiede il download del file è il seguente: samtinfo.ch/i17fargeo/Snowflake

## Progettazione

mettere tutti i metodi e cose i test case

### Design dell’architettura del sistema

Descrive:

-   La struttura del programma/sistema lo schema di rete...

-   Gli oggetti/moduli/componenti che lo compongono.

-   I flussi di informazione in ingresso ed in uscita e le
    relative elaborazioni. Può utilizzare *diagrammi di flusso dei
    dati* (DFD).

-   Eventuale sitemap

### Design dei dati e database

Descrizione delle strutture di dati utilizzate dal programma in base
agli attributi e le relazioni degli oggetti in uso.

### Schema E-R, schema logico e descrizione.

Se il diagramma E-R viene modificato, sulla doc dovrà apparire l’ultima
versione, mentre le vecchie saranno sui diari.

### Design delle interfacce

Descrizione delle interfacce interne ed esterne del sistema e
dell’interfaccia utente. La progettazione delle interfacce è basata
sulle informazioni ricavate durante la fase di analisi e realizzata
tramite mockups.

### Design procedurale

Descrive i concetti dettagliati dell’architettura/sviluppo utilizzando
ad esempio:

-   Diagrammi di flusso e Nassi.

-   Tabelle.

-   Classi e metodi.

-   Tabelle di routing

-   Diritti di accesso a condivisioni …

Questi documenti permetteranno di rappresentare i dettagli procedurali
per la realizzazione del prodotto.

## Implementazione

In questo capitolo dovrà essere mostrato come è stato realizzato il
lavoro. Questa parte può differenziarsi dalla progettazione in quanto il
risultato ottenuto non per forza può essere come era stato progettato.

Sulla base di queste informazioni il lavoro svolto dovrà essere
riproducibile.

In questa parte è richiesto l’inserimento di codice sorgente/print
screen di maschere solamente per quei passaggi particolarmente
significativi e/o critici.

Inoltre dovranno essere descritte eventuali varianti di soluzione o
scelte di prodotti con motivazione delle scelte.

Non deve apparire nessuna forma di guida d’uso di librerie o di
componenti utilizzati. Eventualmente questa va allegata.

Per eventuali dettagli si possono inserire riferimenti ai diari.

## Test

### Protocollo di test

Definire in modo accurato tutti i test che devono essere realizzati per
garantire l’adempimento delle richieste formulate nei requisiti. I test
fungono da garanzia di qualità del prodotto. Ogni test deve essere
ripetibile alle stesse condizioni.


|Test Case      | TC-001                               |
|---------------|--------------------------------------|
|**Nome**       |Import a card, but not shown with the GUI |
|**Riferimento**|REQ-012                               |
|**Descrizione**|Import a card with KIC, KID and KIK keys with no obfuscation, but not shown with the GUI |
|**Prerequisiti**|Store on local PC: Profile\_1.2.001.xml (appendix n\_n) and Cards\_1.2.001.txt (appendix n\_n) |
|**Procedura**     | - Go to “Cards manager” menu, in main page click “Import Profiles” link, Select the “1.2.001.xml” file, Import the Profile - Go to “Cards manager” menu, in main page click “Import Cards” link, Select the “1.2.001.txt” file, Delete the cards, Select the “1.2.001.txt” file, Import the cards |
|**Risultati attesi** |Keys visible in the DB (OtaCardKey) but not visible in the GUI (Card details) |


### Risultati test

Tabella riassuntiva in cui si inseriscono i test riusciti e non del
prodotto finale. Se un test non riesce e viene corretto l’errore, questo
dovrà risultare nel documento finale come riuscito (la procedura della
correzione apparirà nel diario), altrimenti dovrà essere descritto
l’errore con eventuali ipotesi di correzione.

### Mancanze/limitazioni conosciute

Descrizione con motivazione di eventuali elementi mancanti o non
completamente implementati, al di fuori dei test case. Non devono essere
riportati gli errori e i problemi riscontrati e poi risolti durante il
progetto.

## Consuntivo

Consuntivo del tempo di lavoro effettivo e considerazioni riguardo le
differenze rispetto alla pianificazione (cap 1.7) (ad esempio Gannt
consuntivo).

## Conclusioni

L'applicativo sviluppato facilita il compito della generazione di un fiocco, fatta precedentemente a mano. DOpo aver svolto il progetto mi sono accorto di quanta importanza si debba dare a certi particolari quali prevedere eventuali perdite di tempo, impegnarsi tanto nel tempo dedicato e non sottovalutare gli aspetti di implementazione che talvolta possono sembrare più facili.
Per quanto riguarda le disponibilità dell'utente nell'utilizzo, sicuramente si potrebbe fare di più, migliorare qualche dettaglio tralasciato per mancanza di tempo.

## Bibliografia

### Bibliografia per articoli di riviste
1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo dell’articolo (tra virgolette),

3.  Titolo della rivista (in italico),

4.  Anno e numero

5.  Pagina iniziale dell’articolo,

### Bibliografia per libri


1.  Cognome e nome (o iniziali) dell’autore o degli autori, o nome
    dell’organizzazione,

2.  Titolo del libro (in italico),

3.  ev. Numero di edizione,

4.  Nome dell’editore,

5.  Anno di pubblicazione,

6.  ISBN.

### Sitografia

1.  URL del sito (se troppo lungo solo dominio, evt completo nel
    diario),

2.  Eventuale titolo della pagina (in italico),

3.  Data di consultazione (GG-MM-AAAA).

**Esempio:**

-   http://standards.ieee.org/guides/style/section7.html, *IEEE
    Standards Style Manual*, 07-06-2008.

## Allegati

Elenco degli allegati, esempio:

-   Diari di lavoro

-   Codici sorgente/documentazione macchine virtuali

-   Istruzioni di installazione del prodotto (con credenziali
    di accesso) e/o di eventuali prodotti terzi

-   Documentazione di prodotti di terzi

-   Eventuali guide utente / Manuali di utilizzo

-   Mandato e/o Qdc

-   Prodotto

-   …
