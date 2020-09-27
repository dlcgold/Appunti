#include <header.h>

void main(void){
    struct message m1, m2; // messaggio in entrata e uscita
    int r; // risultato
    
    while(TRUE){ // il server è sempre in esecuzione
        receive(FILE_SERVER, &m1); // stato di wait in attesa di m1
        switch(m1.code){ // vari casi in base alla richiesta
           case CREATE: 
             r = do_create(&m1, &m2);
             break;
           case CREATE: 
             r = do_read(&m1, &m2);
             break;
           case CREATE: 
             r = do_write(&m1, &m2);
             break;
           case CREATE: 
             r = do_delete(&m1, &m2);
             break;
           default:
             r = E_BAD_OPCODE;
     }
     m2.result = r; // ritorna il risultato al client
     send(m1.source, &m2); // manda la risposta
    }
}    
