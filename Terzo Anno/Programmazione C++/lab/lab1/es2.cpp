#include <iostream>

// questa è una struct
struct pi{
  int x;
  int y;
};

// vediamo gli enum (se i numeri sono in ordine metto solo il primo e gli altri vengono)
// messi in automatico

  enum giorno {
    lun=1,
    mar=3,
    mer=2,
    gio=7,
    ven=9,
    sab=90,
    dom=13
  };



int main(int argc, char *argv[]){
  // i puntatori contengono indirizzi di memoria
  // accedo a dove vive un dato che mi interessa
  // come tipo hanno quello del dato che ci interessa
  // VANNO SEMPRE INIZIALIZZATI, l'indirizzo di comodo è 0
  // o anche nullptr da c++11
  int i = 100;
  int* a = 0;
  int* b = nullptr;

  // per chiedere un indirizzo di una variabile uso &
  a = &i;
  // li sfrutto per accedere a più valori (array)
  std::cout << a << std::endl;

  // possiamo derefernziare un puntatore, modificando la cella puntata
  // modifico richiamando il puntatore con *
  *a = 2;
  std::cout << a << std::endl;
  std::cout << i << std::endl;
  std::cout << *a << std::endl;
  b = a;
  std::cout << a << std::endl;
  *b = 4;
  std::cout << i << std::endl;
  // i puntatori si possono concatenare (per array e matrici) con **

  // vediamo quindi gli array

  char arrayChar[2] = {'a', 'b'};
  int arrayInt[] = {1,2,3,4,5};
  // anche bidimensionali
  int matrix[2][3] = {{1,2,3},{4,5,6}};

  // lavoriamo con gli enum
  giorno g;
  g = sab;
  std::cout << g << std::endl; 
  int d = lun;
  std::cout << d << std::endl; 


  // le stringhe non sono altro che array di char

  char strc[10]="Buona ";
  char strl[]={'v', 'i', 't', 'a', ' '};
  const char *strp="ciao";

  std::cout << strp << std::endl;
  

  // passaggio parametri all'esecuzione

  // argc : numero di parametri passati al programma
  // argv : array di puntatori a char (stringhe C) con i parametri

  for(int i=1;i<argc;i++)
    std::cout << "Parametro [" << i << "] : " << argv[i] << std::endl;


  // vediamo i cast epliciti

  int ii;
  double dd;
  ii=static_cast<int>(dd);
  // cast esplicito C++ (1)
  int *pi;
  const int *cpi = &i;
  pi=const_cast<int*>(cpi); // Ok, cast esplicito C++ (2)
  char *c;
  c=reinterpret_cast<char*>(&i); // cast esplicito C++ (3)
  *(c+2)=0;
  return 0;
}
