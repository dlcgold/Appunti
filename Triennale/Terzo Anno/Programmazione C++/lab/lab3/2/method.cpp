#include "method.h"
#include <iostream>
#include <cassert>
#include <algorithm>

void print_str(const char* str){
  assert(str != nullptr); // uso asserzione 
  
  int i = 0;
  while(str[i] != '\0'){
    std::cout << str[i] << std::endl;
    i++;
  }

  // oppure

  const char* current = str;
  while(*current != '\0'){
    std::cout << *current << std::endl;
    current++;
  }
  
}


int size_str(const char* str){
  assert(str != nullptr);
    
  int i = 0;
  while(str[i] != '\0'){
    i++;
  }
  // return i
  // oppure

  const char* current = str;
  while(*current != '\0'){
    current++;
  }
  return static_cast<int>(current - str);
}


void reverse_str(char* str){
  char* start = str;
  char* end = str + size_str(str) - 1; // -1 per togliere '\0'

  while(start < end){
    std::swap(*start, *end);
    start++;
    end--;
  }
}
