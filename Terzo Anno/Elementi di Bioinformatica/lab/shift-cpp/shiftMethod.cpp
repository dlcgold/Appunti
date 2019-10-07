#include "shiftMethod.h"

std::vector<int> shiftMethod(std::string pattern, std::string text){
  
  std::vector<int> results;  
  tsl::ordered_map<char, int> mask;

  // charge the map 
  for(unsigned int i = 0; i < pattern.size(); i++){
    mask.insert(std::pair<char, int>(pattern.at(i), 0));
  }
  
  for(unsigned int i = 0; i < pattern.size(); i++){
    mask.at(pattern.at(i)) = mask.at(pattern.at(i)) | (1 << i);
  }

  // insert random char with 0 as value for not match
  mask.insert(std::pair<char, int>('*', 0));

  // execution of shift 
  int d = 0; // variable for the check 
  bool match = true;
  int matchMask = 1 << (pattern.size() - 1);

  for (unsigned int i = 0; i < text.size(); i++){
    if(mask.count(text.at(i))){
      d = ((d << 1) | 1) & (mask.at(text.at(i)) | 0);
    }else{
      d = ((d << 1) | 1) & (mask.at('*') | 0);
    }
    match = d & matchMask;
    if(match != 0)
      results.push_back(i - pattern.size() + 1);
  }
  return results;
}
