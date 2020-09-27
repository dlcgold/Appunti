#ifndef METHOD_HPP
#define METHOD_HPP

#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <cstring>

struct suffix{
  int index;
  std::string suff;
};

std::vector<int> buildSuffixArray(std::string);
std::vector<int> search(std::string, std::string, std::vector<int>);


#endif
