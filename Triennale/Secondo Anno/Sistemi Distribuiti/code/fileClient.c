#include <header.h>

int copy(char *src, char *dst){ // copia file usando il server
    strcut message m1; // buffer del messaggio
    long position; // attuale posizione del file
    long client = 110; // indirizzo del client
   
    initialize(); // prepara l'esecuzione
    position = 0; 
    do{
        m1.opcode = READ; // operazione settata su READ
        m1.offset = position; // scelta la posizione nel file
        m1.count = BUF_SIZE; // byte da leggere
        strcpy(&m1.name, src); // nome file copiato in m1
        send(FILESERVER, &m1); // manda il messaggio al file server
        receive(client, &m1); // aspetta la risposta
 
        // scrive quanto ricevuto su un file di destinazione
        m1.opcode = WRITE; // operazione settata su WRITE
        m1.offset = position; // scelta la posizione nel file
        m1.count = BUF_SIZE; // byte da leggere
        strcpy(&m1.name, dst); // nome del file sul buffer
        send(FILESERVER, &m1); // manda il messaggio al file server
        receive(client, &m1); // aspetta la risposta
        position += m1.result // il risultato sono i byte scritti
    }while(m1.result > 0); // itera fino alla fine
    return(m1.result >= 0 ? OK : m1.result); // ritorna OK o l'errore
}

