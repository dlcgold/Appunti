#include <iostream>  // per usare cout
// ciò che non vive in una class evive nel namespace globale

struct test{
  char c;
  int s;
  int ss;
};

int main(){
  // stampiamo un messaggio
  // std è un namespace in qaunto cout vive dentro std (una sorta di package)
  std::cout << "hello world" << std::endl;
  // return 0 è tradizione per il ritorno del main senza errori
  // vediamo l'input
  int n;
  std::cin >> n;
  int* m = new int[3];
  // se true o 1 ho un cin errato ma comunque carica 0
  if (!std::cin.fail())
    std::cout << "la variabile è " << n << std::endl;
  else
    std::cout << "errore\n";
  //oltre i soliti tipi posso anche forzare i bit usati dai tipi
  short int i; // dimezza i bit
  long int ii; // raddoppia i bit
  double dd;
  test d;
  d.s = 1;
  // vediamo la dimensione (in char) dei dati
  std::cout << sizeof(test) << std::endl;
  std::cout << d.s << "\n";
  
  return 0;
}

