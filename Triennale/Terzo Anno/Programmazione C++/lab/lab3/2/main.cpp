#include "method.h"
#include <iostream>
int main(int argc, char** argv){

  print_str(argv[1]);
  std::cout << std::endl;
  std::cout << "la stringa è lunga: " << size_str(argv[1]) << std::endl;
  reverse_str(argv[1]);
  print_str(argv[1]);
  return 0;
}
