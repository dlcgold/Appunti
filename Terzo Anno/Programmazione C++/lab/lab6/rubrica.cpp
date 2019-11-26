#include "rubrica.h"
#include <algorithm>
#include <cassert>
#include <fstream>

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
  // salvo le voci aumentando lo spazio per nuove voci se posso
  // altrimenti credo una nuova rubrica
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

voce& rubrica::operator [](unsigned int index) {
  assert(index < _size);
  return _voci[index];
}


const voce& rubrica::operator [](unsigned int index) const {
  assert(index < _size);
  return _voci[index];
}

void rubrica::add_voce(const voce& v) {
  if (_size < _capacity) {
    voce *tmp = nullptr;
    tmp = find_voce_helper(v.ntel);
    if(tmp == nullptr) {
      _voci[_size] = v;
      _size++;
    }
    else{ 
      throw duplicated_voce_exception();
    }
  }
  else {
    throw rubrica_out_of_space_exception();
  }
}

void rubrica::add_voce(const std::string& name, const std::string& surname, const std::string& ntel) {
  add_voce(voce(name, surname, ntel));
}

voce rubrica::find_voce(const std::string &nt) const {
  voce *risultato = nullptr;

  risultato = find_voce_helper(nt);
	
  if(risultato == nullptr)
    throw voce_not_found_exception();
  else
    return *risultato;
}

voce *rubrica::find_voce_helper(const std::string &nt) const{

  for(unsigned int i = 0; i < _size; ++i) {
    if (nt == _voci[i].ntel) 
      return &(_voci[i]);
  }
	
  return nullptr;
}

void rubrica::clear() {
  delete[] _voci;
  _voci = nullptr;
  _size = 0;
}

void rubrica::save(const std::string& filename) const {
  std::ofstream out;

  out.open(filename);
	
  if (out.is_open()) {
    out << *this;
  }
  else{
    throw file_error_exception();
  }
  out.close();
}

void rubrica::load(const std::string& filename) {
  std::ifstream ifs;

  ifs.open(filename);

  if(ifs.is_open()) {
    unsigned int capacity, size;
    ifs>>capacity;
    ifs>>size;
        
    rubrica tmp;

    tmp.set_capacity(capacity);
		
    for(unsigned int  i = 0;i < size;i++) {
      std::string name, surname, ntel;
      ifs >> name >> surname >> ntel;
      tmp.add_voce(name, surname, ntel);
    }
    std::swap(_voci,tmp._voci);
    std::swap(_size,tmp._size);
    std::swap(_capacity,tmp._capacity);
  }	
  else{
    throw fail_load_expection();
  }

  ifs.close();
	
}


std::ostream& operator <<(std::ostream& os, const rubrica& r) {
  os << r.get_capacity() << std::endl;  
  os << r.get_size() << std::endl;
  for(unsigned int i = 0; i < r.get_size(); ++i) {
    os << r[i];
    if (i < r.get_size() - 1)
      os << std::endl;
  }
  return os;
}

