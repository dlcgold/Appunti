#ifndef RUBRICA_H
#define RUBRICA_H


#include "voce.h"

class rubrica{
 private:
  
  unsigned int _size; // current size rubrica
  unsigned int _capacity; // max size rubrica
  voce* _voci;
  
 public:

  rubrica(); // constructor
  ~rubrica(); // destructor
  rubrica& operator=(const rubrica &other);
  rubrica(const rubrica& other); // copy constructor
  unsigned int get_size() const; // getter
  unsigned int get_capacity() const; // getter
  explicit rubrica(unsigned int limit); // constructor with limit
  void set_capacity(unsigned int limit); // setter
};

#endif
