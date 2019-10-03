#include <iostream>

struct pi{
  int x;
  int y;
};
int main(){
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
  return 0;
}
