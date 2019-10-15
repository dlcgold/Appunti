#include <iostream>


// passaggio a funzione per valori
// fa una copia bit a bit del valore
// ogni modifica al dato non viene propagata fuori dalla funzione
// si usa su dati piccoli, tali per cui le copie non sono un problema
void func1(double d){
  std::cout << d + 1 << std::endl;
}

// passaggio a funzione per puntatore
// si usa per dati grossi
// si ha la condivisione del dato e non la copia
// le modifiche vengono propagate
// obbligatorio nel caso di array anche se si perde la dimensione dell'array
// solitamente si passa anche la funzione
// posso avere anche la forma func(double dd[])
 
void func2(double* dd){
  // lavoro solo se non ho un nullptr
  if(dd != nullptr){
    std::cout << dd[0] << std::endl;
    dd[0] = 4;
  }
}
// passaggio a funzione per reference
// si ha condivisione
// ma in chiamata passo la variabile senza nulla
void func3(double &ddd){
  // non servono controlli
  ddd = 3;
}

// posso obbligare la non modifica dati con const
// l'eventuale modifica darebbe errore


struct myarray{
  int size;
  double* data;
};

void func5(myarray* a){
  for(int i = 0; a -> size; i++)
    a -> data[i] = 0;
}

void func6(int a, int b, int c = 10){
  // posso non passare c che viene in caso messo al valore di default
  std::cout << a + b + c << std:.endl;
}
int main(int argc, char** argv){

  int* vec = new int[100]; // il puntatoren è sullo stack, i dati sull'heap

  delete [] vec;
  //  definire matrici
  int** mat = nullptr;
  mat = new int*[10]; // array di puntatori
  // gestiamo l'eccezione di non allocazione della memoria
  int i = 0; // salvo fuori per fare una delete sensata nel catch
  
  try{
    for(i = 0; i< 10; i++)
      mat[i] = new int[100];
  }catch(...){
    //catch(...) significa catch su qualunque eccezione
    // elimino solo l'allocato, magari non tutto lo è 
    for(int o = 0; o < i; o++){ 
      delete [] mat[o];
      mat[i] = nullptr;
    }
    delete [] mat;
    mat = nullptr;
  }

  // un altro modo sarebebe stato inizializzare tutto a nullptr in quanto
  // poi la delete non avrebbe problemi, senza dover usare una variabile esterna
  // con:
  /* for(i = 0; i< 10; i++)
   *   mat[i] = nullptr;
   */
  
  mat[2][10];

  // anche per la delete devo ciclare
  for(int i = 0; i< 10; i++){      
    delete [] mat[i];
    mat[i] = nullptr;
  }

  delete [] mat;
  mat = nullptr;

  double d = 3.14;
  func1(d);
  
  double* dd = new double[100000];
  dd[0] = 2;
  func2(dd);
  std::cout << dd[0] << std::endl;
  delete[] dd;
  
  double ddd;
  func3(ddd);
  std::cout << ddd << std::endl;

  myarray test;
  test.size = 10;
  test.data = new double[10];
  //func5(test)
  
  return 0;
}
