#include <iostream>
#include "olist.hpp"
#include <cassert> // assert

struct equal_int {
  bool operator()(int a, int b) const {
    return a==b;
  }
};

struct compare_int {
  bool operator()(int a, int b) const {
    return a<b;
  }
};

typedef ordered_list<int,compare_int,equal_int> olint;

void test_metodi_fondamentali() {

  olint ol;

  ol.add(1);
  ol.add(2);
  ol.add(0);

  std::cout << "stampa di ol dopo inserimenti"
	    <<std::endl<< ol << std::endl;

  olint ol2(ol);

  std::cout << "stampa di ol2" 
	    << std::endl << ol2 << std::endl;

  olint ol3;

  ol3 = ol;	

  std::cout << "stampa di ol3" 
	    << std::endl << ol3 << std::endl;
}

void test_uso() {
	
  int a[5] = {7, 9, 12, 2, 1};
	
  olint ol(a, a + 5);

  std::cout << "stampa di ol costruito con gli iteratori" 
	    << std::endl << ol << std::endl;

  std::cout << "size di ol: " << ol.get_size() << std::endl;	
  assert(ol.get_size() == 5);

  bool ok1 = ol.find(12);
  assert(ok1 == true);

  bool ok2 = ol.find(120);
  assert(ok2 == false);
}


/**
   Funtore per il confronto tra stringhe. La valutazione e' fatta
   sulla lunghezza.  Ritorna true se la prima stringa e' piu' corta
   della seconda. 

   @brief Funtore per il confronto tra stringhe.
*/
struct compare_string {
  bool operator()(const std::string &a, const std::string &b) const {
    return (a.size()<b.size());
  } 
};

/**
   Funtore per l'uguaglianza tra stringhe. 

   @brief Funtore per il confronto tra stringhe.
*/
struct equal_string {
  bool operator()(const std::string &a, const std::string &b) const {
    return (a==b);
  } 
};

/**
   Struct point che implementa un punto 2D.

   @brief Struct point che implementa un punto 2D.
*/
struct point {
  int x; ///< coordinata x del punto
  int y; ///< coordinata y del punto

  point(int xx, int yy) : x(xx), y(yy) {}
};

/**
   Funtore per il confronto di due punti. 
   Ritorna true se p1.x < p2.x.

   @brief Funtore per il confronto di due punti.
*/
struct compare_point {
  bool operator()(const point &p1, const point &p2) const {
    return (p1.x<p2.x);
  } 
};

/**
   Funtore per il confronto di uguaglianza tra due punti. 
   Ritorna true se p1.x != p2.x.

   @brief Funtore per il confronto di due punti.
*/
struct equal_point {
  bool operator()(const point &p1, const point &p2) const {
    return (p1.x==p2.x) && (p1.y==p2.y);
  } 
};

/**
   Ridefinizione dell'operatore di stream << per un point.
   Necessario per l'operatore di stream della classe ordered_list.
*/
std::ostream &operator<<(std::ostream &os, const point &p) {
  std::cout<<"("<<p.x<<","<<p.y<<")";
  return os;
}

void test_list_int(olint &other) {
  std::cout<<"******** Test sulla lista di interi ********"<<std::endl;

  std::cout<<"Insertimento dei valori 56, 90, 60, 5, 60"<<std::endl;
  other.add(56);
  other.add(90);
  other.add(60);
  other.add(5);
  other.add(60);

  std::cout<<"Stampa con operator<<"<<std::endl;
  std::cout<<other<<std::endl;

  std::cout<<"Dimensione della lista: "<<other.get_size()<<std::endl;

  std::cout<<"Stampa con iteratori"<<std::endl;

  olint::const_iterator i,ie;
  i=other.begin();
  std::cout << *i;
    for(i=other.begin(),ie=other.end(); i!=ie; ++i)
      std::cout<<*i<<std::endl;

  other.clear();
  std::cout<<"Dimensione della lista dopo clear(): "<<other.get_size()
	   <<std::endl;
}

void test_const_list_int(const olint &other) {
  std::cout<<"******** Test sulla lista costante di interi ********"
	   <<std::endl;

  // add non e' chiamabile su un oggetto const

  std::cout<<"Stampa con operator<<"<<std::endl;
  std::cout<<other<<std::endl;

  std::cout<<"Dimensione della lista: "<<other.get_size()<<std::endl;

  std::cout<<"Stampa con iteratori"<<std::endl;

  olint::const_iterator i,ie;

  for(i=other.begin(),ie=other.end(); i!=ie; ++i)
    std::cout<<*i<<std::endl;

  // clear non e' chiamabile su un oggetto const
}

void test_funtore_stringhe(void) {
  std::cout<<"******** Test sulla lista di stringhe ********"<<std::endl;

  ordered_list<std::string, compare_string,equal_string> ols;

  std::cout<<"Insertimento dei valori 'pippo', 'pluto', 'paperino', 'cip'"<<std::endl;
  ols.add("pippo");
  ols.add("pluto");	
  ols.add("paperino");
  ols.add("cip");

  std::cout<<"Stampa con operator<<"<<std::endl;
  std::cout<<ols<<std::endl;

  std::cout<<"Dimensione della lista: "<<ols.get_size()<<std::endl;

  std::cout<<"Ricerca di 'cip': "<<ols.find("cip")<<std::endl;
  std::cout<<"Ricerca di 'cipp': "<<ols.find("cipp")<<std::endl;

  //E' possibile usare le asserzioni per verificare dei test
  //assert(ols.find("cip")==true);
}

void test_list_point(void) {

  ordered_list<point,compare_point,equal_point> op;

  std::cout<<"******** Test sulla lista di point ********"<<std::endl;

  std::cout<<"Insertimento dei valori (1,1), (1,2), (2,7), (0,0), (5,4)"<<std::endl;
  op.add(point(1,1));
  op.add(point(1,2));
  op.add(point(2,7));
  op.add(point(0,0));
  op.add(point(5,4));

  std::cout<<"Stampa con operator<<"<<std::endl;
  std::cout<<op<<std::endl;

  std::cout<<"Dimensione della lista: "<<op.get_size()<<std::endl;

  std::cout<<"Stampa con iteratori"<<std::endl;

  ordered_list<point,compare_point,equal_point>::const_iterator i,ie;

  for(i=op.begin(),ie=op.end(); i!=ie; ++i)
    std::cout<<*i<<std::endl;

  std::cout<<"Ricerca di '(1,1)': "<<op.find(point(1,1))<<std::endl;
  std::cout<<"Ricerca di '(2,2)': "<<op.find(point(2,2))<<std::endl;

}

int main() {

  test_metodi_fondamentali();

  test_uso();
	
  olint oli;

  test_list_int(oli);

  test_const_list_int(oli);

  test_funtore_stringhe();

  test_list_point();

  return 0;
}
