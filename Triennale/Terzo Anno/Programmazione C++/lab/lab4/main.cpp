#include "method.h"
#include <iostream>

int main(){
  dynbuf::dynamic_buffer test;
  
  
  //dynbuf::print_buf(test);
  //std::cout << std::endl;
  dynbuf::costr_zero(test);
  
  //std::cout << test.size << " " << test.buffer;
  dynbuf::costr(test, 5);
  //dynbuf::print_buf(vec);
  //std::cout << test.size << " " << test.buffer;
  //dynbuf::dealloc(vec);
  //std::cout << test.size << " " << test.buffer;
  int array[] = {1, 2, 3, 4, 5};
  dynbuf::charge(test, array, 5);
  dynbuf::print_buf(test);
  dynbuf::dynamic_buffer testCopy = dynbuf::copy(test);
  dynbuf::print_buf(testCopy);
  dynbuf::dealloc(test);
  dynbuf::dealloc(testCopy);
  return 0;
}
