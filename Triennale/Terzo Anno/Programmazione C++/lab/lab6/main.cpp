#include <iostream>
#include <cassert>
#include "rubrica.h"

void test_voce();
void test_rubrica();
void test_load();
void test_interattivo();
int main(int argc, char *argv[]) {

  test_voce();
  test_rubrica();
  test_load();
  test_interattivo();
  return 0;
}

void test_voce(void) {

  std::cout<<"*** TEST VOCE ***" << std::endl << std::endl;

  voce v;

  std::cout << v << std::endl;
  assert(v.name=="");
  assert(v.surname=="");
  assert(v.ntel=="");

  voce v2("Paolino","Paperino","313");
  std::cout << v2 << std::endl;
  assert(v2.name=="Paolino");
  assert(v2.surname=="Paperino");
  assert(v2.ntel=="313");
	
  voce v3(v2);
  std::cout << v3 << std::endl;
  assert(v3.name=="Paolino");
  assert(v3.surname=="Paperino");
  assert(v3.ntel=="313");
	
  v = v2;
  std::cout << v << std::endl;
  assert(v.name=="Paolino");
  assert(v.surname=="Paperino");
  assert(v.ntel=="313");

  v.name = "Mickey";
  v.surname = "Mouse";
  v.ntel = "111";
  std::cout << v << std::endl;
  assert(v.name=="Mickey");
  assert(v.surname=="Mouse");
  assert(v.ntel=="111");
}


void test_rubrica() {

  std::cout<<std::endl<<"*** TEST RUBRICA ***" << std::endl << std::endl;

  rubrica r1;

  std::cout<<r1<<std::endl;

  rubrica r2(10);

  std::cout<<r2<<std::endl;

  r1 = r2; // assegnamento

  std::cout << r1 << std::endl;

  rubrica r3 = r2; //copy constructor

  try {
    // Aggiungiamo 11 voci (NB r3 ha capacita' 10)
    r3.add_voce("nome1","cognome1","2222222");
    r3.add_voce("nome2","cognome2","2222227");
    r3.add_voce("nome3","cognome3","22272222");
    r3.add_voce("nome4","cognome4","22212222");
    r3.add_voce("nome5","cognome5","22229222");
    r3.add_voce("nome6","cognome6","23222222");
    r3.add_voce("nome7","cognome7","222222222");
    r3.add_voce("nome8","cognome8","229822222");
    r3.add_voce("nome9","cognome9","20022222");
    r3.add_voce("nome10","cognome10","222652222");
    r3.add_voce("nome11","cognome11","55555555"); // Viene lanciata una eccezione
  }
  catch(rubrica_out_of_space_exception &e) {
    // Catturiamo l'eccezione che abbiamo volutamente provocato
    //
    //	ATTENZIONE: se nei test provocate volutamente il lancio di una eccezione,
    //	gestitela sempre con un blocco try/catch altrimenti valgrind puo' darvi 
    //	dei falsi memory leak.
		
    std::cout<<"!! errore di rubrica piena !!"<<std::endl;
  }

  std::cout << r3 << std::endl;

  try {
    voce v = r3.find_voce("999999");
  }
  catch(voce_not_found_exception &e) {
    std::cout<<"!! errore voce non trovata !!"<<std::endl;
  }
	
  std::cout<< r3.find_voce("222652222") << std::endl;

  r3[0]=voce("nome12","cognome12","555123"); // creiamo al volo una voce con il costruttore
  assert(r3[0].name=="nome12");
  assert(r3[0].surname=="cognome12");
  assert(r3[0].ntel=="555123");

  std::cout << r3 << std::endl;

  r3.save("output.txt");
}

void test_load() {
  std::cout<< std::endl <<"*** TEST LOAD ***" << std::endl << std::endl;

  rubrica r;

  r.load("output.txt");

  std::cout<<r<<std::endl;
}

void test_interattivo() {
  rubrica rub;

  unsigned int capacity;

  std::cout<<"Inserire la capacita' della rubrica: ";
  std::cin >> capacity;

  if (capacity==0) {
    std::cout << rub << std::endl;
    return;
  }

  rub.set_capacity(capacity);

  std::string cognome, nome, ntel;

  do {
    std::cout<<"Inserire una voce"<<std::endl;
    std::cout<<"  Cognome (* per terminare): ";
    std::cin >> cognome;
		
    if (cognome!="*") {
			
      std::cout<<"  Nome: ";
      std::cin >> nome;

      std::cout<<"  Numero Tel: ";
      std::cin >> ntel;

      try {
	rub.add_voce(nome, cognome, ntel);
      }
      catch(rubrica_out_of_space_exception &e) {
	std::cout<<"****** Rubrica Piena!"<<std::endl;
	cognome = "*";			
      }
      catch(duplicated_voce_exception &e) {
	std::cout<<"****** Voce duplicata. Inserirne un'altra."<<std::endl;			
      }
    }
  } while(cognome!="*"); // fine do-while

  std::cout<<rub<<std::endl;

  std::string answer, filename;

  std::cout<<"Vuoi salvare la rubrica (y/n)?";
  std::cin >> answer;

  if (answer[0]=='n') return;

  std::cout<<"Nome del file: ";
  std::cin >> filename;

  try {
    rub.save(filename);
    std::cout<<"Rubrica rub salvata."<<std::endl;

    rubrica rub2;

    rub2.load(filename);

    std::cout<<"Rubrica rub2 caricata."<<std::endl;
    std::cout<<rub2<<std::endl;
  }
  catch(file_error_exception &e) {
    std::cout << "****** Errore File!"<<std::endl;
  }
}
