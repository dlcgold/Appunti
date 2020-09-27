#include "method.h"
#include <iostream>
#include <cassert>

namespace dynbuf{
  void print_buf(const dynamic_buffer &buffer_test){
    for(unsigned int i = 0; i < buffer_test.size; i++){
      std::cout << buffer_test.buffer[i] << " ";
    }
    std::cout << std::endl;
  }

  void costr_zero(dynamic_buffer &buffer_test){
    buffer_test.size = 0;
    buffer_test.buffer = nullptr;
  }

  void costr(dynamic_buffer &buffer_test, unsigned int size){
    if(buffer_test.buffer != nullptr){
      dealloc(buffer_test);
    }
    buffer_test.buffer = new int[size];
    buffer_test.size = size;
  }
  
  void dealloc(dynamic_buffer &buffer_test){
    delete[] buffer_test.buffer;
    costr_zero(buffer_test);
  }
  
  void charge(dynamic_buffer &buffer_test, const int* array, unsigned int size){
    assert(array != nullptr);
    assert(buffer_test.buffer != nullptr);
    // se sono uguali copio tutto, altriemnti fino a dove ci stanno o fino a che
    // non esaurisco array
    if(buffer_test.size == size){
      for(unsigned int i = 0; i < size; i++){
	buffer_test.buffer[i] = array[i];
      }
    }else if (buffer_test.size > size){ 
      for(unsigned int i = 0; i < size; i++){
	buffer_test.buffer[i] = array[i];
      }
    }else{
       for(unsigned int i = 0; i < buffer_test.size; i++){
	buffer_test.buffer[i] = array[i];
      }
    }
  }

  dynamic_buffer copy(const dynamic_buffer &buffer_test){
    assert(buffer_test.buffer != nullptr);
    dynamic_buffer copy;
    costr_zero(copy);
    costr(copy, buffer_test.size);
    for(unsigned int i = 0; i < buffer_test.size; i++)
      copy.buffer[i] = buffer_test.buffer[i];
    return copy;
  }
}
