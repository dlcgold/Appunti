#include "method.h"
#include <iostream>
#include <cassert> 

dbuffer::dbuffer(){
  _size = 0;
  _buffer = nullptr;

#ifndef NDEBUG
  std::cout<<"dbuffer::dbuffer()"<<std::endl;
#endif
}

// constructor without setting values
dbuffer::dbuffer(unsigned int size) {
  _buffer = new int[size];
  _size = size;

#ifndef NDEBUG
  std::cout<<"dbuffer::dbuffer(unsigned int)"<<std::endl;
#endif
}

// constructor with a default value
dbuffer::dbuffer(unsigned int size, int value) {
  _buffer = new int[size];
  _size = size;

  for(int i=0; i<_size; ++i)
    _buffer[i] = value;
	
#ifndef NDEBUG
  std::cout<<"dbuffer::dbuffer(unsigned int,int)"<<std::endl;
#endif
}
// destructor
dbuffer::~dbuffer() {
  delete[] _buffer;
  _buffer = nullptr;
  _size = 0;

#ifndef NDEBUG
  std::cout<<"dbuffer::~dbuffer()"<<std::endl;
#endif
}

int dbuffer::get_value(unsigned int index){
  assert(index<_size);
  return _buffer[index];
}

void dbuffer::set_value(unsigned int index, int value) {
  assert(index < _size);	
  _buffer[index] = value;
}

unsigned int dbuffer::size() {
  return _size;
}

