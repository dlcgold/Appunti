#include "rubrica.h"
#include <algorithm>

rubrica::rubrica(): _size(0), _capacity(0), _voci(nullptr){
  
}

rubrica::~rubrica(){
  delete[] _voci;
  _voci = nullptr;
  _size = 0;
  _capacity = 0;
}

rubrica& rubrica::operator=(const rubrica &other){
  if(this != &other){
    rubrica tmp(other);
    std::swap(this->_size, tmp._size);
    std::swap(this->_capacity, tmp._capacity);
    std::swap(this->_voci, tmp._voci);
  }
  return *this;
}


rubrica::rubrica(const rubrica& other) : _size(0), _capacity(0), _voci(nullptr) {
  _voci = new voce[other._capacity];

  for(unsigned int i = 0; i < other._size; ++i)
    _voci[i] = other._voci[i];
  _size = other._size;
  _capacity = other._capacity;
}

unsigned int rubrica::get_size() const{
  return _size;
}

unsigned int rubrica::get_capacity() const{
  return _capacity;
}

rubrica::rubrica(unsigned int limit) : _size(0), _capacity(0), _voci(nullptr) {
  _voci = new voce[limit];
  _capacity = limit;
}

void rubrica::set_capacity(unsigned int limit) {
  if(limit > _size){
    voce *tmp = new voce[limit];
    for(unsigned int i = 0; i < _size; ++i){
      tmp[i] = _voci[i];
    }
    _capacity = limit;
    delete[] _voci;
    _voci = tmp;
  }else{
    rubrica tmp(limit);
    std::swap(this->_size, tmp._size);
    std::swap(this->_capacity, tmp._capacity);
    std::swap(this->_voci, tmp._voci);				
  }
}

