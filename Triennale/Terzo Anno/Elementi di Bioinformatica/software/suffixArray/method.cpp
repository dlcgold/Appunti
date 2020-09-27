#include "method.hpp"

std::vector<int> buildSuffixArray(std::string txt) {
  std::vector<suffix> suffixes;

  for(int i = 0; i < txt.size(); i++){
    suffix var;
    var.index = i;
    var.suff = txt[i];
    suffixes.push_back(var);
  }

  // riordino con una lambda adeguata
  std::sort(suffixes.begin(), suffixes.end(),
	    [](suffix first, suffix second){
	      return first.suff > second.suff ? 0 : 1;
	    });

  std::vector<int> result;
  for(auto i: suffixes){
    result.push_back(i.index);
  }
  return result;
}

std::vector<int> search(std::string pattern, std::string txt,
			std::vector<int> suffixArray) {
  // converto string in array di char per strncmp
  char patternA[pattern.size() + 1];
  std::strcpy(patternA, pattern.c_str());

  char txtA[txt.size() + 1];
  std::strcpy(txtA, txt.c_str());

  
  std::vector<int> matchArray;
  int l = 0;
  int r = txt.size() - 1;

  // seguo l'algortimo fatto in aula
  while(l <= r){
    int mid = l + (r - l) / 2;
    // res restituisce un n maggiore, minore o uguale a 0 a seconda del confronto
    // tra le due stringhe ( se la prima è maggiore, minore o
    // uguale alla seconda)
    int res = std::strncmp(patternA, txtA + suffixArray[mid], pattern.size());
    if(res == 0){
      matchArray.push_back(suffixArray[mid]);
      return matchArray;
    }else if(res < 0){
      r = mid -l;
    }else{
      l = mid + 1;
    }
    
  }
  return matchArray;
}
