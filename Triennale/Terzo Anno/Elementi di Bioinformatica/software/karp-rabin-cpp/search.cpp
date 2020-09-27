#include "search.h"
#include <iostream>
std::set<int> search(std::string pattern, std::string text, int q){
  std::set<int> results;
  int m = pattern.size();
  int n = text.size();
  int j; // iterator check
  int p = 0; // hash for pattern
  int t = 0; // hash for text
  int h = (int)std::pow(d, m-1) % q; // n_bit^(m-1)

  // hash of pattern
  for(auto i: pattern){
    p = (d * p + i) % q;
  }

  
  // hash for text
  for(int i = 0; i < m; i++){
    t = (d * t + text[i]) % q;
  }

  // slide over text
  for(int i = 0; i <= n - m; i++){
    // first check of hashes
    // if correct check characters
    if (p == t){
      // check characters one by one
      for(j = 0; j < m; j++){
	if(text[i + j] != pattern[j])
	  break;
      }
      if(j == m)
	results.insert(i);
    }
    // Calculate hash value for next window of text
    if (i < n-m){
      t = (d * (t - text[i] * h) + text[i + m]) % q;
      // if negative make it positive
      if(t < 0)
	t = (t + q);
    }
  }
  return results;
}


