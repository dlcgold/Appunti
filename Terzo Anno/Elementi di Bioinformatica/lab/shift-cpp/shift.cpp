#include <iostream>
#include <vector>
#include <fstream>
#include "shiftMethod.h"

int main(int argc, char *argv[]){
  
  if(argc == 1){
    std::cerr << "Missing file-name\nusage: ./bin/shift [file]\n";
    return -1;
  }

  std::string pattern= "";
  std::cout << "Insert pattern: ";
  std::cin >> pattern;
  
  std::string text;
  std::fstream ifs(argv[1]);
  std::getline(ifs, text, (char) ifs.eof());

  std::vector<int> results = shiftMethod(pattern, text);
  std::cout << results.size() << " occurrance at: ";
  for(auto i: results)
    std::cout << i << " ";
  return 0;
    
}

