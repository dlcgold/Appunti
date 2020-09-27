 // definizioni necessarie a client e server 
  
 #define TRUE 1
 #define MAX_PATH 255 // lunghezza massima del nome di un file
 #define BUF_SIZE 1024 // massima grandezza file trasferibili per volta
 #define FILE_SERVER 243 // indirizzo di rete del file del server
 167 
// operazioni permesse 
 
#define CREATE 1 // crea un nuovo file
#define READ 2 // legge il contenuto di un file e lo restituisce
#define WRITE 3 // scrive su un file
#define DELETE 4 // cancella un file
 174 
 175 // errori
 176 
#define OK 0 // nessun errore
#define E_BAD_OPCODE -1 // operazione sconosciuta
#define E_BAD_PARAM -2 // errore in un parametro
#define E_IO -3 // errore del disco o errore di I/O
 181 
// definizione del messaggio
 183 
struct message{
    long source; // identità del mittente
    long dest; // identità del ricevente
    long opcode; // operazione richiesta 
    long count; // numero di byte da trasferire
    long offset; // posizione sul file da cui far partire l'I/O
    long result; // risultato dell'operazione
    char name[MAX_PATH]; // nome del file
    char data[BUF_SIZE]; //informazione da leggere o scrivere
};

