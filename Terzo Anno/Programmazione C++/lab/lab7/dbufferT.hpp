#ifndef DBUFFERT_H
#define DBUFFERT_H

#include <iostream>
#include <ostream>
#include <cassert>

template <typename T> class dbuffer{

private:
  unsigned int _size;
  T* _buffer;

public:
  // constructor
  dbuffer(): _size(0), _buffer(nullptr){
#ifndef NDEBUG
    std::cout<<"dbuffer::dbuffer()"<<std::endl;
#endif
  }

  explicit dbuffer(unsigned int size){
    _buffer = new T[size]; 
    _size = size;
    
#ifndef NDEBUG
    std::cout<<"dbuffer::dbuffer(unsigned int)"<<std::endl;
#endif
  }
  
  dbuffer(unsigned int size, const T &value){
    _buffer = new T[size];
    _size = size;
    // se riesce carica il buffer col valore
    try{
      for(unsigned int i = 0;  i < _size; ++i)
	_buffer[i] = value;
    }catch(...){
      delete[] _buffer;
      _buffer = nullptr;
      _size = 0;
      throw;
    }
#ifndef NDEBUG
    std::cout<<"dbuffer::dbuffer(unsigned int,int)"<<std::endl;
#endif
  }

  // copy constructor

  dbuffer& operator=(const dbuffer& rhs) {
    if(this != &rhs) {

      dbuffer tmp(rhs);

      std::swap(this->_buffer, tmp._buffer);
      std::swap(this->_size, tmp._size);		
    }

#ifndef NDEBUG
    std::cout<<"dbuffer::operator=(const dbuffer&)"<<std::endl;
#endif
	
    return *this;
  }

  // destructor

  ~dbuffer(){
    delete[] _buffer;
    _buffer = nullptr;
    _size = 0;

    
#ifndef NDEBUG
    std::cout<<"dbuffer::~dbuffer()"<<std::endl;
#endif
	
  }

  // getter

  unsigned int get_size() const{
    return _size;
  }

  T &get_value(unsigned int index) const{
    assert(index < _size); 
    return _buffer[index];
  }

  T &get_value_mut(unsigned int index){
    assert(index < _size); 
    return _buffer[index];
  }
  
  // setter

  void set_value(unsigned int index, const T &value) {
	assert(index < _size);
	
	_buffer[index] = value;
  }

  // overload []
  T& operator[](unsigned int index) {
    assert(index < _size);
    return _buffer[index];	
  }


  const T& operator[](unsigned int index) const {
    assert(index < _size);
    return _buffer[index];	
  }

  //print
  
  void print() const {
    std::cout << "dimensione: " << _size << std::endl;
    for(unsigned int i=0; i < _size; ++i)
      std::cout << _buffer[i] << std::endl;
  }

  //swap
  
  void swap(dbuffer &other) {
    std::swap(_buffer, other._buffer);
    std::swap(_size, other._size);
  }
  
  template <typename U>
  friend std::ostream &operator<<(std::ostream &os,const dbuffer<U> &db) {
    os << db._size << std::endl;
    for(unsigned int i =0;i < db._size; i++)
      os << db._buffer[i] << std::endl;

    return os; 
  }

  // iterartor to begin of dbuffer
  T* begin() {
    return _buffer;
  }

  const T* begin() const{
    return _buffer;
  }
  
  // to end
  T* end() {
    return _buffer + _size;
  }
  
  const  T* end() const{
    return _buffer + _size;
  }

  
};


#endif
