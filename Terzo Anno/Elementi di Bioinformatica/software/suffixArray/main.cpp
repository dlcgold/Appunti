#include "method.hpp"
#include <iostream>
#include <string>
#include <vector>

int main(){
  std::string txt = "banana";
  std::vector<int> suffixArray = buildSuffixArray(txt);
  for(auto i: suffixArray)
    std::cout << i << " ";
  std::cout << std::endl;
  std::string pattern = "nan";
  std::vector<int> match = search(pattern, txt, suffixArray);
   for(auto i: match)
    std::cout << i << " ";
  return 0;
}
